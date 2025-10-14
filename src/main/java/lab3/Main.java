package lab3;

import lab3.game.*;
import lab3.ui.Console;

public class Main {

    static void main(String[] args) {


        // Initialize a TicTacToe game with a 3x3 board and two players
        var game = new Board();
        Play currentPlayer = Play.PX;
        int[] currentPosition = {-1,-1};



        while (true) {
           Console.displayBoard(game); //Show board

            currentPosition = Console.getMoves(currentPlayer, game);
            game.setPosition(currentPosition[0], currentPosition[1],  currentPlayer);

            if(game.checkWin(currentPlayer)){
                Console.displayBoard(game);
                Console.displayWin(currentPlayer);
                System.exit(0);
            }

            if(game.checkDraw()) {
                Console.displayBoard(game);
                Console.displayDraw();
                System.exit(0);
            }

            currentPlayer = switchPlayer(currentPlayer); //switch PX to PO

        }
    }

    public static Play switchPlayer(Play currentPlayer){
        return currentPlayer == Play.PX ? Play.PO : Play.PX;

    }
}
