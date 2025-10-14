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



    public static int[] getMoves(Play player,Board game) {

        IO.println("Player " + player + ", enter your move: ");
        String input = IO.readln();
        var positions = input.trim().replaceAll(" +", " ").split(" ");
        if (positions.length != 2){
            IO.println("Invalid position! ");
            return getMoves(player, game);
        } else {
            int row = getResults(positions[0]);
            int col = getResults(positions[1]);

            if(row == -1 || col == -1) {
                IO.println("Invalid position! ");
                return getMoves(player, game);
            }
            if(game.checkPosition(row,col)) {
                return new int[]{row, col};
            } else{
                IO.println("Invalid position! Position taken! ");
                return getMoves(player, game);
            }
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

    public static void displayWin(Play play){
        IO.println("Player " + play + " won!!!");
    }

    public static void displayDraw(){
        IO.println("It's a draw. Everyone loses");
    }
}

