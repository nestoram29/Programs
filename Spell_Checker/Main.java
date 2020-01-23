package com.company;

/*
Nestor Molina
Program 4: Binary Search Trees
Due: Nov 1, 2017
 */

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    private static DictionaryTree dictionary = new DictionaryTree();

    public static void main(String[] args) {
        fileToTree();
    }

    // Method Name: fileToTree()
    // Return Type: void
    // Arguments:
    // Method Desc: inserts data from 'dictionary.txt' into an avl tree
    public static void fileToTree() {
        try{
            Scanner dFile = new Scanner( new FileReader("dictionary.txt"));

            while(dFile.hasNextLine()) {
                dictionary.insert(dFile.nextLine());
            }

            dFile.close();
            spellChecker();
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The 'dictionary.txt' file could not be found!", "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method Name: spellChecker()
    // Return Type: void
    // Arguments:
    // Method Desc: prompts the user to enter a file. Spells checks the words of the file with the avl tree from fileToTree(). Ask user to add word to tree or not. Displays log to user.
    public static void spellChecker() {
        try{
            String inputFile = JOptionPane.showInputDialog("Please enter the name of the file which contains the text you wish to spell check.");

            Scanner inFile = new Scanner(new FileReader(inputFile));

            int lineNum = 1;

            String f = "%-20s%5s%5s%5s%-20s";
            String log = String.format(f, "Word", " ", "Line", " ", "Added") + "\n";
            String print = "Line and word not found:\n";

            while(inFile.hasNextLine()) {
                String line = inFile.nextLine();
                String[] words = line.split(" ");

                for(int i = 0; i < words.length; i++) {
                    String word = words[i];

                    boolean found = dictionary.search(word);

                    if(!found) {
                        int yesNo = JOptionPane.showConfirmDialog(null, "'" + word + "' " + "was not found in the dictionary.\n\nWould you like to add it?");

                        if(yesNo == JOptionPane.YES_OPTION) {
                            dictionary.insert(word);

                            log += String.format(f, word, " ", lineNum, " ", "Yes") + "\n";
                        }
                        else{
                            log += String.format(f, word, " ", lineNum, " ", "No") + "\n";
                        }

                        print += "Line: " + String.format("%2d", lineNum) + " " + word + "\n";
                    }
                }

                lineNum++;
            }

            inFile.close();

            try{
                PrintWriter logWriter = new PrintWriter("spellerlog.txt");

                logWriter.print(log);

                logWriter.close();
            }
            catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "The 'spellerlog.txt' could not be found!", "Missing File", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, print, "Log", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The file you input could not be found!", "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }
}
