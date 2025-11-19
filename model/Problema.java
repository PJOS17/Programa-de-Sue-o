package model;

import java.util.HashMap;
import java.util.Map;

public class Problema {
    private String problemaDetectado;
    private static final Map<String, Integer> penalizaciones = new HashMap<>();

    // Inicializamos las penalizaciones para cada problema
    static {
        penalizaciones.put("Insomnio", 3);
        penalizaciones.put("Pesadillas", 2);
        penalizaciones.put("Apnea del sueño", 4);
        penalizaciones.put("Narcolepsia", 3);
        penalizaciones.put("Sin problemas", 0);
    }

    public Problema(String problemaDetectado) {
        this.problemaDetectado = problemaDetectado;
    }

    public String getProblemaDetectado() {
        return problemaDetectado;
    }

    public int getPenalizacion() {
        return penalizaciones.getOrDefault(problemaDetectado, 0);
    }

    public static Map<String, Integer> getListaProblemas() {
        return penalizaciones;
    }
}
