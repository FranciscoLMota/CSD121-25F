package lab5.players;

import lab5.game.Board;
import lab5.game.PlayerToken;
import lab5.game.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a player in the game.
 * <p>
 */
public class Omola extends Player {

    public Omola() {
        super("Omola");
    }

    /**
     * Selects the position based on the possibility of winning and blocking their opponent
     *
     * @param currentBoard The current state of the game board
     * @return The position on the board where Omola will place their token
     */
    public Position pickNextMove(Board currentBoard) {
        List<Position> availablePositions = currentBoard.getEmptyCells();
        List<Position> winningPositions = new ArrayList<>();
        List<Position> blockingPositions = new ArrayList<>();
        PlayerToken player = currentBoard.getNextTurnToken();
        Random random = new Random();

        for (Position position : availablePositions) {

            //If player is X, adds the moves that makes X win to the winning Positions
            // and moves that makes O win to blocking positions
            if (player == PlayerToken.X) {
                if (checkWinX(currentBoard, position)) {
                    winningPositions.add(position);
                }

                if (checkWinO(currentBoard, position)) {
                    blockingPositions.add(position);
                }

            //If the player is O, does the opposite
            } else if (player == PlayerToken.O) {

                if (checkWinO(currentBoard, position)) {
                    winningPositions.add(position);
                }

                if (checkWinX(currentBoard, position)) {
                    blockingPositions.add(position);
                }
            }

        }

        //If there's a winning position, prioritize winning
        if (!winningPositions.isEmpty()) {
            return winningPositions.get(random.nextInt(winningPositions.size()));

        //If it can't win, but can block the other from winning, blocks the opponent
        } else if (!blockingPositions.isEmpty()) {
            return blockingPositions.get(random.nextInt(blockingPositions.size()));

        //If no one won, just selects a random position
        } else {
            return availablePositions.get(random.nextInt(availablePositions.size()));
        }
    }

    private Boolean checkWinX(Board currentBoard, Position position) {
        Board simulatedBoard = new Board(currentBoard);
        simulatedBoard.placeX(position);

        return simulatedBoard.getWinner() == PlayerToken.X;
    }

    private Boolean checkWinO(Board currentBoard, Position position) {
        Board simulatedBoard = new Board(currentBoard);
        simulatedBoard.placeO(position);

        return simulatedBoard.getWinner() == PlayerToken.O;
    }

}
