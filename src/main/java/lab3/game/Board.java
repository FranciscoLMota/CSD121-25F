package main.java.lab3.game;

import static main.java.lab3.game.Play.*;

public class Board {
    // TODO: encapsulate the representation of the tictactoe board and provide instance methods to access and update it

    public Play[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    public Play getPosition(int row, int col) {
        return board[row][col];
    }

    public void setPosition(int row, int col, Play mark) {
        board[row][col] = mark;
    }

}
