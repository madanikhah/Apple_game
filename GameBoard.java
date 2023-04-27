package game;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {

    private int appleCount = 0, mushroomCount = 0, wallCount = 0, count = 0;
    public static int starCount = 0;
    private boolean bluePlayerSw = false;
    private static boolean inApple = false;
    private static boolean inMushroom = false;
    private static boolean inStar = false;
    private static boolean inWall = false;

    public static boolean inBluePlayer = false;
    public static boolean inRedPlayer = false;
    int row, col;

    public Map map[][] = new Map[Game.LENGTH][Game.WIDTH];
    public Player bluePlayer;
    public Player redPlayer;

    public GameBoard() {
        checkBoard();
    }

    public void newMap() {
        for (int i = 0; i < Game.WIDTH; i++) {
            for (int j = 0; j < Game.LENGTH; j++) {
                map[j][i] = new Map();
                map[j][i].setPosition(j, i);
            }
        }
    }

    public Map[][] copyMap() {
        return map;
    }

    private void checkBoard() {
        newMap();
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
                if (Game.inSetBoard && me.getX() > 1070 && me.getX() < 1070 + Game.SIZEOFQUADRANGLE) {
                    if (me.getY() > 850 && me.getY() < 850 + Game.SIZEOFQUADRANGLE) {
                        System.out.println("me!");
                        if (appleCount > 1 && mushroomCount > 1 && starCount > 1 && wallCount > 1) {
                            System.out.println("start!");
                            Game.inSetBoard = false;
                            Game.inGame = true;
                            repaint();
                        }
                    } else if (me.getY() > 160 && me.getY() < 160 + Game.SIZEOFQUADRANGLE) {
                        inApple = true;
                        inMushroom = false;
                        inStar = false;
                        inWall = false;
                        System.out.print("apple!");
                    } else if (me.getY() > 240 && me.getY() < 240 + Game.SIZEOFQUADRANGLE) {
                        inApple = false;
                        inMushroom = true;
                        inStar = false;
                        inWall = false;
                        System.out.print("mushroom!");
                    } else if (me.getY() > 320 && me.getY() < 320 + Game.SIZEOFQUADRANGLE) {
                        inApple = false;
                        inMushroom = false;
                        inStar = true;
                        inWall = false;
                        starCount++;
                        System.out.print("star!");
                    } else if (me.getY() > 400 && me.getY() < 400 + Game.SIZEOFQUADRANGLE) {
                        inApple = false;
                        inMushroom = false;
                        inStar = false;
                        inWall = true;
                        System.out.print("wall!");
                    }

                } else if (Game.inSetBoard && me.getX() > 80 && me.getX() < 80 + Game.SIZEOFQUADRANGLE * Game.LENGTH
                        && me.getY() > 80 && me.getY() < 80 + Game.SIZEOFQUADRANGLE * Game.WIDTH) {
                    row = ((me.getX() - 80) / Game.SIZEOFQUADRANGLE);
                    col = ((me.getY() - 80) / Game.SIZEOFQUADRANGLE);

                    if (inApple) {

                        map[row][col].setType("apple");
                        appleCount++;
                        repaint();
                        System.out.println(map[row][col].getType());

                    } else if (inMushroom) {
                        map[row][col].setType("mushroom");
                        mushroomCount++;
                        repaint();
                    } else if (inStar) {
                        map[row][col].setType("star");
                        starCount++;
                        repaint();
                    } else if (inWall) {
                        map[row][col].setType("wall");
                        wallCount++;
                        repaint();
                    }
                    System.out.println(row + " " + col + " ");
                }

                if (Game.inGame && me.getX() > 80 && me.getX() < 80 + Game.SIZEOFQUADRANGLE * Game.LENGTH
                        && me.getY() > 80 && me.getY() < 80 + Game.SIZEOFQUADRANGLE * Game.WIDTH) {

                    row = ((me.getX() - 80) / Game.SIZEOFQUADRANGLE);
                    col = ((me.getY() - 80) / Game.SIZEOFQUADRANGLE);
                    if (count == 0) {
                        inBluePlayer = true;
                        bluePlayer = new Player(copyMap());
                        bluePlayer.setType("bluePlayer");
                        map[row][col].setType("bluePlayer");
                        bluePlayer.initLocate(row, col);
                        bluePlayer.setType("bluePlayer");
                        System.out.println("truePlayer!");
                        count++;
                        repaint();
                    } else if (count == 1) {
                        inRedPlayer = true;
                        redPlayer = new Player(copyMap());
                        redPlayer.setType("redPlayer");
                        map[row][col].setType("redPlayer");
                        redPlayer.initLocate(row, col);
                        redPlayer.setType("redPlayer");
                        count++;
                        bluePlayerSw = true;
                        repaint();
                    } else if (count % 2 == 0) {
                        bluePlayer.move(row, col);
                        //  if (bluePlayer.checkMove()) {
                        count++;
                        bluePlayerSw = false;
                        System.out.println("trueBlue!" + !bluePlayer.checkMove());
                        map = bluePlayer.copyMap();
                        repaint();
                        if (!bluePlayer.checkMove()) {
                            count--;
                            bluePlayerSw = true;
                        }
                        //}
                    } else if (count % 2 == 1) {
                        redPlayer.move(row, col);
                        //  if (redPlayer.checkMove()) {
                        count++;
                        System.out.println("trueRed!");
                        bluePlayerSw = true;
                        map = redPlayer.copyMap();
                        repaint();
                        if (!redPlayer.checkMove()) {
                            
                            count--;
                            bluePlayerSw = false;
                        }
                        //}
                    }
                }
            }
        }
        );
    }

    public static Image appleImage;
    public static Image mushroomImage;
    public static Image starImage;
    public static Image wallImage;
    public static Image backGroundImage;
    public static Image bluePlayerImage;
    public static Image redPlayerImage;
    public static Image buttonStartImage;

    public static void loadImages() {

        ImageIcon ia = new ImageIcon("d:\\Apple4.png");
        appleImage = ia.getImage();

        ImageIcon im = new ImageIcon("d:\\Mashroom.png");
        mushroomImage = im.getImage();

        ImageIcon is = new ImageIcon("d:\\Star3.png");
        starImage = is.getImage();

        ImageIcon ib = new ImageIcon("d:\\BackGround.png");
        backGroundImage = ib.getImage();

        ImageIcon iw = new ImageIcon("d:\\Wall2.jpg");
        wallImage = iw.getImage();

        ImageIcon ip1 = new ImageIcon("d:\\Player5.png");
        bluePlayerImage = ip1.getImage();

        ImageIcon ip2 = new ImageIcon("d:\\Player6.png");
        redPlayerImage = ip2.getImage();

        ImageIcon ist = new ImageIcon("d:\\Start2.png");
        buttonStartImage = ist.getImage();
    }
    
    public boolean endOfGame(){
        if(starCount==0){
            return true;
        }
        else {
            return false;
        }
    }
            

    public void paint(Graphics g) {

        loadImages();

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(backGroundImage, 0, 0, 1200, 1000, this);
        if (Game.inSetBoard) {
            g2.drawImage(appleImage, 1070, 160, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
            g2.drawImage(mushroomImage, 1070, 240, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
            g2.drawImage(starImage, 1070, 320, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
            g2.drawImage(wallImage, 1070, 400, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, java.awt.Color.white, this);
            g2.drawImage(buttonStartImage, 1070, 855, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE, this);
 
        } else if (Game.inGame) {
            Font font = new Font("arial", Font.BOLD, 22);
            g2.setFont(font);
            if(count>2){
            g2.drawString("blueScore:  "+bluePlayer.getPow(), 1000, 400);
            g2.drawString("redScore:  "+redPlayer.getPow(), 1000, 480);}

        }

        if (Game.inGame && count > 0) {
            bluePlayer.bluePaint(g2);
        }
        if (Game.inGame && count > 1) {
            redPlayer.redPaint(g2);
        }
        for (int i = 0; i < Game.WIDTH; i++) {
            for (int j = 0; j < Game.LENGTH; j++) {
                if (map[j][i].sw) {
                    map[j][i].paint(g2);
                }
            }
        }
        for (int i = 0; i < Game.WIDTH; i++) {
            for (int j = 0; j < Game.LENGTH; j++) {
                g2.drawRect(80 + Game.SIZEOFQUADRANGLE * j, 80 + Game.SIZEOFQUADRANGLE * i, Game.SIZEOFQUADRANGLE, Game.SIZEOFQUADRANGLE);
            }
        }
    }
}
