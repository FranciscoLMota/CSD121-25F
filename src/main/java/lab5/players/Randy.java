package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

import java.util.List;
import java.util.Random;

/**
 * Represents a player in the game.
 * <p>
 */
public class Randy extends Player {

    public Randy() {
        super("Randy");
    }

    /**
     * Randomly selects one of the available places to add their token.
     *
     * @param currentBoard The current state of the game board
     * @return The position on the board where Randy will place their token
     */
    public Position pickNextMove(Board currentBoard) {
        List<Position> availablePositions = currentBoard.getEmptyCells();
        Random random = new Random();

        return availablePositions.get(random.nextInt(availablePositions.size()));
    }
}
