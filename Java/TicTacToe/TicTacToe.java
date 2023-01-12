package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    //<editor-fold desc="Private Variables">
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnFour;
    private JButton btnFive;
    private JButton btnSix;
    private JButton btnSeven;
    private JButton btnEight;
    private JButton btnNine;

    private JButton btnReset;
    private JTextField txtTurn;

    private boolean turn;

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private Timer t;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public TicTacToe() {
        setSize(WIDTH, HEIGHT);

        txtTurn = new JTextField("Player 1", JTextField.CENTER);

        //When true its player ones turn, when false its player twos turn.
        turn = true;

        createButtons();

        addActionToButtons();

        t = new Timer(15000, new TimerSwitchListener());
        t.start();

        add(panels());
    }
    //</editor-fold>

    //<editor-fold desc="Methods">

    //Method Name: createButtons()
    //Return Type: void
    //Arguments:
    //Method Desc: initializes all the JButtons
    private void createButtons() {
        btnReset = new JButton("Reset");

        btnOne = new JButton(" ");
        btnTwo = new JButton(" ");
        btnThree = new JButton(" ");
        btnFour = new JButton(" ");
        btnFive = new JButton(" ");
        btnSix = new JButton(" ");
        btnSeven = new JButton(" ");
        btnEight = new JButton(" ");
        btnNine = new JButton(" ");
    }

    //Method Name: addActionToButtons()
    //Return Type: void
    //Arguments:
    //Method Desc: adds corresponding ActionListener class to each JButton
    private void addActionToButtons() {
        btnOne.addActionListener(new ButtonListener());
        btnTwo.addActionListener(new ButtonListener());
        btnThree.addActionListener(new ButtonListener());
        btnFour.addActionListener(new ButtonListener());
        btnFive.addActionListener(new ButtonListener());
        btnSix.addActionListener(new ButtonListener());
        btnSeven.addActionListener(new ButtonListener());
        btnEight.addActionListener(new ButtonListener());
        btnNine.addActionListener(new ButtonListener());

        btnReset.addActionListener(new ButtonResetListener());
    }

    //Method Name: panels()
    //Return Type: JPanel
    //Arguments:
    //Method Desc: adds corresponding objects to panels and panels to one main panel that is return to be added to JFrame TicTacToe
    private JPanel panels() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        topPanel.add(btnReset);
        topPanel.add(txtTurn);

        centerPanel.setLayout(new GridLayout(3,3));

        centerPanel.add(btnOne);
        centerPanel.add(btnTwo);
        centerPanel.add(btnThree);
        centerPanel.add(btnFour);
        centerPanel.add(btnFive);
        centerPanel.add(btnSix);
        centerPanel.add(btnSeven);
        centerPanel.add(btnEight);
        centerPanel.add(btnNine);

        panel.setLayout(new BorderLayout());
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    //Method Name: switchTurn()
    //Return Type: void
    //Arguments:
    //Method Desc: switches the variable turn from true to false/false to true and sets text of txtTurn to correspond to whoever's turn it is
    private void switchTurn() {
        turn = !turn;

        if(turn) {
            txtTurn.setText("Player 1");
        }
        else{
            txtTurn.setText("Player 2");
        }

        t.restart();
    }

    //Method Name: checkWin()
    //Return Type: void
    //Arguments: String letter
    //Method Desc: checks to see if a require pattern is met to 'win'. Changes the background color of pattern and disables all buttons. Also stops the timer
    private void checkWin(String letter) {
        Boolean won = false;

        //There are four blocks of if statements. The first one checks horizontal patterns. The second vertical. The third checks on diagonal and the fourth the other diagonal.
        //If any of the conditions are true it makes the buttons it checked turn red and sets won equal to true.
        //Won is checked to see if it has turned true. If so, it disables all the buttons (except reset) stops the timer (t) and displays the winner message.

        if(btnOne.getText().equals(letter) && btnTwo.getText().equals(letter) && btnThree.getText().equals(letter)) {
            btnOne.setBackground(Color.red);
            btnTwo.setBackground(Color.red);
            btnThree.setBackground(Color.red);

            won = true;
        }

        else if(btnFour.getText().equals(letter) && btnFive.getText().equals(letter) && btnSix.getText().equals(letter)) {
            btnFour.setBackground(Color.red);
            btnFive.setBackground(Color.red);
            btnSix.setBackground(Color.red);

            won = true;
        }

        else if(btnSeven.getText().equals(letter) && btnEight.getText().equals(letter) && btnNine.getText().equals(letter)) {
            btnSeven.setBackground(Color.red);
            btnEight.setBackground(Color.red);
            btnNine.setBackground(Color.red);

            won = true;
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(btnOne.getText().equals(letter) && btnFour.getText().equals(letter) && btnSeven.getText().equals(letter)) {
            btnOne.setBackground(Color.red);
            btnFour.setBackground(Color.red);
            btnSeven.setBackground(Color.red);

            won = true;
        }

        else if(btnTwo.getText().equals(letter) && btnFive.getText().equals(letter) && btnEight.getText().equals(letter)) {
            btnTwo.setBackground(Color.red);
            btnFive.setBackground(Color.red);
            btnEight.setBackground(Color.red);

            won = true;
        }

        else if(btnThree.getText().equals(letter) && btnSix.getText().equals(letter) && btnNine.getText().equals(letter)) {
            btnThree.setBackground(Color.red);
            btnSix.setBackground(Color.red);
            btnNine.setBackground(Color.red);

            won = true;
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(btnOne.getText().equals(letter) && btnFive.getText().equals(letter) && btnNine.getText().equals(letter)) {
            btnOne.setBackground(Color.red);
            btnFive.setBackground(Color.red);
            btnNine.setBackground(Color.red);

            won = true;
        }

        if(btnThree.getText().equals(letter) && btnFive.getText().equals(letter) && btnSeven.getText().equals(letter)) {
            btnThree.setBackground(Color.red);
            btnFive.setBackground(Color.red);
            btnSeven.setBackground(Color.red);

            won = true;
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(won) {
            disableButtons();
            t.stop();
            displayWinner(letter);
        }
    }

    //Method Name: displayWinner()
    //Return Type: void
    //Arguments: String letter
    //Method Desc: when called, uses the string letter to display the corresponding pop up message
    private void displayWinner(String letter) {
        if(letter.equals("X")) {
            JOptionPane.showMessageDialog(null, "Player One (X) Won!!!\n\nClick the reset button to start a new game");
        }
        else{
            JOptionPane.showMessageDialog(null, "Player Two (O) Won!!\n\nClick the reset button to start a new game");
        }
    }

    //Method Name: disableButtons()
    //Return Type: void
    //Arguments:
    //Method Desc: disables all the buttons (excepts reset)
    private void disableButtons() {
        btnOne.setEnabled(false);
        btnTwo.setEnabled(false);
        btnThree.setEnabled(false);
        btnFour.setEnabled(false);
        btnFive.setEnabled(false);
        btnSix.setEnabled(false);
        btnSeven.setEnabled(false);
        btnEight.setEnabled(false);
        btnNine.setEnabled(false);
    }

    //Method Name: resetButtons()
    //Return Type: void
    //Arguments:
    //Method Desc: sets all the buttons back to default constructor settings
    private void resetButtons() {
        btnOne.setText("");
        btnTwo.setText("");
        btnThree.setText("");
        btnFour.setText("");
        btnFive.setText("");
        btnSix.setText("");
        btnSeven.setText("");
        btnEight.setText("");
        btnNine.setText("");

        btnOne.setEnabled(true);
        btnTwo.setEnabled(true);
        btnThree.setEnabled(true);
        btnFour.setEnabled(true);
        btnFive.setEnabled(true);
        btnSix.setEnabled(true);
        btnSeven.setEnabled(true);
        btnEight.setEnabled(true);
        btnNine.setEnabled(true);

        btnOne.setBackground(null);
        btnTwo.setBackground(null);
        btnThree.setBackground(null);
        btnFour.setBackground(null);
        btnFive.setBackground(null);
        btnSix.setBackground(null);
        btnSeven.setBackground(null);
        btnEight.setBackground(null);
        btnNine.setBackground(null);
    }
    //</editor-fold>

    //<editor-fold desc="Action Listeners">
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("X") || ((JButton)e.getSource()).getText().equals("O")) {
               JOptionPane.showMessageDialog(null, "Please select a button that is blank", "Try Again", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (turn) {
                    ((JButton)e.getSource()).setText("X");

                    checkWin("X");
                } else {
                    ((JButton)e.getSource()).setText("O");

                    checkWin("O");
                }

                switchTurn();
            }
        }
    }

    class ButtonResetListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            turn = false;

            switchTurn();

            resetButtons();

            t.restart();
        }
    }

    class TimerSwitchListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            switchTurn();
        }
    }
    //</editor-fold>
}
