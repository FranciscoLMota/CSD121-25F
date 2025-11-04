package Lab4.game;
import lab4.game.Col;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ColTest {

    @Test
    public void testLowerCase() {
        assertEquals(Col.Left, Col.from("l"), "Incorrect Column Value" );
        assertEquals(Col.Middle, Col.from("m"), "Incorrect Column Value" );
        assertEquals(Col.Middle, Col.from("c"), "Incorrect Column Value" );
        assertEquals(Col.Right, Col.from("r"), "Incorrect Column Value" );
    }

    @Test
    public void testUpperCase() {
        assertEquals(Col.Left, Col.from("L"), "Incorrect Column Value" );
        assertEquals(Col.Middle, Col.from("M"), "Incorrect Column Value" );
        assertEquals(Col.Middle, Col.from("C"), "Incorrect Column Value" );
        assertEquals(Col.Right, Col.from("R"), "Incorrect Column Value" );
    }

    @Test
    public void testNumbers() {
        assertEquals(Col.Left, Col.from("1"), "Incorrect Column Value" );
        assertEquals(Col.Middle, Col.from("2"), "Incorrect Column Value" );
        assertEquals(Col.Right, Col.from("3"), "Incorrect Column Value" );
    }

    @Test
    public void testError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Col.from("6");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Col.from("Middle");
        });
    }

}
