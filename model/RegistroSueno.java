package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

public class RegistroSueno {
    private Usuario usuario;
    private LocalDate fecha;
    private LocalTime horaDormir;
    private LocalTime horaDespertar;
    private int calidad;
    private String observaciones;

    // Constructor que GUISueno espera
    public RegistroSueno(Usuario usuario, LocalDate fecha, LocalTime horaDormir, LocalTime horaDespertar, int calidad, String observaciones) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.horaDormir = horaDormir;
        this.horaDespertar = horaDespertar;
        this.calidad = calidad;
        this.observaciones = observaciones;
    }

    // Getters
    public Usuario getUsuario() { return usuario; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraDormir() { return horaDormir; }
    public LocalTime getHoraDespertar() { return horaDespertar; }
    public String getObservaciones() { return observaciones; }

    // Métodos esperados por GUISueno
    public double getHorasSueno() {
        return Duration.between(horaDormir, horaDespertar).toHours() +
               Duration.between(horaDormir, horaDespertar).toMinutesPart() / 60.0;
    }

    public int getCalidadSueno() {
        return calidad;
    }

    public String mostrarResumen() {
        return "Usuario: " + usuario.getNombre() +
               "\nFecha: " + fecha +
               "\nHoras dormidas: " + String.format("%.2f", getHorasSueno()) +
               "\nCalidad: " + calidad +
               "\nObservaciones: " + observaciones;
    }
}