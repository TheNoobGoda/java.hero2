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
import java.util.Random;

public class Arena {
    int height;
    int width;
    Hero hero ;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;



    public Arena(int width, int height) throws IOException {
        this.hero =new Hero(10,10);
        this.width = width;
        this.height = height;
        this.walls = createWalls(height,width);
        this.coins = createCoins(height,width);
        this.monsters = createMonsters(height,width);
    }

    public void processKey (KeyStroke key) throws IOException {
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
        moveMonsters(monsters);
    }

    private void moveHero (Position position){
        if(canMove(position)){
            this.hero.setPosition(position);
            retrieveCoins(position);
        }
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        this.hero.draw(graphics);
        for (Wall wall : walls){
            wall.draw(graphics);
        }
        for (Coin coin : coins){
            coin.draw(graphics);
        }
        for (Monster monster : monsters){
            monster.draw(graphics);
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

    private boolean canMove(Position position){
        for (Wall wall : walls){
            if(position.getY() == wall.position.getY() && position.getX() == wall.position.getX()){
                return false;
            }
        }
        return true;
    }

    private List<Coin> createCoins(int height, int width){
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();

        for (int i = 0; i<5;i++){
            coins.add(new Coin(random.nextInt(width-2)+1,random.nextInt(height-2)+1 ));
        }
        return coins;
    }

    private List<Monster> createMonsters(int height, int width){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();

        for (int i =0;i<5;i++){
            monsters.add(new Monster(random.nextInt(width-2)+1, random.nextInt(height-2)+1 ));
        }
        return monsters;
    }

    private void moveMonsters(List<Monster> monsters){
        Position pos;
        for (Monster monster : monsters){
            pos =monster.move();
            while (colisionMonsterWall(pos)){
                pos = monster.move();
            }
            monster.setPosition(pos);
        }
    }

    private void retrieveCoins(Position position){
        for (int i = 0; i<coins.size();i++){
            if (position.getX() == coins.get(i).position.getX() && position.getY() == coins.get(i).position.getY()){
                coins.remove(i);
            }
        }
    }
    public boolean endGame(){
        if(coins.size() == 0){
            System.out.println("You win");
            return true;
        }if (colisionMonster()){
            return true;
        }

        return false;
    }
    private boolean colisionMonster(){
        for (Monster monster : monsters){
            if(monster.position.getY() == hero.position.getY() && monster.position.getX() == hero.position.getX()){
                System.out.println("you lose");
                return true;
            }
        }
        return false;
    }

    private boolean colisionMonsterWall(Position position){
            for (Wall wall : walls) {
                if (wall.position.getX() == position.getX() && wall.position.getY() == position.getY()){
                    return true;
                }
            }
        return false;
    }
}
