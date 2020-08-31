package screens;

import javax.swing.*;

public class Screen extends JPanel {
    private final String title;

    public Screen(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
