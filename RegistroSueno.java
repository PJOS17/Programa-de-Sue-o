import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroSueno {
    private Usuario persona;
    private LocalDate fecha;
    private LocalTime horaDormir;
    private LocalTime horaDespertar;
    private int calidadSueno;
    private String observaciones;

    public RegistroSueno(Usuario persona, LocalDate fecha, LocalTime horaDormir, LocalTime horaDespertar,
                         int calidadSueno, String observaciones) {
        this.persona = persona;
        this.fecha = fecha;
        this.horaDormir = horaDormir;
        this.horaDespertar = horaDespertar;
        this.calidadSueno = calidadSueno;
        this.observaciones = observaciones;
    }

    public int calcularDuracion() {
        Duration d = Duration.between(horaDormir, horaDespertar);
        return (int) d.toHours();
    }

    public String mostrarResumen() {
        return "Registro de sueño (" + fecha + "):\n" +
               "Hora de dormir: " + horaDormir +
               "\nHora de despertar: " + horaDespertar +
               "\nDuración: " + calcularDuracion() + " horas" +
               "\nCalidad: " + calidadSueno +
               "\nObservaciones: " + observaciones;
    }

    public int getCalidadSueno() { return calidadSueno; }
    public String getObservaciones() { return observaciones; }
}
