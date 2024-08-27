
import java.io.IOException;

import AdventureModel.AdventureGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAdventureTest {
    @Test
    void getCommandsTest() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        String commands = game.player.getCurrentRoom().getCommands();
        assertEquals("WEST, NORTH, IN, SOUTH, DOWN", commands);
    }

    @Test
    void getObjectString() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        String objects = game.player.getCurrentRoom().getObjectString();
        assertEquals("a water bird", objects);
    }

    @Test
    void toggleAccessibilityTest() {
        AdventureGame game = new AdventureGame("TinyGame");
        assertFalse(game.getAccessibility());
        game.toggleAccessibility();
        assertTrue(game.getAccessibility());
    }

    @Test
    void hintGiverTest(){
        AdventureGame game = new AdventureGame("TinyGame");
        game.player.getCurrentRoom().getNPC().interact(game.player);
        assertEquals(1, game.player.getAllHints().size());
    }

    @Test
    void itemGiverTest(){
        AdventureGame game = new AdventureGame("TinyGame");
        game.movePlayer("DOWN");
        game.movePlayer("DOWN");
        game.player.getCurrentRoom().getNPC().interact(game.player);
        assertEquals(1, game.player.inventory.size());
    }

    @Test
    void shopOwnerTest(){
        AdventureGame game = new AdventureGame("TinyGame");
        //TODO: continue after full implementation of the shop
    }
}
