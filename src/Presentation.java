import windows.MainMenu;

import javax.swing.*;

public class Presentation {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}