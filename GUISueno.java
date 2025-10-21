import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GUISueno extends JFrame {
    private AnalisisSueno analisis;
    private Grafica grafica;
    private JTextArea infoArea;

    public GUISueno(AnalisisSueno analisis) {
        this.analisis = analisis;
        setTitle("Análisis del Sueño");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        grafica = new Grafica(extraerHoras(), extraerCalidad(), extraerFechas());
        add(grafica, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnActualizar = new JButton("Actualizar Datos");
        JButton btnCerrar = new JButton("Cerrar");
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrar);

        btnActualizar.addActionListener(e -> actualizarGrafica());
        btnCerrar.addActionListener(e -> dispose());

        add(panelBotones, BorderLayout.SOUTH);

        infoArea = new JTextArea(5, 40);
        infoArea.setEditable(false);
        infoArea.setText(analisis.mostrarTendencia());
        add(new JScrollPane(infoArea), BorderLayout.EAST);
    }

    private void actualizarGrafica() {
        grafica.actualizarDatos(extraerHoras(), extraerCalidad(), extraerFechas());
        infoArea.setText(analisis.mostrarTendencia());
    }

    private List<Double> extraerHoras() {
        List<Double> lista = new ArrayList<>();
        for (RegistroSueno r : analisis.getRegistros()) lista.add((double) r.getHorasSueno());
        return lista;
    }

    private List<Double> extraerCalidad() {
        List<Double> lista = new ArrayList<>();
        for (RegistroSueno r : analisis.getRegistros()) lista.add((double) r.getCalidadSueno());
        return lista;
    }

    private List<String> extraerFechas() {
        List<String> lista = new ArrayList<>();
        for (RegistroSueno r : analisis.getRegistros()) lista.add(r.getFecha().toString());
        return lista;
    }
}


