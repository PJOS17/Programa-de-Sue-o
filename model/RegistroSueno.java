package model;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroSueno {
    private Usuario usuario;
    private LocalDate fecha;
    private LocalTime horaDormir;
    private LocalTime horaDespertar;
    private int horasSueno;
    private int calidadSueno;
    private String observaciones;

    public RegistroSueno(Usuario usuario, LocalDate fecha, LocalTime horaDormir,
                         LocalTime horaDespertar, int calidadSueno, String observaciones) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.horaDormir = horaDormir;
        this.horaDespertar = horaDespertar;
        this.horasSueno = calcularDuracion();
        this.calidadSueno = calidadSueno;
        this.observaciones = observaciones;
    }

    public int calcularDuracion() {
        Duration dur = Duration.between(horaDormir, horaDespertar);
        // Si la hora de despertar es antes que la hora de dormir, significa que durmió hasta el día siguiente
        if (dur.isNegative()) {
            dur = dur.plusHours(24);
        }
        return (int) dur.toHours();
    }

    public int getHorasSueno() { return horasSueno; }
    public int getCalidadSueno() { return calidadSueno; }
    public String getObservaciones() { return observaciones; }

    public String mostrarResumen() {
        return "Registro (" + fecha + "): Dormir: " + horaDormir + ", Despertar: " + horaDespertar +
               ", Horas dormidas: " + horasSueno + ", Calidad: " + calidadSueno +
               ", Observaciones: " + observaciones;
    }
    
    public LocalDate getFecha() {
    return fecha;
    }
}
