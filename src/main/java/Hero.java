import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Hero{
    Screen screen;
    private int x = 10;
    private int y = 10;
    public Hero(int x, int y) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
    }

    private int getX(){
        return x;
    }
    private int getY(){
        return y;
    }
    private void setX(int x){
        this.x =x;
    }
    private void setY(int y){
        this.y = y;
    }

    public void moveUp(){
        y -= 1;
    }
    public void moveDown(){
        y += 1;
    }
    public void moveRight(){
        x += 1;
    }
    public void moveLeft(){
        x -= 1;
    }

    public void draw() throws IOException{
        screen.startScreen();
        screen.doResizeIfNecessary();
        screen.setCharacter(y , x ,new TextCharacter('H'));
    }




}
