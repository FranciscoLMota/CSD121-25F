package main.java.lab3.game;

import static main.java.lab3.game.Play.*;

public class Board {
    public Play[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
    };

    public Play getPosition(int row, int col) {
        return board[row][col];
    }

    public void setPosition(int row, int col, Play mark) {
        board[row][col] = mark;
    }

    public boolean checkPosition(int row, int col) {
        return this.getPosition(row, col) == EMPTY;
    }

    /**
     * Checks if there has been a win on the board, should be called after each play.
     *
     */
    public boolean checkWin(Play play) {

        //Checks for row/column wins
        for (int row = 0; row < board.length; row++) {
            boolean rowWin = true;
            boolean colWin = true;

            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != play) rowWin = false;
                if (board[col][row] != play) colWin = false;
            }

            if (rowWin || colWin) {
                return true;
            }

        }

        //Checks for diagonal wins
        //TODO: improve this somehow
        return (board[0][0] == play && board[1][1] == play && board[2][2] == play) ||
                (board[0][2] == play && board[1][1] == play && board[2][0] == play);
    }

    public boolean checkDraw() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if(this.getPosition(row, col) == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }
}