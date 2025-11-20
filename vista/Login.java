package vista;

import javax.swing.*;
import java.awt.*;
import controller.AnalisisSueno;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    private final String USER_CORRECTO = "admin";
    private final String PASS_CORRECTA = "1234";
    private AnalisisSueno analisis;

    public Login(AnalisisSueno analisis) {
        this.analisis = analisis;

        setTitle("Inicio de Sesión");
        setSize(350, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        add(panel, BorderLayout.CENTER);

        JButton btnLogin = new JButton("Iniciar Sesión");
        add(btnLogin, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> verificarLogin());
    }

    private void verificarLogin() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (user.equals(USER_CORRECTO) && pass.equals(PASS_CORRECTA)) {

            JOptionPane.showMessageDialog(this, "Bienvenido " + user);

            GUISueno gui = new GUISueno(analisis);
            gui.setVisible(true);

            dispose();

        } else {
            JOptionPane.showMessageDialog(this,
                "Usuario o contraseña incorrectos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
