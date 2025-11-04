package model;

import java.util.ArrayList;

public class Problema {
    private ArrayList<String> problemasSueno;
    private String problemaDetectado;

    public Problema() {
        problemasSueno = new ArrayList<>();
        problemasSueno.add("Insomnio");
        problemasSueno.add("Pesadillas");
        problemasSueno.add("Apnea del sueño");
        problemasSueno.add("Narcolepsia");
        problemasSueno.add("Sin problemas");
        problemaDetectado = "Sin problemas";
    }

    public ArrayList<String> getProblemasSueno() {
        return problemasSueno;
    }

    public String getProblemaDetectado() {
        return problemaDetectado;
    }

    public void setProblema(String problema) {
        this.problemaDetectado = problema;
    }
}