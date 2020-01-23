package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    TicTacToe game = new TicTacToe();

	    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    game.setTitle("Nestor's Not So Broken TicTacToe");
	    game.setVisible(true);
    }
}
