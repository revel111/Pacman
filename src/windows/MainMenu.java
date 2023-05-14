package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        JFrame jframe = new JFrame("Pacman");

        JLabel title = new JLabel("Pacman Game", JLabel.CENTER);
        title.setFont(new Font("OCR A Extended", Font.PLAIN, 70));
        title.setForeground(Color.YELLOW);
        title.setBackground(Color.BLACK);
        title.setOpaque(true);
        jframe.add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(10, 20, 10, 20);

        JButton newGame = createButton("New Game");
        JButton highScores = createButton("High Scores");
        JButton exit = createButton("Exit");

        gbc.gridx = 0;
        buttons.add(newGame, gbc);
        gbc.gridx = 1;
        buttons.add(highScores, gbc);
        gbc.gridx = 2;
        buttons.add(exit, gbc);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // start game
                String valueStr = JOptionPane.showInputDialog(null, "Enter a height:", "Input Value", JOptionPane.PLAIN_MESSAGE);
                int height;
                int width;
                try {
                    height = Integer.parseInt(valueStr);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "You wrote nothing.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                valueStr = JOptionPane.showInputDialog(null, "Enter a width:", "Input Value", JOptionPane.PLAIN_MESSAGE);
                try {
                    width = Integer.parseInt(valueStr);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "You wrote nothing.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (height < 10 || height > 100 || width < 10 || width > 100) {
                    JOptionPane.showMessageDialog(null, "Invalid value entered. Please enter a value from 10 to 100.", "Error", JOptionPane.ERROR_MESSAGE);
                    actionPerformed(e);
                    return;
                }

                //kill main menu
                jframe.dispose();
                SwingUtilities.invokeLater(() -> new Game(height, width));
            }
        });

        highScores.addActionListener(e -> {  //high scores
            jframe.dispose();
            SwingUtilities.invokeLater(Scores::new);
        });

        exit.addActionListener(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to exit?", "Warning", JOptionPane.OK_CANCEL_OPTION, dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                jframe.dispose();
            }
        });

        KeyStroke ctrlShiftQ = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);//shortcut to close window
        jframe.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ctrlShiftQ, "closeWindow");
        jframe.getRootPane().getActionMap().put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }
        });

        jframe.add(buttons);
        Image frameImage = new ImageIcon("src/images/icon.png").getImage();
        jframe.setIconImage(frameImage);
        jframe.pack();
        jframe.setSize(1200, 750);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }

    public static JButton createButton(String name) {
        JButton button = new JButton(name);

        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        button.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        button.setForeground(Color.YELLOW);
        button.setBackground(Color.BLACK);

        return button;
    }
}