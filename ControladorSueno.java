import java.util.ArrayList;

public class ControladorSueno {
    private ArrayList<RegistroSueno> registros;

    public ControladorSueno() {
        registros = new ArrayList<>();
    }

    public void agregarRegistro(RegistroSueno registro) {
        registros.add(registro);
    }

    public ArrayList<RegistroSueno> obtenerRegistros() {
        return registros;
    }

    public double calcularPromedioHorasDormidas() {
        if (registros.isEmpty()) return 0;
        int totalHoras = 0;
        for (RegistroSueno r : registros) {
            totalHoras += r.getHoraSueno();
        }
        return (double) totalHoras / registros.size();
    }

    public double calcularPromedioCalidadSueno() {
        if (registros.isEmpty()) return 0;
        int totalCalidad = 0;
        for (RegistroSueno r : registros) {
            totalCalidad += r.getCalidad();
        }
        return (double) totalCalidad / registros.size();
    }
}
