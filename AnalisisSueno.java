import java.util.ArrayList;

public class AnalisisSueno {
    private ArrayList<RegistroSueno> registros;

    public AnalisisSueno() {
        registros = new ArrayList<>();
    }

    public void agregarRegistro(RegistroSueno r) {
        registros.add(r);
    }

    public double promedioHoras() {
        if (registros.isEmpty()) return 0;
        double suma = 0;
        for (RegistroSueno r : registros) suma += r.getHorasSueno();
        return suma / registros.size();
    }

    public double promedioCalidad() {
        if (registros.isEmpty()) return 0;
        double suma = 0;
        for (RegistroSueno r : registros) suma += r.getCalidadSueno();
        return suma / registros.size();
    }

    public String mostrarTendencia() {
        if (registros.isEmpty()) return "No hay registros aún.";

        int n = Math.min(3, registros.size());
        double inicioHoras = 0, finHoras = 0;
        double inicioCalidad = 0, finCalidad = 0;

        for (int i = 0; i < n; i++) {
            inicioHoras += registros.get(i).getHorasSueno();
            inicioCalidad += registros.get(i).getCalidadSueno();
        }

        for (int i = registros.size() - n; i < registros.size(); i++) {
            finHoras += registros.get(i).getHorasSueno();
            finCalidad += registros.get(i).getCalidadSueno();
        }

        inicioHoras /= n; inicioCalidad /= n;
        finHoras /= n; finCalidad /= n;

        StringBuilder tendencia = new StringBuilder("Tendencia del sueño:\n");
        tendencia.append(finHoras > inicioHoras ? "- Duración mejorando\n" :
                         finHoras < inicioHoras ? "- Duración disminuyendo\n" : "- Duración estable\n");
        tendencia.append(finCalidad > inicioCalidad ? "- Calidad mejorando\n" :
                         finCalidad < inicioCalidad ? "- Calidad disminuyendo\n" : "- Calidad estable\n");

        return tendencia.toString();
    }

    public String generarRecomendaciones(Usuario u) {
        StringBuilder rec = new StringBuilder("Recomendaciones:\n");
        double promHoras = promedioHoras();
        double promCalidad = promedioCalidad();

        if (promHoras < 6) rec.append("- Dormir al menos 7 horas.\n");
        else if (promHoras < 7) rec.append("- Descanso aceptable, mejorar.\n");
        else rec.append("- Buen trabajo, mantén tu rutina.\n");

        if (promCalidad < 5) rec.append("- Evita pantallas antes de dormir.\n");
        else if (promCalidad < 7) rec.append("- Prueba técnicas de relajación.\n");
        else rec.append("- Tu sueño es de buena calidad.\n");

        if (u.getEdad() > 60) rec.append("- Mantén horarios regulares y evita siestas largas.\n");
        if (u.getPeso() > 100 && promCalidad < 6)
            rec.append("- Consulta a un especialista sobre tu peso y sueño.\n");

        return rec.toString();
    }

    public ArrayList<RegistroSueno> getRegistros() { return registros; }
}
