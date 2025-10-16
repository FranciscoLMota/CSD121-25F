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

    public static void main(String[] args) {
        launch();
    }

    public static Play switchPlayer(Play currentPlayer){
        return currentPlayer == Play.PX ? Play.PO : Play.PX;

    }
}

//public class Main {


//    static void main(String[] args) {
//
//
//        // Initialize a TicTacToe game with a 3x3 board and two players
//        var game = new Board();
//
//        IO.println("What's the name of the first player (X):");
//        var px = IO.readln();
//        IO.println("What's the name of the second player (O):");
//        var ox = IO.readln();
//
//
//        Play currentPlayer = Play.PX;
//        int[] currentPosition = {-1,-1};
//
//
//
//        while (true) {
//           Console.displayBoard(game); //Show board
//
//            currentPosition = Console.getMoves(currentPlayer, game);
//            game.setPosition(currentPosition[0], currentPosition[1],  currentPlayer);
//
//            if(game.checkWin(currentPlayer)){
//                Console.displayBoard(game);
//                Console.displayWin(currentPlayer);
//                System.exit(0);
//            }
//
//            if(game.checkDraw()) {
//                Console.displayBoard(game);
//                Console.displayDraw();
//                System.exit(0);
//            }
//
//            currentPlayer = switchPlayer(currentPlayer); //switch PX to PO
//
//        }
//    }


//}
