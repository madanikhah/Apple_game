package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Star extends JPanel{

    String name = "star";
    int row, col;

    // public Apple(int row, int col) {
    //   locate(row,col);
    //}
    public void setLocate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getStarRow() {
        return row;
    }

    public int getStarCol() {
        return col;
    }
     public void paint(Graphics g) {

        GameBoard.loadImages();

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
         g2.drawImage(GameBoard.starImage, 80 + getStarRow() * Game.SIZEOFQUADRANGLE, 80 + getStarCol() * Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
     }
}
