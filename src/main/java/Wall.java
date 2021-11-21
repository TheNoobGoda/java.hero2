import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Wall extends Element {
    Position position;

    public Wall(int x, int y){
        super(x,y);
        this.position = new Position(x,y);

    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),"#");
    }

    public Position getPosition(){
        return position;
    }
}

