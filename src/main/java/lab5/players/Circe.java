package lab5.players;

import lab5.game.Col;
import lab5.game.Board;
import lab5.game.Position;
import lab5.game.Row;

import java.util.List;

/**
 * Represents a player in the game.
 * <p>
 *
 */
public class Circe extends Player {

    public Circe() {
        super("Circe");
    }

    /**
     * Picks available positions in the following order: very middle of the board; then clockwise starting at the top middle.
     *
     * @param currentBoard The current state of the game board
     * @return The position on the board where Circe will place their token
     */
    public Position pickNextMove(Board currentBoard) {
        List<Position> availablePositions = currentBoard.getEmptyCells();

        //Middle of the board
        if(availablePositions.contains(new Position(Row.Middle, Col.Middle))) {
            return new Position(Row.Middle, Col.Middle);
        }

        //Top Middle
        if(availablePositions.contains(new Position(Row.Top, Col.Middle))) {
            return new Position(Row.Top, Col.Middle);
        }

        //Right Col
        for (int r = 1; r < 4; r++) {
            Row currentRow = Row.from(String.valueOf(r));
            if(availablePositions.contains(new Position(currentRow, Col.Right))) {
                return new Position(currentRow, Col.Right);
            }
        }

        //Bottom Middle
        if(availablePositions.contains(new Position(Row.Bottom, Col.Middle))) {
            return new Position(Row.Bottom, Col.Middle);
        }

        //Left Col UpsideDown
        for (int r = 3; r > 0; r--) {
            Row currentRow = Row.from(String.valueOf(r));
            if(availablePositions.contains(new Position(currentRow, Col.Left))) {
                return new Position(currentRow, Col.Left);
            }
        }

        return pickNextMove(currentBoard);
    }
}
