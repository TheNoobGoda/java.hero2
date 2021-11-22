import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.w3c.dom.Text;

import java.util.Random;

public class Monster extends Element{
    Position position;
    public Monster(int x, int y){
        super(x,y);
        this.position = new Position(x,y);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#143001"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),"M");
    }

    public Position move(){
        Random random = new Random();
        Position pos;
        int num = random.nextInt(4);
        if (num == 0){
            pos = new Position(position.getX()+1, position.getY() );
        }else if(num == 1){
            pos = new Position(position.getX(), position.getY()+1 );
        }else if(num ==2){
            pos = new Position(position.getX()-1, position.getY() );
        }else{
            pos = new Position(position.getX(), position.getY()-1 );
        }
        return pos;
    }
    public void setPosition(Position position){
        this.position = position;
    }
}
