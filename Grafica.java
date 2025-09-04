import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Grafica extends Application {
    private static AnalisisSueno analisis;

    public static void setAnalisis(AnalisisSueno a) {
        analisis = a;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gráfica de Sueño");

        // Eje X (categoría)
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Categoría");

        // Eje Y (valores numéricos)
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Valor");

        // Crear gráfico de barras
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Análisis del Sueño");

        // Crear serie de datos
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Promedios");

        // Agregar datos si el análisis no es null
        if (analisis != null) {
            series.getData().add(new XYChart.Data<>("Horas dormidas", analisis.calcularPromedioHorasDormidas()));
            series.getData().add(new XYChart.Data<>("Calidad del sueño", analisis.calcularPromedioCalidadSueno()));
        }

        // Añadir la serie al gráfico
        barChart.getData().add(series);

        // Crear escena
        Scene scene = new Scene(barChart, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void mostrarGrafica(AnalisisSueno a) {
        setAnalisis(a);
        launch();
    }
}
