package Lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;
import lab5.players.Circe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CirceTest {
    Board game;
    Circe circe;

    @BeforeEach
    public void setUp() {
        game = new Board();
        circe = new Circe();
    }

    @Test
    public void testFirstChoice() {
        /*
         * . . .
         * . E .
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Middle, Col.Middle), circe.pickNextMove(game), "Circe should pick Middle Middle." );
    }

    @Test
    public void testSecondChoice() {
        game.placeX(new Position(Row.Middle, Col.Middle));
        /*
         * . E .
         * . X .
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Top, Col.Middle), circe.pickNextMove(game), "Circe should pick Top Middle." );
    }

    @Test
    public void testThirdChoice() {
        game.placeX(new Position(Row.Middle, Col.Middle));
        game.placeO(new Position(Row.Top, Col.Middle));
        game.placeX(new Position(Row.Top, Col.Right));
        /*
         * . O X
         * . X E
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Middle, Col.Right), circe.pickNextMove(game), "Circe should pick Middle Right." );
    }

    @Test
    public void testFourthChoice() {
        game.placeX(new Position(Row.Middle, Col.Middle));
        game.placeO(new Position(Row.Top, Col.Middle));
        game.placeX(new Position(Row.Top, Col.Right));
        game.placeO(new Position(Row.Middle, Col.Right));
        game.placeX(new Position(Row.Bottom, Col.Right));
        /*
         * . O X
         * . X O
         * . E X
         *  E = Expected
         */

        assertEquals(new Position(Row.Bottom, Col.Middle), circe.pickNextMove(game), "Circe should pick Bottom Middle." );
    }

    @Test
    public void testFifthChoice() {
        game.placeX(new Position(Row.Middle, Col.Middle));
        game.placeO(new Position(Row.Top, Col.Middle));
        game.placeX(new Position(Row.Top, Col.Right));
        game.placeO(new Position(Row.Middle, Col.Right));
        game.placeX(new Position(Row.Bottom, Col.Right));
        game.placeO(new Position(Row.Bottom, Col.Middle));
        game.placeX(new Position(Row.Middle, Col.Left));
        game.placeO(new Position(Row.Bottom, Col.Left));
        /*
         * E O X
         * X X O
         * O O X
         *  E = Expected
         */

        assertEquals(new Position(Row.Top, Col.Left), circe.pickNextMove(game), "Circe should pick Top Left." );
    }

}
