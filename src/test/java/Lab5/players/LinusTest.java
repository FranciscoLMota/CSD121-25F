package Lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;
import lab5.players.Linus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinusTest {
    Board game;
    Linus linus;

    @BeforeEach
    public void setUp() {
        game = new Board();
        linus = new Linus();
    }

    @Test
    public void testFirstChoice() {
        /*
         * E . .
         * . . .
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Top, Col.Left), linus.pickNextMove(game), "Linus should pick Top Left." );
    }

    @Test
    public void testSecondChoice() {
        game.placeX(new Position(Row.Top, Col.Left));
        /*
         * X E .
         * . . .
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Top, Col.Middle), linus.pickNextMove(game), "Linus should pick Top Middle." );
    }

    @Test
    public void testThirdChoice() {
        game.placeX(new Position(Row.Top, Col.Left));
        game.placeO(new Position(Row.Top, Col.Middle));
        game.placeX(new Position(Row.Top, Col.Right));
        /*
         * X O X
         * E . .
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Middle, Col.Left), linus.pickNextMove(game), "Linus should pick Middle Left." );
    }

    @Test
    public void testFourthChoice() {
        game.placeX(new Position(Row.Top, Col.Left));
        game.placeO(new Position(Row.Top, Col.Middle));
        game.placeX(new Position(Row.Top, Col.Right));
        game.placeO(new Position(Row.Middle, Col.Left));
        game.placeX(new Position(Row.Middle, Col.Middle));
        /*
         * X O X
         * O X E
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Middle, Col.Right), linus.pickNextMove(game), "Linus should pick Bottom Right." );
    }

    @Test
    public void testFifthChoice() {
        game.placeX(new Position(Row.Top, Col.Left));
        game.placeO(new Position(Row.Top, Col.Middle));
        game.placeX(new Position(Row.Top, Col.Right));
        game.placeO(new Position(Row.Middle, Col.Left));
        game.placeX(new Position(Row.Middle, Col.Middle));
        game.placeO(new Position(Row.Middle, Col.Right));
        game.placeX(new Position(Row.Bottom, Col.Middle));

        /*
         * X O X
         * O X O
         * E X .
         *  E = Expected
         */

        assertEquals(new Position(Row.Bottom, Col.Left), linus.pickNextMove(game), "Linus should pick Bottom Left." );
    }

}
