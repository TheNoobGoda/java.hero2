import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    int height;
    int width;
    Hero hero ;
    private List<Wall> walls;
    Wall wall;



    public Arena(int width, int height) throws IOException {
        hero =new Hero(10,10);
        this.width = width;
        this.height = height;
        this.walls = createWalls(height,width);
        this.wall = new Wall(15,15);
    }

    public void processKey (KeyStroke key) throws IOException {
        System.out.println(key);
        switch (key.getKeyType()){
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;

        }
    }

    private void moveHero (Position position){
        if(canHeroMove(position)){
            hero.setPosition(position);
        }

    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        this.hero.draw(graphics);
        //this.wall.draw(graphics);
        for (Wall wall : walls){
            wall.draw(graphics);
        }
    }


    private List<Wall> createWalls(int height, int width){
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c< width; c++){
            walls.add(new Wall(c,0));
            walls.add(new Wall(c, height-1));
        }

        for (int r=1; r < height ; r++){
            walls.add(new Wall(0,r));
            walls.add(new Wall(width-1, r));
        }
        return walls;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (this == null){
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }

        Position position = (Position) o;
        return hero.position.getX() == position.getX() && hero.position.getY() == position.getY();

    }

    private boolean canHeroMove(Position position){
        System.out.println("testing");
        for (Wall wall : walls){
            if(wall.getPosition().equals(position)){
                System.out.println("colision");
                return false;
            }
        }
        return true;
    }

}
