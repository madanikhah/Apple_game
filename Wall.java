package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Wall extends JPanel{
     String name = "wall";
    int row, col;
    
    public void setLocate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getWallRow() {
        return row;
    }

    public int getWallCol() {
        return col;
    }
     public void paint(Graphics g) {

        GameBoard.loadImages();

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
         g2.drawImage(GameBoard.wallImage, 80 + getWallRow() * Game.SIZEOFQUADRANGLE, 80 + getWallCol() * Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
     }
}
