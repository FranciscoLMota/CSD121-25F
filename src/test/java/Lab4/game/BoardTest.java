package Lab4.game;

import lab4.game.Board;
import lab4.game.Col;
import lab4.game.Position;
import lab4.game.Row;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    public void testInitialize() {
       Board game = new Board();
       assertEquals("...\n...\n...\n", game.toString(), "The board should be empty.");
    }

    @Test
    public void testPlaceX() {
        Board game = new Board();
        game.placeX(new Position(Row.from("1"), Col.from("1")));
        assertEquals("X..\n...\n...\n", game.toString(), "Position 1 1 should have an X.");
    }

    @Test
    public void testPlaceO() {
        Board game = new Board();
        game.placeO(new Position(Row.from("1"), Col.from("1")));
        assertEquals("O..\n...\n...\n", game.toString(), "Position 1 1 should have an O.");
    }

    @Test
    public void testStatus() {
        Board game = new Board();
        assertEquals(Board.Status.InProgress, game.getStatus(), "The game should be in progress.");

        Board gameWinX = new Board();
        gameWinX.placeX(new Position(Row.from("1"), Col.from("1")));
        gameWinX.placeX(new Position(Row.from("1"), Col.from("2")));
        gameWinX.placeX(new Position(Row.from("1"), Col.from("3")));
        assertEquals(Board.Status.XWins, gameWinX.getStatus(), "The game should be an X Win.");

        Board gameWinO = new Board();
        gameWinO.placeO(new Position(Row.from("1"), Col.from("1")));
        gameWinO.placeO(new Position(Row.from("2"), Col.from("2")));
        gameWinO.placeO(new Position(Row.from("3"), Col.from("3")));
        assertEquals("O..\n.O.\n..O\n", gameWinO.toString(), "Diagonal should have an O.");
        assertEquals(Board.Status.OWins, gameWinO.getStatus(), "The game should be an O Win.");

        Board gameFull = new Board();

        // OOX
        gameFull.placeO(new Position(Row.from("1"), Col.from("1")));
        gameFull.placeO(new Position(Row.from("1"), Col.from("2")));
        gameFull.placeX(new Position(Row.from("1"), Col.from("3")));

        //XXO
        gameFull.placeX(new Position(Row.from("2"), Col.from("1")));
        gameFull.placeX(new Position(Row.from("2"), Col.from("2")));
        gameFull.placeO(new Position(Row.from("2"), Col.from("3")));

        //OXO
        gameFull.placeO(new Position(Row.from("3"), Col.from("1")));
        gameFull.placeX(new Position(Row.from("3"), Col.from("2")));
        gameFull.placeO(new Position(Row.from("3"), Col.from("3")));
        assertEquals(Board.Status.Draw, gameFull.getStatus(), "Status should be Draw.");

    }

    @Test
    public void TestFull() {
        Board gameFull = new Board();
        assertEquals(false, gameFull.isFull(), "Game should be empty.");

        // OOX
        gameFull.placeO(new Position(Row.from("1"), Col.from("1")));
        gameFull.placeO(new Position(Row.from("1"), Col.from("2")));
        gameFull.placeX(new Position(Row.from("1"), Col.from("3")));

        //XXO
        gameFull.placeX(new Position(Row.from("2"), Col.from("1")));
        gameFull.placeX(new Position(Row.from("2"), Col.from("2")));
        gameFull.placeO(new Position(Row.from("2"), Col.from("3")));

        //OXO
        gameFull.placeO(new Position(Row.from("3"), Col.from("1")));
        gameFull.placeX(new Position(Row.from("3"), Col.from("2")));
        gameFull.placeO(new Position(Row.from("3"), Col.from("3")));
        assertEquals(true, gameFull.isFull(), "Game should be full.");

    }

    @Test
    public void TestisOccupiedAt() {
        Board game = new Board();
        Position pos00 = new Position(Row.from("1"), Col.from("1"));
        assertEquals(false, game.isOccupiedAt(pos00), "Position should be empty.");

        game.placeX(pos00);
        assertEquals(true, game.isOccupiedAt(pos00), "Position should be occupied.");


        Position pos11 = new Position(Row.from("2"), Col.from("2"));
        game.placeO(pos11);
        assertEquals(true, game.isOccupiedAt(pos11), "Position should be occupied.");
    }
}
