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
    private Problema problema;

    public RegistroSueno(Usuario usuario, LocalDate fecha, LocalTime horaDormir,
                         LocalTime horaDespertar, int calidad, String observaciones, Problema problema) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.horaDormir = horaDormir;
        this.horaDespertar = horaDespertar;
        this.calidad = calidad;
        this.observaciones = observaciones;
        this.problema = problema;
    }

    public Usuario getUsuario() { return usuario; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraDormir() { return horaDormir; }
    public LocalTime getHoraDespertar() { return horaDespertar; }
    public String getObservaciones() { return observaciones; }
    public Problema getProblema() { return problema; }

    public double getHorasSueno() {
        Duration dur = Duration.between(horaDormir, horaDespertar);

        double horas = dur.toHours();
        double minutos = dur.toMinutes() % 60;  // Java 8 compatible

        return horas + (minutos / 60.0);
    }

    public int getCalidadSueno() {
        int ajustada = calidad - problema.getPenalizacion();
        return Math.max(ajustada, 0);
    }

    public String mostrarResumen() {
        return "Usuario: " + usuario.getNombre() +
               "\nFecha: " + fecha +
               "\nHoras dormidas: " + String.format("%.2f", getHorasSueno()) +
               "\nCalidad ajustada: " + getCalidadSueno() +
               "\nProblema: " + problema.getProblemaDetectado() +
               "\nObservaciones: " + observaciones;
    }
}
