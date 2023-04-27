package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Mushroom extends JPanel{

    String name = "mushroom";
    int row, col;

    public void setLocate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getMushroomRow() {
        return row;
    }

    public int getMushroomCol() {
        return col;
    }
     public void paint(Graphics g) {

        GameBoard.loadImages();

        super.paint(g);
         Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
         g2.drawImage(GameBoard.mushroomImage, 80 + getMushroomRow() * Game.SIZEOFQUADRANGLE, 80 + getMushroomCol() * Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
     }
}
