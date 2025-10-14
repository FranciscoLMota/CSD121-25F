package lab3;

import lab3.game.*;
import lab3.ui.Console;

public class Main {

    static void main(String[] args) {


        // Initialize a TicTacToe game with a 3x3 board and two players
        var game = new Board();
        Console.displayBoard(game);
        Play currentPlayer = Play.PX;
        int[] currentPosition = {-1,-1};



        while (true) {
            // TODO: Display the board
              Console.displayBoard(game);
            // TODO: Check if the game is over or a draw, and if so display the result and exit


            // TODO: Get the next move from the player and update the game state

            currentPosition = Console.getMoves(currentPlayer);

            game.setPosition(currentPosition[0], currentPosition[1],  currentPlayer);
           currentPlayer = switchPlayer(currentPlayer);

        }
    }

    public static Play switchPlayer(Play currentPlayer){
        return currentPlayer == Play.PX ? Play.PO : Play.PX;

    }
}
