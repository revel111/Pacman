package menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        JFrame jframe = new JFrame("Pacman");
        jframe.getContentPane().setBackground(Color.BLACK);
        jframe.setForeground(Color.YELLOW);
        jframe.setLayout(null);
        JLabel background = new JLabel("", new ImageIcon("src/images/background.gif"), JLabel.CENTER);
        background.setBounds(0, 0, 1200, 850);
        jframe.add(background);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            }
        });

        jframe.pack();
        jframe.setSize(1200, 750);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
}