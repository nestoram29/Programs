package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Expression> expressions = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        try {
            displayMenu();
        }
        catch(NullPointerException e) {}
    }

    // Method Name: displayMenu()
    // Return Type: void
    // Arguments:
    // Method Desc: used to display the main menu to the user
    public static void displayMenu() throws FileNotFoundException {
        String menu = "Please choose one of the following\n\n" +
                      "1) Input a file with infix expressions and output to 'stacks.out'\n" +
                      "2) Exit\n";

        String option = "";

        while(!(option.equals("2"))) {
            option = JOptionPane.showInputDialog(menu);

            switch (option) {
                case "1":
                    fileInput();
                    break;
                case "2":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Your input was invalid!\n\nPlease try again.");
            }
        }
    }

    // Method Name: fileInput()
    // Return Type: void
    // Arguments:
    // Method Desc: used to prompt user for file name, which is used to create instances of the Expression class
    public static void fileInput() {
        String inputFileName = JOptionPane.showInputDialog("Please enter the name of the input file");

        try{
            Scanner in = new Scanner(new FileReader(inputFileName));

            while(in.hasNextLine()) {
                Expression temp = new Expression(in.nextLine());

                expressions.add(temp);
            }

            in.close();

            fileOutput(inputFileName);
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The file name you input was not found(" + inputFileName + "). Please try again.", "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch (NullPointerException e) {}
    }

    // Method Name: fileOutput
    // Return Type: void
    // Arguments: String inputName
    // Method Desc: used to write to stacks.out
    public static void fileOutput(String inputName) {
        String f = "%-50s%5s%-50s%5s%5s";

        try{
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("stacks.out"), true));

            writer.println("Nestor Molina");
            writer.println("Input File: " + inputName);
            writer.println();
            writer.println(String.format(f, "Infix Expression", " ", "Postfix Expression", " ", "Value"));

            for(int i = 0; i < expressions.size(); i++) {
                if(expressions.get(i).isError()) {
                   String inexp = expressions.get(i).getInfixExp();

                   writer.println(String.format(f, inexp, " ", "Invalid infix expression", " " , " "));
                }
                else{
                    String inexp = expressions.get(i).getInfixExp();
                    String postexp = expressions.get(i).getPostfixExp();
                    String value = Integer.toString(expressions.get(i).getValue());

                    writer.println(String.format(f, inexp, " ", postexp, " ", value));
                }
            }

            writer.println();

            writer.close();
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "The file stacks.out could not be found! Please create file before running this program.", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        expressions.clear();
    }
}
