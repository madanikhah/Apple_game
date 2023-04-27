package game;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Apple extends JPanel{

    String name = "apple";
    public int row=1, col=1;

    // public Apple(int row, int col) {
    //   locate(row,col);
    //}
    public void setLocate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getAppleRow() {
        return row;
    }

    public int getAppleCol() {
        return col;
    }
    
     public void paint(Graphics g) {

        GameBoard.loadImages();

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
         g2.drawImage(GameBoard.appleImage, 80 + getAppleRow() * Game.SIZEOFQUADRANGLE,
                 80 + getAppleCol() * Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
     }
}
