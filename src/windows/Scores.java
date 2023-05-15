package windows;

import operations.ObjectScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scores extends JFrame {
    public Scores() throws IOException {
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

        ArrayList<ObjectScore> arrayList = ObjectScore.readObjects();
        ArrayList<String> stringArrayList = new ArrayList<>();

        for (ObjectScore objectScore : arrayList) {
            String string = objectScore.getNick() + ": " + objectScore.getScore();
            stringArrayList.add(string);
        }

        Comparator<String> scoreComparator = new Comparator<>() {
            @Override
            public int compare(String element1, String element2) {
                int score1 = extractScore(element1);
                int score2 = extractScore(element2);
                return Integer.compare(score2, score1);
            }
            private int extractScore(String element) {
                String[] parts = element.split(":");
                String scoreString = parts[1].trim();
                return Integer.parseInt(scoreString);
            }
        };
        stringArrayList.sort(scoreComparator);

        JList list = new JList(stringArrayList.toArray(new String[0]));
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