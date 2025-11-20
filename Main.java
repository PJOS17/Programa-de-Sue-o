import controller.AnalisisSueno;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import vista.Login;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                AnalisisSueno analisis = new AnalisisSueno();
                Login login = new Login(analisis);
                login.setVisible(true);

            } catch (Exception e) {
                System.err.println("Error initializing GUI: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}