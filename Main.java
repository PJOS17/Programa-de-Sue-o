import controller.AnalisisSueno;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import vista.GUISueno;

public class Main {
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                AnalisisSueno analisis = new AnalisisSueno();
                GUISueno gui = new GUISueno(analisis);
                gui.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error initializing GUI: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}