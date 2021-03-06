import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Hero extends Element{

    Position position;


    public Hero(int x, int y) {
        super(x,y);
        position = new Position(x,y);
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY()-1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY()+1);
    }
    public Position moveLeft() {
        return new Position(position.getX()-1, position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX()+1, position.getY());
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#f44336"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),"H");
        graphics.disableModifiers(SGR.BOLD);
    }

    public void setPosition(Position position) {
        this.position = position;
    }



}
