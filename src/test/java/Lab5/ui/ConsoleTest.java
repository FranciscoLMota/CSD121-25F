package Lab5.ui;

import lab5.game.PlayerToken;
import lab5.players.*;
import lab5.ui.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleTest {
    InputStream sysInBackup;

    @BeforeEach
    public void setUp() {
        sysInBackup = System.in;
    }

    @AfterEach
    public void cleanup() {
        System.setIn(sysInBackup);
    }

    @Test
    public void testHumanPlayer() {
        ByteArrayInputStream in = new ByteArrayInputStream("John".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        HumanPlayer hplayer  = new HumanPlayer("humanPlayer");

        assertEquals(hplayer.getClass(), player.getClass(), "Should return a HumanPlayer." );
        assertEquals("John", player.getName(), "Should return the name John." );
    }

    @Test
    public void testHumanPlayerCPUName() {
        ByteArrayInputStream in = new ByteArrayInputStream("Circe".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        HumanPlayer hplayer  = new HumanPlayer("humanPlayer");

        assertEquals(hplayer.getClass(), player.getClass(), "Should return a HumanPlayer." );
        assertEquals("Circe", player.getName(), "Should return the name Circe." );
    }

    @Test
    public void testHumanPlayerNumbersName() {
        ByteArrayInputStream in = new ByteArrayInputStream("1234".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        HumanPlayer hplayer  = new HumanPlayer("humanPlayer");

        assertEquals(hplayer.getClass(), player.getClass(), "Should return a HumanPlayer." );
        assertEquals("1234", player.getName(), "Should return the name 1234." );
    }

    @Test
    public void testHumanPlayeSpecialCharactersName() {
        ByteArrayInputStream in = new ByteArrayInputStream("!@#^&%".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        HumanPlayer hplayer  = new HumanPlayer("humanPlayer");

        assertEquals(hplayer.getClass(), player.getClass(), "Should return a HumanPlayer." );
        assertEquals("!@#^&%", player.getName(), "Should return the name !@#^&%." );
    }

    @Test
    public void testCirce() {
        ByteArrayInputStream in = new ByteArrayInputStream("@circe".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        Circe circe = new Circe();

        assertEquals(circe.getClass(), player.getClass(), "Should return a Circe Class." );
        assertEquals("Circe", player.getName(), "Should return the name Circe." );
    }

    @Test
    public void testLinus() {
        ByteArrayInputStream in = new ByteArrayInputStream("@Linus".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        Linus linus = new Linus();

        assertEquals(linus.getClass(), player.getClass(), "Should return a Linus Class." );
        assertEquals("Linus", player.getName(), "Should return the name Linus." );
    }

    @Test
    public void testRandy() {
        ByteArrayInputStream in = new ByteArrayInputStream("@RANDY".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        Randy randy = new Randy();

        assertEquals(randy.getClass(), player.getClass(), "Should return a Randy Class." );
        assertEquals("Randy", player.getName(), "Should return the name Randy." );
    }

    @Test
    public void testOmola() {
        ByteArrayInputStream in = new ByteArrayInputStream("@oMoLa".getBytes());
        System.setIn(in);

        var player = Console.promptForPlayer(PlayerToken.X);
        Omola omola = new Omola();

        assertEquals(omola.getClass(), player.getClass(), "Should return a Omola Class." );
        assertEquals("Omola", player.getName(), "Should return the name Omola." );
    }

}
