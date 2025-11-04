package vista;

import controller.AnalisisSueno;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import model.RegistroSueno;
import model.Usuario;
import model.Problema;

public class GUISueno extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel loginPanel;
    private JPanel menuPanel;
    private JPanel registroPanel;
    private JPanel perfilPanel;
    private JPanel analisisPanel;
    private JPanel graficaPanel;
    private Grafica grafica;

    private Usuario usuario;
    private AnalisisSueno analisis;
    private JTextField nombreField, edadField, generoField, pesoField, alturaField;

    public GUISueno(AnalisisSueno analisis) {
        this.analisis = analisis;
        setTitle("Análisis del Sueño");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        initLoginPanel();
        initMenuPanel();
        initRegistroPanel();
        initPerfilPanel();
        initAnalisisPanel();
        initGraficaPanel();

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    // ==============================
    // Panel de Inicio de Sesión
    // ==============================
    private void initLoginPanel() {
        loginPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Registro / Inicio de Sesión"));

        nombreField = new JTextField();
        edadField = new JTextField();
        generoField = new JTextField();
        pesoField = new JTextField();
        alturaField = new JTextField();

        JButton btnIniciar = new JButton("Iniciar sesión");
        btnIniciar.addActionListener(e -> crearUsuario());

        loginPanel.add(new JLabel("Nombre:"));
        loginPanel.add(nombreField);
        loginPanel.add(new JLabel("Edad:"));
        loginPanel.add(edadField);
        loginPanel.add(new JLabel("Género (hombre/mujer):"));
        loginPanel.add(generoField);
        loginPanel.add(new JLabel("Peso (kg):"));
        loginPanel.add(pesoField);
        loginPanel.add(new JLabel("Altura (m):"));
        loginPanel.add(alturaField);
        loginPanel.add(new JLabel());
        loginPanel.add(btnIniciar);

        mainPanel.add(loginPanel, "Login");
    }

    private void crearUsuario() {
        try {
            String nombre = nombreField.getText().trim();
            int edad = Integer.parseInt(edadField.getText().trim());
            String genero = generoField.getText().trim().toLowerCase();
            double peso = Double.parseDouble(pesoField.getText().trim());
            double altura = Double.parseDouble(alturaField.getText().trim());

            if (!genero.equals("hombre") && !genero.equals("mujer")) {
                throw new IllegalArgumentException("El género debe ser 'hombre' o 'mujer'.");
            }

            usuario = new Usuario(nombre, edad, genero, peso, altura);
            JOptionPane.showMessageDialog(this, "Usuario creado correctamente.");
            cardLayout.show(mainPanel, "Menu");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // ==============================
    // Panel de Menú Principal
    // ==============================
    private void initMenuPanel() {
        menuPanel = new JPanel(new GridLayout(9, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menú Principal"));

        String[] opciones = {
                "Registrar nuevo sueño",
                "Ver registros anteriores",
                "Ver perfil del usuario",
                "Editar perfil del usuario",
                "Ver análisis del sueño",
                "Ver recomendaciones personalizadas",
                "Ver gráfica de sueño",
                "Cerrar sesión"
        };

        for (String opcion : opciones) {
            JButton btn = new JButton(opcion);
            btn.addActionListener(this::menuAccion);
            menuPanel.add(btn);
        }

        mainPanel.add(menuPanel, "Menu");
    }

    private void menuAccion(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Registrar nuevo sueño":
                cardLayout.show(mainPanel, "Registro");
                break;
            case "Ver registros anteriores":
                mostrarRegistros();
                break;
            case "Ver perfil del usuario":
                mostrarPerfil(false);
                break;
            case "Editar perfil del usuario":
                mostrarPerfil(true);
                break;
            case "Ver análisis del sueño":
                mostrarAnalisis();
                break;
            case "Ver recomendaciones personalizadas":
                JOptionPane.showMessageDialog(this, analisis.generarRecomendaciones(usuario));
                break;
            case "Ver gráfica de sueño":
                mostrarGrafica();
                break;
            case "Cerrar sesión":
                cardLayout.show(mainPanel, "Login");
                usuario = null;
                break;
        }
    }

    // ==============================
    // Panel de Registro de Sueño
    // ==============================
    private void initRegistroPanel() {
        registroPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        registroPanel.setBorder(BorderFactory.createTitledBorder("Registrar Sueño"));

        JTextField fechaField = new JTextField();
        JTextField dormirField = new JTextField();
        JTextField despertarField = new JTextField();
        JTextField calidadField = new JTextField();
        JTextField observacionesField = new JTextField();

JButton btnGuardar = new JButton("Guardar");
btnGuardar.addActionListener(e -> {
    try {
        LocalDate fecha = LocalDate.parse(fechaField.getText().trim());
        LocalTime dormir = LocalTime.parse(dormirField.getText().trim());
        LocalTime despertar = LocalTime.parse(despertarField.getText().trim());
        int calidad = Integer.parseInt(calidadField.getText().trim());
        String obs = observacionesField.getText().trim();

        // Preguntar por problema de sueño
        Problema problema = new Problema();
        String[] opciones = problema.getProblemasSueno().toArray(new String[0]);

        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "¿Tuviste algún problema durante el sueño?",
            "Problema de Sueño",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (seleccion != null) {
            problema.setProblema(seleccion);
            obs += " | Problema: " + problema.getProblemaDetectado();
        }

        RegistroSueno reg = new RegistroSueno(usuario, fecha, dormir, despertar, calidad, obs);
        analisis.agregarRegistro(reg);
        JOptionPane.showMessageDialog(this, "Registro agregado correctamente.");
        cardLayout.show(mainPanel, "Menu");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
});

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        registroPanel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        registroPanel.add(fechaField);
        registroPanel.add(new JLabel("Hora dormir (HH:MM):"));
        registroPanel.add(dormirField);
        registroPanel.add(new JLabel("Hora despertar (HH:MM):"));
        registroPanel.add(despertarField);
        registroPanel.add(new JLabel("Calidad (1-10):"));
        registroPanel.add(calidadField);
        registroPanel.add(new JLabel("Observaciones:"));
        registroPanel.add(observacionesField);
        registroPanel.add(btnVolver);
        registroPanel.add(btnGuardar);

        mainPanel.add(registroPanel, "Registro");
    }

    // ==============================
    // Panel de Perfil
    // ==============================
    private void initPerfilPanel() {
        perfilPanel = new JPanel(new BorderLayout());
        mainPanel.add(perfilPanel, "Perfil");
    }

    private void mostrarPerfil(boolean editable) {
        perfilPanel.removeAll();

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField n = new JTextField(usuario.getNombre());
        JTextField e = new JTextField(String.valueOf(usuario.getEdad()));
        JTextField g = new JTextField(usuario.getGenero());
        JTextField p = new JTextField(String.valueOf(usuario.getPeso()));
        JTextField a = new JTextField(String.valueOf(usuario.getAltura()));

        n.setEditable(editable);
        e.setEditable(editable);
        g.setEditable(editable);
        p.setEditable(editable);
        a.setEditable(editable);

        form.add(new JLabel("Nombre:")); form.add(n);
        form.add(new JLabel("Edad:")); form.add(e);
        form.add(new JLabel("Género:")); form.add(g);
        form.add(new JLabel("Peso:")); form.add(p);
        form.add(new JLabel("Altura:")); form.add(a);

        JButton volver = new JButton("Volver");
        volver.addActionListener(ev -> cardLayout.show(mainPanel, "Menu"));

        perfilPanel.add(form, BorderLayout.CENTER);
        perfilPanel.add(volver, BorderLayout.SOUTH);

        if (editable) {
            JButton guardar = new JButton("Guardar cambios");
            guardar.addActionListener(ev -> {
                try {
                    usuario.actualizarDatos(
                            n.getText().trim(),
                            Integer.parseInt(e.getText().trim()),
                            g.getText().trim(),
                            Double.parseDouble(p.getText().trim()),
                            Double.parseDouble(a.getText().trim())
                    );
                    JOptionPane.showMessageDialog(this, "Perfil actualizado.");
                    cardLayout.show(mainPanel, "Menu");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            });
            perfilPanel.add(guardar, BorderLayout.NORTH);
        }

        perfilPanel.revalidate();
        perfilPanel.repaint();
        cardLayout.show(mainPanel, "Perfil");
    }

    // ==============================
    // Panel de Análisis
    // ==============================
    private void initAnalisisPanel() {
        analisisPanel = new JPanel(new BorderLayout());
        mainPanel.add(analisisPanel, "Analisis");
    }

    private void mostrarAnalisis() {
        analisisPanel.removeAll();
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText(analisis.mostrarTendencia() + "\n\n" + analisis.generarRecomendaciones(usuario));

        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        analisisPanel.add(new JScrollPane(area), BorderLayout.CENTER);
        analisisPanel.add(volver, BorderLayout.SOUTH);

        analisisPanel.revalidate();
        analisisPanel.repaint();
        cardLayout.show(mainPanel, "Analisis");
    }

    // ==============================
    // Panel de Gráfica
    // ==============================
    private void initGraficaPanel() {
        graficaPanel = new JPanel(new BorderLayout());
        mainPanel.add(graficaPanel, "Grafica");
    }

private void mostrarGrafica() {
    graficaPanel.removeAll();

    grafica = new Grafica(analisis.getRegistros()); // ahora pasa registros completos

    JButton volver = new JButton("Volver");
    volver.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

    graficaPanel.add(grafica, BorderLayout.CENTER);
    graficaPanel.add(volver, BorderLayout.SOUTH);
    graficaPanel.revalidate();
    graficaPanel.repaint();

    cardLayout.show(mainPanel, "Grafica");
}

    private void mostrarRegistros() {
        if (analisis.getRegistros().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay registros aún.");
            return;
        }

        JTextArea area = new JTextArea();
        area.setEditable(false);
        StringBuilder sb = new StringBuilder();
        for (RegistroSueno r : analisis.getRegistros()) {
            sb.append(r.mostrarResumen()).append("\n");
        }
        area.setText(sb.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Registros de Sueño", JOptionPane.INFORMATION_MESSAGE);
    }
}
