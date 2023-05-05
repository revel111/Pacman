package windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class Scores extends JFrame implements Serializable {
    public Scores() {
        JFrame jframe = new JFrame("Pacman");
        Image frameImage = new ImageIcon("src/images/icon.png").getImage();
        jframe.setIconImage(frameImage);
        jframe.pack();
        jframe.setSize(1200, 750);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SwingUtilities.invokeLater(MainMenu::new);
            }
        });

        KeyStroke ctrlShiftQ = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        jframe.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ctrlShiftQ, "closeWindow");
        jframe.getRootPane().getActionMap().put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(MainMenu::new);
                jframe.dispose();
            }
        });



        String[] all = {"fuuf", "shshh", "KKK"};

        for (int i = 0; i < all.length; i++)
            all[i] = i + 1 + ") " + all[i];



        JList list = new JList(all);
        list.setBackground(Color.BLACK);
        list.setForeground(Color.YELLOW);
        list.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
        

        jframe.add(list);
        JScrollPane jScrollPane = new JScrollPane(list);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jframe.getContentPane().add(jScrollPane);
    }
}