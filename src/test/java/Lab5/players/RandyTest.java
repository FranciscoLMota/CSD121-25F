package Lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;
import lab5.players.Randy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandyTest {
    Board game;
    Randy randy;

    @BeforeEach
    public void setUp() {
        game = new Board();
        randy = new Randy();
    }

    @Test
    public void testRandy() {
        int[] selections = new int[9];
        int expected = 1111;
        Position[] availablePositions = new Position[] {
                new Position(Row.Top, Col.Left),
                new Position(Row.Top, Col.Middle),
                new Position(Row.Top, Col.Right),
                new Position(Row.Middle, Col.Left),
                new Position(Row.Middle, Col.Middle),
                new Position(Row.Middle, Col.Right),
                new Position(Row.Bottom, Col.Left),
                new Position(Row.Bottom, Col.Middle),
                new Position(Row.Bottom, Col.Right)
        };

        //Run the test 9999 times and save how many times each position was selected
        for (int i = 0; i < 10000; i++) {
            Position selectedPosition = randy.pickNextMove(game);

            for (int idx = 0; idx < availablePositions.length; idx++) {
                if (selectedPosition.equals(availablePositions[idx])) {
                    selections[idx]++;
                    break;
                }
            }
        }

        // Assert the number of each selected position is within the expected 5% threshold
        // 95% = 1055;  100% = 1111; 105% = 1166
        for (int idx = 0; idx < availablePositions.length; idx++) {
            assertTrue(selections[idx] > expected * 0.95 && selections[idx] < expected * 1.05,
                    "Randy is not being equal in their selections");
        }


    }

}
