import menus.MainMenu;

import javax.swing.*;

public class Presentation {
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MainMenu();
//            }
//        });

        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}