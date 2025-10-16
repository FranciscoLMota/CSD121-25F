package lab3;

import lab3.game.*;
import lab3.ui.Console;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("JavaFX is working!");
        Scene scene = new Scene(label, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Hello JavaFX");
        stage.show();
    }

    static void main() {
        launch();
//        // Initialize a TicTacToe game with a 3x3 board and two players
//        var game = new Board();
//
//        //Sets the initial player as X
//        Play currentPlayer = Play.PX;
//        int[] currentPosition;
//
//
//
//        while (true) {
//            //Shows the current Board
//            Console.displayBoard(game);
//
//            // Requests the place where the player wants to leave his mark
//            // and validates the position
//            currentPosition = Console.getMoves(currentPlayer, game);
//
//            //Sets the selected position to the board
//            game.setPosition(currentPosition[0], currentPosition[1], currentPlayer);
//
//            //Checks if there's a win and display message accordingly
//            if (game.checkWin(currentPlayer)) {
//                Console.displayBoard(game);
//                Console.displayWin(currentPlayer);
//                System.exit(0);
//            }
//
//            //Checks for a draw and display message accordingly
//            if (game.checkDraw()) {
//                Console.displayBoard(game);
//                Console.displayDraw();
//                System.exit(0);
//            }
//
//            //Switches between the players
//            currentPlayer = switchPlayer(currentPlayer); //switch PX to PO
//
//        }
    }

    public static Play switchPlayer(Play currentPlayer){
        return currentPlayer == Play.PX ? Play.PO : Play.PX;

    }
}



