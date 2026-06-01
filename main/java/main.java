import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game("Flappy Bird", 400, 600));
    }
}
