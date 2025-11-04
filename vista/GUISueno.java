package vista;

import controller.AnalisisSueno;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.RegistroSueno;
import model.Usuario;
import model.Problema;

public class GUISueno extends JFrame {
    private AnalisisSueno analisis;
    private Usuario usuario;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Paneles para diferentes vistas
    private JPanel loginPanel;
    private JPanel menuPanel;
    private JPanel registroPanel;
    private JPanel historialPanel;
    private JPanel perfilPanel;
    private JPanel editarPerfilPanel;
    private JPanel analisisPanel;
    private JPanel recomendacionesPanel;
    private JPanel graficaPanel;
    
    // Componentes de login
    private JTextField txtNombreLogin;
    private JSpinner spnEdad;
    private JComboBox<String> cmbGenero;
    private JTextField txtPeso;
    private JTextField txtAltura;
    private JCheckBox chkInsomnio;
    private JCheckBox chkPesadillas;
    private JCheckBox chkApnea;
    private JCheckBox chkNarcolepsia;
    
    // Componentes de registro de sueño
    private JTextField txtFecha;
    private JTextField txtHoraDormir;
    private JTextField txtHoraDespertar;
    private JSpinner spnCalidad;
    private JTextArea txtObservaciones;
    
    // Componentes de editar perfil
    private JTextField txtNombreEdit;
    private JSpinner spnEdadEdit;
    private JComboBox<String> cmbGeneroEdit;
    private JTextField txtPesoEdit;
    private JTextField txtAlturaEdit;
    private JCheckBox chkInsomnioEdit;
    private JCheckBox chkPesadillasEdit;
    private JCheckBox chkApneaEdit;
    private JCheckBox chkNarcolepsiaEdit;
    
    // Tabla para historial
    private JTable tablaHistorial;
    private DefaultTableModel modeloTabla;
    
// Panel de gráfica
private Grafica grafica;

public GUISueno(AnalisisSueno analisis) {
        this.analisis = analisis;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Análisis del Sueño");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Crear todos los paneles
        crearPanelLogin();
        crearPanelMenu();
        crearPanelRegistro();
        crearPanelHistorial();
        crearPanelPerfil();
        crearPanelEditarPerfil();
        crearPanelAnalisis();
        crearPanelRecomendaciones();
        crearPanelGrafica();
        
        // Agregar paneles al mainPanel
        mainPanel.add(loginPanel, "login");
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(registroPanel, "registro");
        mainPanel.add(historialPanel, "historial");
        mainPanel.add(perfilPanel, "perfil");
        mainPanel.add(editarPerfilPanel, "editarPerfil");
        mainPanel.add(analisisPanel, "analisis");
        mainPanel.add(recomendacionesPanel, "recomendaciones");
        mainPanel.add(graficaPanel, "grafica");
        
        add(mainPanel);
        
        // Mostrar login al inicio
        cardLayout.show(mainPanel, "login");
    }
    
    private void crearPanelLogin() {
        loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(new Color(240, 248, 255));
        
        // Panel central con scroll
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createLineBorder(new Color(100, 149, 237), 2)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel lblTitulo = new JLabel("Bienvenido al Sistema de Análisis del Sueño");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(25, 118, 210));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        centerPanel.add(lblTitulo, gbc);
        
        gbc.gridwidth = 1;
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Nombre:"), gbc);
        txtNombreLogin = new JTextField(20);
        gbc.gridx = 1;
        centerPanel.add(txtNombreLogin, gbc);
        
        // Edad
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(new JLabel("Edad:"), gbc);
        spnEdad = new JSpinner(new SpinnerNumberModel(25, 1, 110, 1));
        gbc.gridx = 1;
        centerPanel.add(spnEdad, gbc);
        
        // Género
        gbc.gridx = 0; gbc.gridy = 3;
        centerPanel.add(new JLabel("Género:"), gbc);
        cmbGenero = new JComboBox<>(new String[]{"hombre", "mujer"});
        gbc.gridx = 1;
        centerPanel.add(cmbGenero, gbc);
        
        // Peso
        gbc.gridx = 0; gbc.gridy = 4;
        centerPanel.add(new JLabel("Peso (kg):"), gbc);
        txtPeso = new JTextField(20);
        gbc.gridx = 1;
        centerPanel.add(txtPeso, gbc);
        
        // Altura
        gbc.gridx = 0; gbc.gridy = 5;
        centerPanel.add(new JLabel("Altura (m):"), gbc);
        txtAltura = new JTextField(20);
        gbc.gridx = 1;
        centerPanel.add(txtAltura, gbc);
        
        // Problemas de sueño
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        JLabel lblProblemas = new JLabel("Problemas de sueño:");
        lblProblemas.setFont(new Font("Arial", Font.BOLD, 12));
        centerPanel.add(lblProblemas, gbc);
        
        JPanel problemasPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        problemasPanel.setBackground(Color.WHITE);
        chkInsomnio = new JCheckBox("Insomnio");
        chkPesadillas = new JCheckBox("Pesadillas");
        chkApnea = new JCheckBox("Apnea del sueño");
        chkNarcolepsia = new JCheckBox("Narcolepsia");
        problemasPanel.add(chkInsomnio);
        problemasPanel.add(chkPesadillas);
        problemasPanel.add(chkApnea);
        problemasPanel.add(chkNarcolepsia);
        
        gbc.gridy = 7;
        centerPanel.add(problemasPanel, gbc);
        
        // Botón Ingresar
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(76, 175, 80));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIngresar.addActionListener(e -> iniciarSesion());
        gbc.gridy = 8; gbc.gridwidth = 2;
        centerPanel.add(btnIngresar, gbc);
        
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        loginPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void iniciarSesion() {
        try {
            String nombre = txtNombreLogin.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre válido");
                return;
            }
            
            int edad = (int) spnEdad.getValue();
            String genero = (String) cmbGenero.getSelectedItem();
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double altura = Double.parseDouble(txtAltura.getText().trim());
            
            if (peso < 30 || peso > 300 || altura < 0.5 || altura > 2.5) {
                JOptionPane.showMessageDialog(this, "Valores de peso o altura fuera de rango");
                return;
            }
            
            // Obtener problemas de sueño
            List<String> problemas = new ArrayList<>();
            if (chkInsomnio.isSelected()) problemas.add("Insomnio");
            if (chkPesadillas.isSelected()) problemas.add("Pesadillas");
            if (chkApnea.isSelected()) problemas.add("Apnea del sueño");
            if (chkNarcolepsia.isSelected()) problemas.add("Narcolepsia");
            
            usuario = new Usuario(nombre, edad, genero, peso, altura, problemas);
            JOptionPane.showMessageDialog(this, "¡Bienvenido " + nombre + "!");
            cardLayout.show(mainPanel, "menu");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos");
        }
    }
    
    private void crearPanelMenu() {
        menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(new Color(240, 248, 255));
        
        // Título
        JLabel lblTitulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        menuPanel.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel de botones
        JPanel botonesPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        botonesPanel.setBackground(new Color(240, 248, 255));
        
        String[] opciones = {
            "Registrar Sueño", "Ver Historial",
            "Ver Perfil", "Editar Perfil",
            "Ver Análisis", "Ver Recomendaciones",
            "Ver Gráfica", "Salir"
        };
        
        Color[] colores = {
            new Color(33, 150, 243), new Color(156, 39, 176),
            new Color(255, 152, 0), new Color(76, 175, 80),
            new Color(244, 67, 54), new Color(63, 81, 181),
            new Color(0, 150, 136), new Color(96, 125, 139)
        };
        
        for (int i = 0; i < opciones.length; i++) {
            JButton btn = new JButton(opciones[i]);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setBackground(colores[i]);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            
            final int index = i;
            btn.addActionListener(e -> manejarOpcionMenu(index));
            botonesPanel.add(btn);
        }
        
        menuPanel.add(botonesPanel, BorderLayout.CENTER);
    }
    
    private void manejarOpcionMenu(int opcion) {
        switch (opcion) {
            case 0: cardLayout.show(mainPanel, "registro"); break;
            case 1: actualizarHistorial(); cardLayout.show(mainPanel, "historial"); break;
            case 2: actualizarPerfil(); cardLayout.show(mainPanel, "perfil"); break;
            case 3: cargarDatosEdicion(); cardLayout.show(mainPanel, "editarPerfil"); break;
            case 4: actualizarAnalisis(); cardLayout.show(mainPanel, "analisis"); break;
            case 5: actualizarRecomendaciones(); cardLayout.show(mainPanel, "recomendaciones"); break;
            case 6: actualizarGrafica(); cardLayout.show(mainPanel, "grafica"); break;
            case 7: System.exit(0); break;
    }
}

private void crearPanelRegistro() {
        registroPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Registrar Nuevo Sueño", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        registroPanel.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Fecha
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Fecha (YYYY-MM-DD):"), gbc);
        txtFecha = new JTextField(LocalDate.now().toString(), 20);
        gbc.gridx = 1;
        formPanel.add(txtFecha, gbc);
        
        // Hora dormir
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Hora de dormir (HH:MM):"), gbc);
        txtHoraDormir = new JTextField("22:00", 20);
        gbc.gridx = 1;
        formPanel.add(txtHoraDormir, gbc);
        
        // Hora despertar
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Hora de despertar (HH:MM):"), gbc);
        txtHoraDespertar = new JTextField("07:00", 20);
        gbc.gridx = 1;
        formPanel.add(txtHoraDespertar, gbc);
        
        // Calidad
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Calidad (1-10):"), gbc);
        spnCalidad = new JSpinner(new SpinnerNumberModel(7, 1, 10, 1));
        gbc.gridx = 1;
        formPanel.add(spnCalidad, gbc);
        
        // Observaciones
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Observaciones:"), gbc);
        txtObservaciones = new JTextArea(4, 20);
        txtObservaciones.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtObservaciones);
        gbc.gridx = 1;
        formPanel.add(scroll, gbc);
        
        registroPanel.add(formPanel, BorderLayout.CENTER);
        
        // Botones
        JPanel botonesPanel = new JPanel(new FlowLayout());
    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.setBackground(new Color(76, 175, 80));
    btnGuardar.setForeground(Color.WHITE);
    btnGuardar.addActionListener(e -> guardarRegistro());
    
    JButton btnVolver = new JButton("Volver");
    btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
    
    botonesPanel.add(btnGuardar);
    botonesPanel.add(btnVolver);
    registroPanel.add(botonesPanel, BorderLayout.SOUTH);
}
    
    private void guardarRegistro() {
        try {
            LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
            LocalTime dormir = LocalTime.parse(txtHoraDormir.getText().trim());
            LocalTime despertar = LocalTime.parse(txtHoraDespertar.getText().trim());
            int calidad = (int) spnCalidad.getValue();
            String obs = txtObservaciones.getText().trim();
            
            RegistroSueno registro = new RegistroSueno(usuario, fecha, dormir, despertar, calidad, obs);
            analisis.agregarRegistro(registro);
            
            JOptionPane.showMessageDialog(this, "Registro guardado exitosamente");
            limpiarFormularioRegistro();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha u hora inválido");
        }
    }
    
    private void limpiarFormularioRegistro() {
        txtFecha.setText(LocalDate.now().toString());
        txtHoraDormir.setText("22:00");
        txtHoraDespertar.setText("07:00");
        spnCalidad.setValue(7);
        txtObservaciones.setText("");
    }
    
    private void crearPanelHistorial() {
        historialPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Historial de Registros", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        historialPanel.add(lblTitulo, BorderLayout.NORTH);
        
        String[] columnas = {"Fecha", "Hora Dormir", "Hora Despertar", "Horas", "Calidad", "Observaciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
    tablaHistorial = new JTable(modeloTabla);
    JScrollPane scrollPane = new JScrollPane(tablaHistorial);
    historialPanel.add(scrollPane, BorderLayout.CENTER);
    
    JPanel botonesPanel = new JPanel(new FlowLayout());
    JButton btnVolver = new JButton("Volver");
    btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
    botonesPanel.add(btnVolver);
    historialPanel.add(botonesPanel, BorderLayout.SOUTH);
}

private void actualizarHistorial() {
    modeloTabla.setRowCount(0);
    for (RegistroSueno r : analisis.getRegistros()) {
        String resumen = r.mostrarResumen();
        String horaDormir = resumen.split("Dormir: ")[1].split(",")[0];
        String horaDespertar = resumen.split("Despertar: ")[1].split(",")[0];
        
        modeloTabla.addRow(new Object[]{
            r.getFecha(),
            horaDormir,
            horaDespertar,
            r.getHorasSueno(),
            r.getCalidadSueno(),
            r.getObservaciones()
        });
    }
}
    
    private void crearPanelPerfil() {
        perfilPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Perfil de Usuario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        perfilPanel.add(lblTitulo, BorderLayout.NORTH);
        
        JTextArea txtAreaPerfil = new JTextArea();
        txtAreaPerfil.setEditable(false);
        txtAreaPerfil.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAreaPerfil.setMargin(new Insets(20, 20, 20, 20));
        perfilPanel.add(new JScrollPane(txtAreaPerfil), BorderLayout.CENTER);
        
        JPanel botonesPanel = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        botonesPanel.add(btnVolver);
        perfilPanel.add(botonesPanel, BorderLayout.SOUTH);
    }
    
    private void actualizarPerfil() {
        if (usuario != null) {
            JTextArea txtArea = (JTextArea) ((JScrollPane) perfilPanel.getComponent(1)).getViewport().getView();
            txtArea.setText(usuario.mostrarPerfil());
        }
    }
    
    private void crearPanelEditarPerfil() {
        editarPerfilPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Editar Perfil", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        editarPerfilPanel.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);
        txtNombreEdit = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(txtNombreEdit, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Edad:"), gbc);
        spnEdadEdit = new JSpinner(new SpinnerNumberModel(25, 1, 110, 1));
        gbc.gridx = 1;
        formPanel.add(spnEdadEdit, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Género:"), gbc);
        cmbGeneroEdit = new JComboBox<>(new String[]{"hombre", "mujer"});
        gbc.gridx = 1;
        formPanel.add(cmbGeneroEdit, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Peso (kg):"), gbc);
        txtPesoEdit = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(txtPesoEdit, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Altura (m):"), gbc);
        txtAlturaEdit = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(txtAlturaEdit, gbc);
        
        // Problemas de sueño
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JLabel lblProblemas = new JLabel("Problemas de sueño:");
        lblProblemas.setFont(new Font("Arial", Font.BOLD, 12));
        formPanel.add(lblProblemas, gbc);
        
        JPanel problemasPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        chkInsomnioEdit = new JCheckBox("Insomnio");
        chkPesadillasEdit = new JCheckBox("Pesadillas");
        chkApneaEdit = new JCheckBox("Apnea del sueño");
        chkNarcolepsiaEdit = new JCheckBox("Narcolepsia");
        problemasPanel.add(chkInsomnioEdit);
        problemasPanel.add(chkPesadillasEdit);
        problemasPanel.add(chkApneaEdit);
        problemasPanel.add(chkNarcolepsiaEdit);
        
        gbc.gridy = 6;
        formPanel.add(problemasPanel, gbc);
        
        JScrollPane scrollPane = new JScrollPane(formPanel);
        editarPerfilPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel botonesPanel = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(76, 175, 80));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarEdicionPerfil());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        
        botonesPanel.add(btnGuardar);
        botonesPanel.add(btnVolver);
        editarPerfilPanel.add(botonesPanel, BorderLayout.SOUTH);
    }
    
    private void cargarDatosEdicion() {
        if (usuario != null) {
            txtNombreEdit.setText(usuario.getNombre());
            spnEdadEdit.setValue(usuario.getEdad());
            cmbGeneroEdit.setSelectedItem(usuario.getGenero());
            txtPesoEdit.setText(String.valueOf(usuario.getPeso()));
            txtAlturaEdit.setText(String.valueOf(usuario.getAltura()));
            
            // Cargar problemas de sueño
            List<String> problemas = usuario.getProblemasSueno();
            chkInsomnioEdit.setSelected(problemas.contains("Insomnio"));
            chkPesadillasEdit.setSelected(problemas.contains("Pesadillas"));
            chkApneaEdit.setSelected(problemas.contains("Apnea del sueño"));
            chkNarcolepsiaEdit.setSelected(problemas.contains("Narcolepsia"));
        }
    }
    
    private void guardarEdicionPerfil() {
        try {
            String nombre = txtNombreEdit.getText().trim();
            int edad = (int) spnEdadEdit.getValue();
            String genero = (String) cmbGeneroEdit.getSelectedItem();
            double peso = Double.parseDouble(txtPesoEdit.getText().trim());
            double altura = Double.parseDouble(txtAlturaEdit.getText().trim());
            
            List<String> problemas = new ArrayList<>();
            if (chkInsomnioEdit.isSelected()) problemas.add("Insomnio");
            if (chkPesadillasEdit.isSelected()) problemas.add("Pesadillas");
            if (chkApneaEdit.isSelected()) problemas.add("Apnea del sueño");
            if (chkNarcolepsiaEdit.isSelected()) problemas.add("Narcolepsia");
            
            usuario.actualizarDatos(nombre, edad, genero, peso, altura, problemas);
            JOptionPane.showMessageDialog(this, "Perfil actualizado exitosamente");
            cardLayout.show(mainPanel, "menu");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos");
        }
    }
    
    private void crearPanelAnalisis() {
        analisisPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Análisis del Sueño", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        analisisPanel.add(lblTitulo, BorderLayout.NORTH);
        
        JTextArea txtAreaAnalisis = new JTextArea();
        txtAreaAnalisis.setEditable(false);
        txtAreaAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAreaAnalisis.setMargin(new Insets(20, 20, 20, 20));
        analisisPanel.add(new JScrollPane(txtAreaAnalisis), BorderLayout.CENTER);
        
        JPanel botonesPanel = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        botonesPanel.add(btnVolver);
        analisisPanel.add(botonesPanel, BorderLayout.SOUTH);
    }
    
    private void actualizarAnalisis() {
        JTextArea txtArea = (JTextArea) ((JScrollPane) analisisPanel.getComponent(1)).getViewport().getView();
        String analisisTexto = analisis.mostrarTendencia();
        analisisTexto += "\n\nPromedio de horas: " + String.format("%.2f", analisis.promedioHoras());
        analisisTexto += "\nPromedio de calidad: " + String.format("%.2f", analisis.promedioCalidad());
        
        // Agregar análisis de IMC
        double imc = usuario.getPeso() / (usuario.getAltura() * usuario.getAltura());
        analisisTexto += "\n\n--- Análisis Físico ---";
        analisisTexto += "\nIMC: " + String.format("%.2f", imc);
        
        if (imc < 18.5) {
            analisisTexto += " (Bajo peso - puede afectar la calidad del sueño)";
        } else if (imc < 25) {
            analisisTexto += " (Peso normal - favorable para el sueño)";
        } else if (imc < 30) {
            analisisTexto += " (Sobrepeso - puede causar problemas de sueño)";
        } else {
            analisisTexto += " (Obesidad - alto riesgo de apnea del sueño)";
        }
        
        // Agregar problemas de sueño identificados
        if (!usuario.getProblemasSueno().isEmpty()) {
            analisisTexto += "\n\n--- Problemas de Sueño Reportados ---";
            for (String problema : usuario.getProblemasSueno()) {
                analisisTexto += "\n• " + problema;
            }
        }
        
        txtArea.setText(analisisTexto);
    }
    
    private void crearPanelRecomendaciones() {
        recomendacionesPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Recomendaciones Personalizadas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        recomendacionesPanel.add(lblTitulo, BorderLayout.NORTH);
        
        JTextArea txtAreaRecomendaciones = new JTextArea();
        txtAreaRecomendaciones.setEditable(false);
        txtAreaRecomendaciones.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaRecomendaciones.setMargin(new Insets(20, 20, 20, 20));
        txtAreaRecomendaciones.setLineWrap(true);
        txtAreaRecomendaciones.setWrapStyleWord(true);
        recomendacionesPanel.add(new JScrollPane(txtAreaRecomendaciones), BorderLayout.CENTER);
        
        JPanel botonesPanel = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        botonesPanel.add(btnVolver);
        recomendacionesPanel.add(botonesPanel, BorderLayout.SOUTH);
    }
    
    private void actualizarRecomendaciones() {
        JTextArea txtArea = (JTextArea) ((JScrollPane) recomendacionesPanel.getComponent(1)).getViewport().getView();
        String recomendaciones = analisis.generarRecomendaciones(usuario);
        
        // Agregar recomendaciones basadas en IMC
        double imc = usuario.getPeso() / (usuario.getAltura() * usuario.getAltura());
        recomendaciones += "\n--- Recomendaciones basadas en tu perfil físico ---\n";
        
        if (imc >= 30) {
            recomendaciones += "• Tu IMC indica obesidad. Esto aumenta significativamente el riesgo de apnea del sueño.\n";
            recomendaciones += "• Considera perder peso mediante dieta balanceada y ejercicio.\n";
            recomendaciones += "• Evita dormir boca arriba para reducir ronquidos.\n";
            recomendaciones += "• Consulta a un especialista del sueño si roncas mucho.\n";
        } else if (imc >= 25) {
            recomendaciones += "• El sobrepeso puede afectar la calidad del sueño.\n";
            recomendaciones += "• Evita comidas pesadas 3 horas antes de dormir.\n";
            recomendaciones += "• El ejercicio regular mejora el sueño, pero no lo hagas muy tarde.\n";
        } else if (imc < 18.5) {
            recomendaciones += "• Un peso muy bajo puede causar problemas para dormir.\n";
            recomendaciones += "• Asegúrate de consumir calorías suficientes durante el día.\n";
            recomendaciones += "• Consulta con un nutricionista para mejorar tu alimentación.\n";
        }
        
        // Recomendaciones específicas por problemas de sueño
        List<String> problemas = usuario.getProblemasSueno();
        if (!problemas.isEmpty()) {
            recomendaciones += "\n--- Recomendaciones para tus problemas de sueño ---\n";
            
            if (problemas.contains("Insomnio")) {
                recomendaciones += "\n[Insomnio]\n";
                recomendaciones += "• Establece un horario fijo para dormir y despertar.\n";
                recomendaciones += "• Evita cafeína después de las 2 PM.\n";
                recomendaciones += "• Crea un ambiente oscuro, fresco y silencioso.\n";
                recomendaciones += "• Practica técnicas de relajación antes de dormir.\n";
                recomendaciones += "• Si no puedes dormir en 20 minutos, levántate y realiza una actividad tranquila.\n";
            }
            
            if (problemas.contains("Pesadillas")) {
                recomendaciones += "\n[Pesadillas]\n";
                recomendaciones += "• Evita contenido perturbador antes de dormir (películas de terror, noticias, etc.).\n";
                recomendaciones += "• Reduce el estrés mediante meditación o yoga.\n";
                recomendaciones += "• Mantén un diario de sueños para identificar patrones.\n";
                recomendaciones += "• Si son frecuentes, consulta a un psicólogo especializado.\n";
            }
            
            if (problemas.contains("Apnea del sueño")) {
                recomendaciones += "\n[Apnea del sueño]\n";
                recomendaciones += "• IMPORTANTE: Consulta a un médico especialista en sueño.\n";
                recomendaciones += "• Evita el alcohol y sedantes antes de dormir.\n";
                recomendaciones += "• Duerme de lado en lugar de boca arriba.\n";
                recomendaciones += "• Si tienes sobrepeso, la pérdida de peso puede mejorar los síntomas.\n";
                recomendaciones += "• Puede ser necesario usar un dispositivo CPAP.\n";
            }
            
            if (problemas.contains("Narcolepsia")) {
                recomendaciones += "\n[Narcolepsia]\n";
                recomendaciones += "• Programa siestas cortas (15-20 min) durante el día.\n";
                recomendaciones += "• Mantén un horario de sueño muy regular.\n";
                recomendaciones += "• Evita actividades peligrosas si tienes somnolencia excesiva.\n";
                recomendaciones += "• Consulta con un neurólogo especializado en trastornos del sueño.\n";
                recomendaciones += "• Informa a familiares y empleadores sobre tu condición.\n";
            }
        }
        
        txtArea.setText(recomendaciones);
    }
    
    private void crearPanelGrafica() {
        graficaPanel = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Gráfica de Evolución del Sueño", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        graficaPanel.add(lblTitulo, BorderLayout.NORTH);
        
        grafica = new Grafica(new ArrayList<Double>(), new ArrayList<Double>(), new ArrayList<String>());
        graficaPanel.add(grafica, BorderLayout.CENTER);
        
        JPanel botonesPanel = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        botonesPanel.add(btnVolver);
        graficaPanel.add(botonesPanel, BorderLayout.SOUTH);
    }
    
    private void actualizarGrafica() {
        List<Double> horas = new ArrayList<>();
        List<Double> calidad = new ArrayList<>();
        List<String> fechas = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        
        for (RegistroSueno r : analisis.getRegistros()) {
            horas.add((double) r.getHorasSueno());
            calidad.add((double) r.getCalidadSueno());
            fechas.add(r.getFecha().format(formatter));
        }
        
        grafica.actualizarDatos(new ArrayList<>(horas), new ArrayList<>(calidad), new ArrayList<>(fechas));
    }
}