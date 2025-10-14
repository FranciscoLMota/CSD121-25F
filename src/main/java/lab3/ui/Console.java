package lab3.ui;

import lab3.game.Board;
import lab3.game.Play;

public class Console{
    public static void displayBoard(Board game){
        IO.println("Current board: ");
        for(int row = 0; row < game.board.length; row++){
            for(int col = 0; col < game.board[row].length; col++){
                IO.print(game.getPosition(row,col) + " ");
                if(col < game.board[row].length-1) IO.print(" | ");
            }
            IO.println("");

        }
    }

    public static int[] getMoves(Play player) {

        IO.println("Player " + player + ", enter your move: ");
        String input = IO.readln();
        var positions = input.split(" ");
        if (positions.length != 2){
            IO.println("Invalid position! ");
            return getMoves(player);
        } else {
            int row = getResults(positions[0]);
            int col = getResults(positions[1]);

            if(row == -1 || col == -1) {
                IO.println("Invalid position! ");
                return getMoves(player);
            }
            return new int[]{row, col};
        }
    }

    public static int getResults(String input) {
        return switch (input.toUpperCase()) {
            case "0", "T", "L" -> 0;
            case "1", "M" -> 1;
            case "2", "B", "R" -> 2;
            default -> -1;
        };
    }
}
