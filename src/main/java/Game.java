import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;
    private int x =10;
    private int y =10;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException{
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        screen.clear();
        screen.setCharacter(x , y ,new TextCharacter('x'));
        screen.refresh();

        KeyStroke key = screen.readInput();

        processKey(key);

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'w'){
            System.out.println("w is pressed");
        }
    }
    public void run() throws IOException{
        draw();
    }



    private void processKey (KeyStroke key) {
        System.out.println(key);
    }
}
