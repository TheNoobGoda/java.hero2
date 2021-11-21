import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {
    Position position;

    public Coin(int x, int y){
        super(x,y);
        this.position = new Position(x,y);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#f1c232"));
        graphics.putString(new TerminalPosition(position.getX(),position.getY()),"C");
    }
}
