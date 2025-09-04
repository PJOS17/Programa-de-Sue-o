import java.util.List;

public class Grafica {
    
    public static String generarGraficaTexto(AnalisisSueno analisis) {
        if (analisis == null || analisis.getRegistros().isEmpty()) {
            return "No hay datos para mostrar la gráfica.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- GRÁFICA DE SUEÑO (MODO TEXTO) ---\n");
        sb.append("Leyenda: ■ = 1 hora de sueño, ★ = 1 punto de calidad\n");
        sb.append("-----------------------------------------\n");
        
        for (RegistroSueno registro : analisis.getRegistros()) {
            String fecha = registro.getFecha().toString();
            int horas = registro.getHorasSueno();
            int calidad = registro.getCalidadSueno();

            String barraHoras = repetirCaracter('■', horas);
            String barraCalidad = repetirCaracter('★', calidad);
            
            sb.append(String.format("%s: %s (%d hrs)%n", fecha, barraHoras, horas));
            sb.append(String.format("Calidad: %s (%d/10)%n", barraCalidad, calidad));
            sb.append("--------------------------------------\n");
        }

        double promHoras = analisis.promedioHoras();
        double promCalidad = analisis.promedioCalidad();
        
        sb.append("PROMEDIOS:\n");
        sb.append(String.format("Horas: %s (%.1f hrs)%n", 
                         repetirCaracter('■', (int) Math.round(promHoras)), promHoras));
        sb.append(String.format("Calidad: %s (%.1f/10)%n", 
                         repetirCaracter('★', (int) Math.round(promCalidad)), promCalidad));
        
        return sb.toString();
    }
    
    public static String generarGraficaTendencia(AnalisisSueno analisis) {
        if (analisis == null || analisis.getRegistros().isEmpty()) {
            return "No hay datos para mostrar la gráfica de tendencia.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- GRÁFICA DE TENDENCIA DE SUEÑO ---\n");
                int maxRegistros = Math.min(7, analisis.getRegistros().size());
        int maxHoras = 12;
        
        for (int i = 0; i < maxRegistros; i++) {
            RegistroSueno registro = analisis.getRegistros().get(i);
            String fecha = registro.getFecha().toString();
            int horas = registro.getHorasSueno();
            int calidad = registro.getCalidadSueno();
            
            int longitudBarra = (int) Math.round((double) horas / maxHoras * 50);
            
            sb.append(String.format("%s: %s %d hrs | Calidad: %d/10%n", 
                             fecha, 
                             repetirCaracter('█', longitudBarra), 
                             horas, 
                             calidad));
        }
        sb.append("\nEscala: Cada █ representa aproximadamente ");
        sb.append(String.format("%.1f", maxHoras / 50.0));
        sb.append(" horas\n");
        
        return sb.toString();
    }
    
    public static String generarGraficaAvanzada(AnalisisSueno analisis) {
        if (analisis == null || analisis.getRegistros().isEmpty()) {
            return "No hay datos para mostrar la gráfica avanzada.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n══════════════════════════════════════════════\n");
        sb.append("           GRÁFICA AVANZADA DE SUEÑO           \n");
        sb.append("══════════════════════════════════════════════s\n");
        sb.append("FECHA       HORAS  BARRAS DE SUEÑO      CALIDAD\n");
        sb.append("────────────────────────────────────────────────\n");
        
        for (RegistroSueno registro : analisis.getRegistros()) {
            String fecha = registro.getFecha().toString();
            int horas = registro.getHorasSueno();
            int calidad = registro.getCalidadSueno();
            
            String barraHoras = crearBarraProgreso(horas, 12, '█');
            String barraCalidad = crearBarraProgreso(calidad, 10, '★');
            
            sb.append(String.format("%s  %2d hrs  %-20s  %s%n", 
                             fecha, horas, barraHoras, barraCalidad));
        }
        
        return sb.toString();
    }
    
    private static String crearBarraProgreso(int valor, int max, char caracter) {
        int longitud = (int) Math.round((double) valor / max * 20);
        return repetirCaracter(caracter, longitud) + 
               repetirCaracter('░', 20 - longitud) +
               " " + valor + "/" + max;
    }
    
    private static String repetirCaracter(char c, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}