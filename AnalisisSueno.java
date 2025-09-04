import java.util.ArrayList;

public class AnalisisSueno {
    private ArrayList<RegistroSueno> registros;

    public AnalisisSueno() {
        registros = new ArrayList<>();
    }

    public void agregarRegistro(RegistroSueno registro) {
        registros.add(registro);
    }

    public double calcularPromedioHorasDormidas() {
        if (registros.isEmpty()) return 0;
        double total = 0;
        for (RegistroSueno r : registros) {
            total += r.calcularDuracion();
        }
        return total / registros.size();
    }

    public double calcularPromedioCalidadSueno() {
        if (registros.isEmpty()) return 0;
        double total = 0;
        for (RegistroSueno r : registros) {
            total += r.getCalidadSueno();
        }
        return total / registros.size();
    }

    public String generarRecomendaciones(Usuario u) {
        StringBuilder rec = new StringBuilder("Recomendaciones:\n");
        double promHoras = calcularPromedioHorasDormidas();
        double promCalidad = calcularPromedioCalidadSueno();

        if (promHoras < 6) rec.append("- Intenta dormir al menos 7 horas.\n");
        else if (promHoras < 7) rec.append("- Tu descanso es aceptable, pero podrías mejorarlo.\n");
        else rec.append("- Buen trabajo, mantén tu rutina.\n");

        if (promCalidad < 5) rec.append("- Evita pantallas antes de dormir.\n");
        else if (promCalidad < 7) rec.append("- Prueba técnicas de relajación.\n");
        else rec.append("- Tu sueño es de buena calidad.\n");

        if (u.getEdad() > 60) rec.append("- A tu edad, mantén horarios regulares y evita siestas largas.\n");
        if (u.getPeso() > 100 && promCalidad < 6)
            rec.append("- Tu peso podría estar afectando el sueño, consulta a un especialista.\n");

        return rec.toString();
    }
    public String mostrarTendencia() {
    if (registros.isEmpty()) return "No hay registros para mostrar tendencia.";

    int n = Math.min(3, registros.size()); // Usamos las primeras y últimas 3 entradas (o menos si hay menos registros)

    double promedioInicioHoras = 0;
    double promedioFinalHoras = 0;
    double promedioInicioCalidad = 0;
    double promedioFinalCalidad = 0;

    // Promedio primeras n entradas
    for (int i = 0; i < n; i++) {
        promedioInicioHoras += registros.get(i).calcularDuracion();
        promedioInicioCalidad += registros.get(i).getCalidadSueno();
    }
    promedioInicioHoras /= n;
    promedioInicioCalidad /= n;

    // Promedio últimas n entradas
    for (int i = registros.size() - n; i < registros.size(); i++) {
        promedioFinalHoras += registros.get(i).calcularDuracion();
        promedioFinalCalidad += registros.get(i).getCalidadSueno();
    }
    promedioFinalHoras /= n;
    promedioFinalCalidad /= n;

    StringBuilder tendencia = new StringBuilder("Tendencia de tu sueño:\n");

    if (promedioFinalHoras > promedioInicioHoras)
        tendencia.append("- La duración de tu sueño está mejorando.\n");
    else if (promedioFinalHoras < promedioInicioHoras)
        tendencia.append("- La duración de tu sueño ha disminuido.\n");
    else
        tendencia.append("- La duración de tu sueño se mantiene estable.\n");

    if (promedioFinalCalidad > promedioInicioCalidad)
        tendencia.append("- La calidad de tu sueño está mejorando.\n");
    else if (promedioFinalCalidad < promedioInicioCalidad)
        tendencia.append("- La calidad de tu sueño ha disminuido.\n");
    else
        tendencia.append("- La calidad de tu sueño se mantiene estable.\n");

    return tendencia.toString();
}

}
