import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Biar komponen (termasuk JButton) mirip native OS
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new ZooFrame().setVisible(true));
    }
}
