package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;

import java.util.List;

/**
 * Represents a player in the game.
 * <p>
 *
 */
public class Linus extends Player {

    public Linus() {
        super("Linus");
    }

    /**
     * Picks the next available position, starting with the top left position, and working right then down one row at a time.
     *
     * @param currentBoard The current state of the game board
     * @return The position on the board where Linus will place their token
     */
    public Position pickNextMove(Board currentBoard) {
        List<Position> availablePositions = currentBoard.getEmptyCells();

        for (int r = 1; r < 4; r++) {
            Row currentRow = Row.from(String.valueOf(r));
            for (int c = 1; c < 4; c++) {
                Col currentCol = Col.from(String.valueOf(c));

                if (availablePositions.contains(new Position(currentRow, currentCol))) {
                    return new Position(currentRow, currentCol);
                }
            }

        }

        return pickNextMove(currentBoard);
    }
}
