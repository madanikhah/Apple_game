package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Player extends JPanel {

    int row, col, score = 0, pow = 1, newRow, newCol, newScore = 0, newPow = 0;
    public boolean move = true;
    public boolean negative = false, zeroPow = false;
    GameBoard game;//=new GameBoard();
    Map map[][] = new Map[Game.LENGTH][Game.WIDTH];

    String type;

    public Player(Map[][] map) {
        this.map = map;
    }

    public Map[][] copyMap() {
        return map;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void initLocate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private void addPow() {
        newPow++;
    }

    private void subPow() {
        newPow--;
    }

    private void addScore() {
        newScore++;
    }

    public int getPow() {
        if (pow == 0) {
            if (!zeroPow) {
                zeroPow = true;
            } else {
                pow = 1;
            }
        }
        return pow;
    }

    public boolean checkMove() {
        return move;
    }

    public void move(int newRow, int newCol) {
        map = copyMap();
        move = true;
        this.newRow = newRow;
        this.newCol = newCol;
        System.out.println("Moving to " + newRow + " " + newCol);
        //      checkCompetitor(newRow, newCol);
        System.out.println(map[newRow][newCol].getType());
        if (move) {
            System.out.println("move true1");
        }
        checkDistance(newRow, newCol);
        if (move) {
            System.out.println("move true 2");
        }
        checkCollision(newRow, newCol);
        if (move) {
            System.out.println("move true!");
            map[row][col].setType("null");
            this.row = newRow;
            this.col = newCol;
            map[newRow][newCol].setType("player");
        } else {
            move = true;
        }
    }

    private void checkPlayer() {

    }

    private void checkCompetitor(int newRow, int newCol) {
        if ((getType().equals("bluePlayer") && map[newRow][newCol].getType().equals("redPlayer"))
                || (getType().equals("redPlayer") && map[newRow][newCol].getType().equals("bluePlayer"))) {
            move = false;
        } else {
            move = true;
        }
    }

    private void checkDistance(int newRow, int newCol) {
        if (move && (newRow == row || newCol == col)) {
            int checkRow = newRow - row;
            int checkCol = newCol - col;
            if (checkRow < 0) {
                negative = true;
                checkRow = checkRow * (-1);
                if (checkRow > getPow()) {
                    move = false;
                }
            } else if (checkCol < 0) {
                negative = true;
                checkCol = checkCol * (-1);
                if (checkCol > getPow()) {
                    move = false;
                }
            } else {
                negative = false;
                if (checkRow > getPow() || checkCol > getPow()) {
                    move = false;
                }
            }
        } else {
            move = false;
        }
    }

    public void checkCollision(int newRow, int newCol) {

        if (move && !negative) {
            firstLoop:
            {
                for (int i = col + 1; i <= newCol; i++) {
                    if (map[row][i].getType().equals("wall")) {
                        move = false;
                        break firstLoop;
                    } else if (map[row][i].getType().equals("apple")) {
                        addPow();
                        GameBoard.starCount--;
                        map[row][i].setType("null");
                    } else if (map[row][i].getType().equals("mushroom")) {
                        subPow();
                        map[row][i].setType("null");
                    } else if (map[row][i].getType().equals("star")) {
                        addScore();
                        map[row][i].setType("null");
                    }
                }
                score += newScore;
                pow += newPow;
            }
            firstLoop:
            {
                for (int i = row + 1; i <= newRow; i++) {
                    if (map[i][col].getType().equals("wall")) {
                        move = false;
                        break firstLoop;
                    } else if (map[i][col].getType().equals("apple")) {
                        addPow();
                        map[i][col].setType("null");
                    } else if (map[i][col].getType().equals("mushroom")) {
                        subPow();
                        map[i][col].setType("null");
                    } else if (map[i][col].getType().equals("star")) {
                        addScore();
                        map[i][col].setType("null");
                    }
                }
                score += newScore;
                pow += newPow;
            }
        } else if (move && negative) {
            firstLoop:
            {
                for (int i = col - 1; i >= newCol; i--) {
                    if (map[row][i].getType().equals("wall")) {
                        move = false;
                        break firstLoop;
                    } else if (map[row][i].getType().equals("apple")) {
                        addPow();
                        map[row][i].setType("null");
                    } else if (map[row][i].getType().equals("mushroom")) {
                        subPow();
                        map[row][i].setType("null");
                    } else if (map[row][i].getType().equals("star")) {
                        addScore();
                        map[row][i].setType("null");
                    }
                }
                score += newScore;
                pow += newPow;
            }
            firstLoop:
            {
                for (int i = row - 1; i >= newRow; i--) {
                    if (map[i][col].getType().equals("wall")) {
                        move = false;
                        break firstLoop;
                    } else if (map[i][col].getType().equals("apple")) {
                        addPow();
                        map[i][col].setType("null");
                    } else if (map[i][col].getType().equals("mushroom")) {
                        subPow();
                        map[i][col].setType("null");
                    } else if (map[i][col].getType().equals("star")) {
                        addScore();
                        map[i][col].setType("null");
                    }
                }
                score += newScore;
                pow += newPow;
            }
        }
    }

    public void bluePaint(Graphics g) {
        game.loadImages();
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(game.bluePlayerImage, 80 + row * Game.SIZEOFQUADRANGLE, 80 + col * Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
    }

    public void redPaint(Graphics g) {
        game.loadImages();
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // if (getType().equals("bluePlayer")) {
        // g2.drawImage(game.bluePlayerImage, 80 + row * Game.SIZEOFQUADRANGLE, 80 + col * Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
        //}
        g2.drawImage(game.redPlayerImage, 80 + row * Game.SIZEOFQUADRANGLE, 80 + col * Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
    }
}
