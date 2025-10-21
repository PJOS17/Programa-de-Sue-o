import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Grafica extends JPanel {
    private List<Double> horas;
    private List<Double> calidad;
    private List<String> fechas;

    public Grafica(List<Double> horas, List<Double> calidad, List<String> fechas) {
        this.horas = horas;
        this.calidad = calidad;
        this.fechas = fechas;
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (horas == null || horas.isEmpty()) {
            g.drawString("No hay datos suficientes para mostrar la gráfica.", 20, 20);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int margen = 50;

        double maxHoras = horas.stream().max(Double::compareTo).orElse(8.0);
        double maxCalidad = calidad.stream().max(Double::compareTo).orElse(10.0);

        int n = horas.size();
        int espacio = (w - 2 * margen) / Math.max(1, n - 1);

        // Ejes
        g2.drawLine(margen, h - margen, w - margen, h - margen);
        g2.drawLine(margen, margen, margen, h - margen);

        // Líneas de horas (azul)
        g2.setColor(new Color(66, 133, 244));
        for (int i = 0; i < n - 1; i++) {
            int x1 = margen + i * espacio;
            int x2 = margen + (i + 1) * espacio;
            int y1 = (int) (h - margen - (horas.get(i) / maxHoras) * (h - 2 * margen));
            int y2 = (int) (h - margen - (horas.get(i + 1) / maxHoras) * (h - 2 * margen));
            g2.drawLine(x1, y1, x2, y2);
            g2.fillOval(x1 - 3, y1 - 3, 6, 6);
        }

        // Líneas de calidad (rojo)
        g2.setColor(new Color(219, 68, 55));
        for (int i = 0; i < n - 1; i++) {
            int x1 = margen + i * espacio;
            int x2 = margen + (i + 1) * espacio;
            int y1 = (int) (h - margen - (calidad.get(i) / maxCalidad) * (h - 2 * margen));
            int y2 = (int) (h - margen - (calidad.get(i + 1) / maxCalidad) * (h - 2 * margen));
            g2.drawLine(x1, y1, x2, y2);
            g2.fillOval(x1 - 3, y1 - 3, 6, 6);
        }

        // Fechas en el eje X
        g2.setColor(Color.BLACK);
        for (int i = 0; i < n; i++) {
            int x = margen + i * espacio;
            g2.drawString(fechas.get(i), x - 15, h - 20);
        }

        // Leyenda
        g2.setColor(Color.BLACK);
        g2.drawString("Horas de sueño", margen + 20, 30);
        g2.setColor(new Color(66, 133, 244));
        g2.fillRect(margen, 20, 10, 10);

        g2.setColor(Color.BLACK);
        g2.drawString("Calidad del sueño", margen + 150, 30);
        g2.setColor(new Color(219, 68, 55));
        g2.fillRect(margen + 130, 20, 10, 10);
    }

    public void actualizarDatos(List<Double> horas, List<Double> calidad, List<String> fechas) {
        this.horas = horas;
        this.calidad = calidad;
        this.fechas = fechas;
        repaint();
    }
}
