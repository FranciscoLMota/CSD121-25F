package lab3;

import javafx.scene.layout.*;
import lab3.game.*;
import lab3.ui.Console;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;


public class Main extends Application {

    private Label turnLabel;
    private Board game;
    private Play currentPlayer;


    @Override
    public void start(Stage stage) {
        game = new Board();
        // Create label for the turn
        turnLabel = new Label("[Player X's turn]");

        //Sets the initial player as X
        currentPlayer = Play.PX;

        // Create buttons
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0));
        grid.setHgap(0);
        grid.setVgap(0);

        // Add the positions
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setText(game.getPosition(row, col).toString()); // Sets the text as the position on the board
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setStyle("-fx-font-size: 24; -fx-pref-width: 100; -fx-pref-height: 100;");


                int finalRow = row;
                int finalCol = col;
                button.setOnAction(_ -> handleMove(finalRow, finalCol, button));
                grid.add(button, col, row);
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);
            }
        }

        // Position the label
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(turnLabel, grid);
        VBox.setVgrow(grid, Priority.ALWAYS);

        // Create Scene
        Scene scene = new Scene(root, 300, 350);
        stage.setScene(scene);
        stage.setTitle("TicTacToe");
        stage.show();
    }

    private void handleMove(int row, int col, Button button) {
        IO.println("Clicked button at: (" + row + ", " + col + ")");
        if (game.checkPosition(row, col)) {
            game.setPosition(row, col, currentPlayer);
            button.setText(game.getPosition(row, col).toString());

            if (currentPlayer == Play.PX) {
                button.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

            } else {
                button.setTextFill(Color.WHITE);
                button.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
            }

            //Checks if there's a win and display message accordingly
            if (game.checkWin(currentPlayer)) {
                turnLabel.setText("Player " + currentPlayer + " Won!!!");

            } else if (game.checkDraw()) {
                //Checks for a draw and display message accordingly
                turnLabel.setText("It's a Draw... :(");

            } else {
                switchPlayer();
                turnLabel.setText("[Player " + currentPlayer + "'s turn]");
            }


        } else {
            turnLabel.setText("[Player" + currentPlayer + "'s turn] Invalid position");
        }
    }

    static void main() {
        launch();

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

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Play.PX ? Play.PO : Play.PX);

    }
}



