package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Map extends JPanel {

    GameBoard game;
    public static boolean sw = false;
    private boolean appleSw = false, mushroomSw = false, starSw = false, wallSw = false, bluePlayerSw = false, redPlayerSw = false;
    int x = 1, y = 1;
    public static String type = "null";
    public boolean setType = false;
    Apple apple = new Apple();
    Mushroom mushroom = new Mushroom();
    Star star = new Star();
    Wall wall = new Wall();

//    public Map(GameBoard game) {
    //      this.game = game;
    //}
   
    public void setFull(boolean setType) {
        this.setType = setType;
    }

    public boolean full() {
        return setType;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
        if (type.equals("apple")) {
            sw = true;
            appleSw = true;
            mushroomSw = false;
            starSw = false;
            wallSw = false;
            apple = new Apple();
            apple.setLocate(x, y);

        } else if (type.equals("mushroom")) {
            sw = true;
            appleSw = false;
            mushroomSw = true;
            starSw = false;
            wallSw = false;
            mushroom = new Mushroom();
            mushroom.setLocate(getX(), getY());
            System.out.println("trueMashroom");
        } else if (type.equals("star")) {
            sw = true;
            appleSw = false;
            mushroomSw = false;
            starSw = true;
            wallSw = false;
            star = new Star();
            star.setLocate(getX(), getY());
        } else if (type.equals("wall")) {
            sw = true;
            appleSw = false;
            mushroomSw = false;
            starSw = false;
            wallSw = true;
            wall = new Wall();
            wall.setLocate(x, y);
        }
        else if(type.equals("null")){
          //  sw = false;
            appleSw = false;
            mushroomSw = false;
            starSw = false;
            wallSw = false;
        }
    }

    public void paint(Graphics g) {

        GameBoard.loadImages();

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (appleSw) {
            apple.paint(g2);
        } else if (mushroomSw) {
            mushroom.paint(g2);
        } else if (starSw) {
            star.paint(g2);
        } else if (wallSw) {
            wall.paint(g2);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static String getType() {
        return type;
    }

    public void checkMove() {

    }
}
