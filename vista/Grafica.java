package vista;

import model.RegistroSueno;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grafica extends JPanel {
    private List<RegistroSueno> registros;
    private List<Double> horasSueno;
    private List<Double> calidadSueno;
    private List<String> fechas;

    // Constructor original
    public Grafica(List<RegistroSueno> registros) {
        this.registros = registros;
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.WHITE);
    }

    // Constructor que GUISueno espera
    public Grafica(ArrayList<Double> horasSueno, ArrayList<Double> calidadSueno, ArrayList<String> fechas) {
        this.horasSueno = horasSueno;
        this.calidadSueno = calidadSueno;
        this.fechas = fechas;
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ((registros == null || registros.isEmpty()) && (horasSueno == null || horasSueno.isEmpty())) {
            g.drawString("No hay datos para mostrar.", 50, 50);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.drawString("Calidad del sueño por día", 50, 20);

        int width = getWidth();
        int height = getHeight();
        int margin = 50;
        int graphHeight = height - 2 * margin;
        int graphWidth = width - 2 * margin;

        int barWidth;
        int maxCalidad = 10;

        if (registros != null && !registros.isEmpty()) {
            barWidth = graphWidth / registros.size();
            for (int i = 0; i < registros.size(); i++) {
                RegistroSueno r = registros.get(i);
                int calidad = r.getCalidadSueno();
                int barHeight = (int) ((double) calidad / maxCalidad * graphHeight);

                int x = margin + i * barWidth;
                int y = height - margin - barHeight;

                g2.setColor(new Color(33, 150, 243));
                g2.fillRect(x, y, barWidth - 5, barHeight);

                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(calidad), x + 5, y - 5);
            }
        } else if (horasSueno != null && !horasSueno.isEmpty()) {
            barWidth = graphWidth / horasSueno.size();
            for (int i = 0; i < horasSueno.size(); i++) {
                double calidad = calidadSueno.get(i);
                int barHeight = (int) (calidad / maxCalidad * graphHeight);

                int x = margin + i * barWidth;
                int y = height - margin - barHeight;

                g2.setColor(new Color(76, 175, 80));
                g2.fillRect(x, y, barWidth - 5, barHeight);

                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(calidad), x + 5, y - 5);
            }
        }
    }

    public void actualizarDatos(List<RegistroSueno> nuevosRegistros) {
        this.registros = nuevosRegistros;
        repaint();
    }

    public void actualizarDatos(ArrayList<Double> horasSueno, ArrayList<Double> calidadSueno, ArrayList<String> fechas) {
    this.horasSueno = horasSueno;
    this.calidadSueno = calidadSueno;
    this.fechas = fechas;
    this.registros = null; // Desactivamos el modo de registros
    repaint();
}
}