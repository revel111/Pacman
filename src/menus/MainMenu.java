package menus;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        JFrame jframe = new JFrame("Pacman");
        jframe.setForeground(Color.YELLOW);
        jframe.setLayout(new BorderLayout());

//        JLabel background = new JLabel("", new ImageIcon("src/images/background.gif"), JLabel.CENTER);
//        Image backIcon = new Image("src/images/background.gif");
//        JLabel background = new JLabel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(b), 0,0,getWidth(),getHeight(),this);
//            }
//        };
//        for (int i = 10; i < Math.min(getWidth(), getHeight()) / 2; i += 10)
//            background.setBounds(i, i, getWidth() - i * 2, getHeight() - i * 2);
//
//        jframe.add(background);

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(10, 20, 10, 20);

        JButton newGame = new JButton("New Game");
        JButton highScores = new JButton("High Scores");
        JButton exit = new JButton("Exit");

        newGame.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        highScores.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        exit.setFont(new Font("OCR A Extended", Font.PLAIN, 20));

        newGame.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        highScores.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        newGame.setForeground(Color.YELLOW);
        highScores.setForeground(Color.YELLOW);
        exit.setForeground(Color.YELLOW);

        newGame.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        highScores.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        exit.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));

        newGame.setBackground(Color.BLACK);
        highScores.setBackground(Color.BLACK);
        exit.setBackground(Color.BLACK);

        gbc.gridx = 0;
        buttons.add(newGame, gbc);

        gbc.gridx = 1;
        buttons.add(highScores, gbc);

        gbc.gridx = 2;
        buttons.add(exit, gbc);

        jframe.add(buttons);
        Image frameImage = new ImageIcon("src/images/icon.png").getImage();
        jframe.setIconImage(frameImage);
        jframe.pack();
        jframe.setSize(1200, 750);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
}