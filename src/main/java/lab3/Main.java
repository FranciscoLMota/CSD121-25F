package main.java.lab3;

import main.java.lab3.game.Board;
import main.java.lab3.game.Play;

public class Main {

    static void main(String[] args) {

        // Initialize a TicTacToe game with a 3x3 board and two players
        var game = new Board();

        IO.print(game.checkWin(Play.PO));

//        while (true) {
//            // TODO: Display the board
//
//            // TODO: Check if the game is over or a draw, and if so display the result and exit
//
//            // TODO: Get the next move from the player and update the game state
//        }
    }
}
