package game;

import java.util.HashSet;

import java.awt.*;
import javax.swing.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;

public class Game extends JFrame {

    public static int LENGTH;
    public static int WIDTH;
    public static int SIZEOFQUADRANGLE;

    private boolean inMenu = true;
    public static boolean inSetBoard = false;
    public static boolean inGame = false;
    public static boolean trueSize = true;

    public static JFrame menuFrame = new JFrame("menu");
    public static JFrame gameFrame = new JFrame("game");

    public Game() {

        initMenuBoard(550, 150, 650, 320);
        add(new GameBoard());
    }

    private void initMenuBoard(int WIDTH, int HEIGHT, int LOCWIDTH, int LOCHEIGHT) {

        menuFrame.setTitle("MENU");
        menuFrame.setSize(WIDTH, HEIGHT);
        menuFrame.setLocation(LOCWIDTH, LOCHEIGHT);
        menuFrame.getContentPane().setBackground(Color.WHITE);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new FlowLayout());
        printText();

    }

    public static void actionTest() {

        Container container = gameFrame.getContentPane();
        container.setLayout(new FlowLayout());

        JButton okButton = new JButton("me");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        container.add(okButton);
    }

    private void printText() {

        Container container = menuFrame.getContentPane();
        container.setLayout(new FlowLayout());

        JTextField length = new JTextField();
        length.setPreferredSize(new Dimension(100, 30));

        JLabel label = new JLabel("Please enter your prefer size");
        label.setFont(new Font("Bold", 1, 23));
        label.setForeground(Color.GRAY);

        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String SIZE = length.getText();
                String[] size = SIZE.split(" ");

                LENGTH = Integer.parseInt(size[0]);
                WIDTH = Integer.parseInt(size[1]);
                if (LENGTH > 2 && WIDTH > 2 && LENGTH < 13 && WIDTH < 13) {
                    SIZEOFQUADRANGLE = 70;
                } else if (LENGTH > 2 && WIDTH > 2 && LENGTH < 17 && WIDTH < 17) {
                    SIZEOFQUADRANGLE = 50;
                } else {
                    trueSize = false;
                }
                label.setText(SIZE);

                menuFrame.setVisible(false);
                inMenu = false;
                inSetBoard = true;
                initGame();
            }
        });
        menuFrame.setVisible(true);
        container.add(length);
        container.add(okButton);
        container.add(label);

    }

    private void initGame() {

        gameFrame.setTitle("Game");

        gameFrame.setSize(1200, 1000);
        gameFrame.setLocationRelativeTo(null);
        // gameFrame.getContentPane().setBackground(Color.WHITE);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //gameFrame.setLayout(new FlowLayout());
        inSetBoard = true;
        GameBoard box = new GameBoard();
        gameFrame.getContentPane().add(box);
        //box.repaint();

        gameFrame.setVisible(true);
    }

    public static void main(String[] args) {

        Game game = new Game();
    }
}
