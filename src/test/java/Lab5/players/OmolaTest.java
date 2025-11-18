package Lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;
import lab5.players.Omola;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OmolaTest {
    Board game;
    Omola omola;

    @BeforeEach
    public void setUp() {
        game = new Board();
        omola = new Omola();
    }

    @Test
    public void testWin() {
        game.placeX(new Position(Row.Top, Col.Middle));
        game.placeO(new Position(Row.Top, Col.Left));
        game.placeX(new Position(Row.Top, Col.Right));
        game.placeO(new Position(Row.Middle, Col.Left));
        game.placeX(new Position(Row.Middle, Col.Right));
        /*
         * O X X
         * O . X
         * E . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Bottom, Col.Left), omola.pickNextMove(game), "Omola should pick Bottom Left." );

    }

    @Test
    public void testBlock() {
        game.placeX(new Position(Row.Top, Col.Left));
        game.placeO(new Position(Row.Middle, Col.Left));
        game.placeX(new Position(Row.Top, Col.Middle));
        /*
         * X X E
         * O . .
         * . . .
         *  E = Expected
         */

        assertEquals(new Position(Row.Top, Col.Right), omola.pickNextMove(game), "Omola should pick Top Right." );

    }

    @Test
    public void testPriority() {
        game.placeX(new Position(Row.Top, Col.Left));
        game.placeO(new Position(Row.Top, Col.Right));
        game.placeX(new Position(Row.Top, Col.Middle));
        game.placeO(new Position(Row.Bottom, Col.Right));
        game.placeX(new Position(Row.Bottom, Col.Middle));
        /*
         * X X O
         * . . E
         * . X O
         *  E = Expected
         */

        assertEquals(new Position(Row.Middle, Col.Right), omola.pickNextMove(game), "Omola should pick Middle Right, prioritizing winning over blocking the X." );

    }

}
