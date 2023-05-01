package menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainMenu extends JFrame {
    public MainMenu() {
        JFrame jframe = new JFrame("Pacman");
//        jframe.setLayout(new BorderLayout()); //idk for what

        JLabel title = new JLabel("Pacman Game", JLabel.CENTER);
        title.setFont(new Font("OCR A Extended", Font.PLAIN, 70));
        title.setForeground(Color.YELLOW);
        title.setBackground(Color.BLACK);
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));// ne obyaz
        jframe.add(title, BorderLayout.NORTH);

        //add background

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

//        newGame.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) { // start game
//
//            }
//        });

//        highScores.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {  //high scores
////                jframe.setVisible(false);
////                JFrame jframeNew = new JFrame("Pacman");
////                jframeNew.setVisible(true);
//            }
//        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to exit?", "Warning", JOptionPane.OK_CANCEL_OPTION, dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(-1);
                }
            }
        });

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