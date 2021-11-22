import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.StyleSet;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.security.Key;

public class Game {
    private Screen screen;
    private Arena arena;
    private Terminal terminal;

    public Game() {

        try {
            this.arena = new Arena(100,30);
            TerminalSize terminalSize = new TerminalSize(100,30);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);

            screen.startScreen();
            screen.doResizeIfNecessary();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void draw() throws IOException{
        screen.clear();
        this.arena.draw(this.screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException{
        while (true){
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);

            if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') || arena.endGame()){
                screen.close();
            }
            if (key.getKeyType() == KeyType.EOF){
                break;
            }
        }
    }



    private void processKey (KeyStroke key) throws IOException {
        arena.processKey(key);
    }
}

