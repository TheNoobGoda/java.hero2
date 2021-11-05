import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.security.Key;

public class Game {
    Screen screen;
    /*
    private int x =10;
    private int y =10;
    */

    Hero hero = new Hero(10,10);

    public Game() throws IOException {

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
        hero.draw();
        screen.refresh();





    }
    public void run() throws IOException{
        draw();

        while (true){
            KeyStroke key = screen.readInput();
            processKey(key);

            screen.clear();
            hero.draw();
            screen.refresh();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.close();
            }
            if (key.getKeyType() == KeyType.EOF){
                break;
            }
        }
    }



    private void processKey (KeyStroke key) throws IOException {
        System.out.println(key);
        switch (key.getKeyType()){
            case ArrowUp:
                hero.moveUp();
                break;
            case ArrowDown:
                hero.moveDown();
                break;
            case ArrowLeft:
                hero.moveLeft();
                break;
            case ArrowRight:
                hero.moveRight();
                break;

        }
    }

}

