import java.time.LocalTime;

public class RegistroSueno {
        private Usuario persona;
        private LocalTime horaDormir;
        private int horaSueno;
        private int calidadSueno;
        private String observaciones;

public RegistroSueno(Usuario persona, LocalTime horaDormir, int horaSueno, int calidadSueno, String observaciones) {
        this.persona = persona;
        this.horaDormir = horaDormir;
        this.horaSueno = horaSueno;
        this.calidadSueno = calidadSueno;
        this.observaciones = observaciones;
}

public int calcularDuracion() {
        return horaSueno;
}

public String mostrarResumen() {
        return "Resumen de sueño:\n" +
        "Usuario: " + persona.getNombre() + "\n" +
        "Hora de dormir: " + horaDormir + "\n" +
        "Horas dormidas: " + horaSueno + "\n" +
        "Calidad del sueño: " + calidadSueno + "/10\n" +
        "Observaciones: " + observaciones;
}

public LocalTime getHoraDormir() {
        return horaDormir;
}

public int getHoraSueno() {
        return horaSueno;
}

public int getCalidad() {
        return calidadSueno;
}

        public String getObservaciones() {
        return observaciones;
}

        public Usuario getPersona() {
        return persona;
}
}
