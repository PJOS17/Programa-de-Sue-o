package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private int edad;
    private String genero;
    private double peso;
    private double altura;
    private List<String> problemasSueno;

    public Usuario(String nombre, int edad, String genero, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.problemasSueno = new ArrayList<>();
    }

    public Usuario(String nombre, int edad, String genero, double peso, double altura, List<String> problemasSueno) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.problemasSueno = problemasSueno != null ? new ArrayList<>(problemasSueno) : new ArrayList<>();
    }

    public void actualizarDatos(String nombre, int edad, String genero, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
    }

    public void actualizarDatos(String nombre, int edad, String genero, double peso, double altura, List<String> problemasSueno) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.problemasSueno = problemasSueno != null ? new ArrayList<>(problemasSueno) : new ArrayList<>();
    }

    public double calcularIMC() {
        return peso / (altura * altura);
    }

    public String mostrarPerfil() {
        StringBuilder perfil = new StringBuilder();
        perfil.append("Perfil Usuario:\n");
        perfil.append("Nombre: ").append(nombre).append("\n");
        perfil.append("Edad: ").append(edad).append("\n");
        perfil.append("Género: ").append(genero).append("\n");
        perfil.append("Peso: ").append(peso).append(" kg\n");
        perfil.append("Altura: ").append(altura).append(" m\n");
        perfil.append("IMC: ").append(String.format("%.2f", calcularIMC()));

        double imc = calcularIMC();
        if (imc < 18.5) {
            perfil.append(" (Bajo peso)");
        } else if (imc < 25) {
            perfil.append(" (Peso normal)");
        } else if (imc < 30) {
            perfil.append(" (Sobrepeso)");
        } else {
            perfil.append(" (Obesidad)");
        }

        if (!problemasSueno.isEmpty()) {
            perfil.append("\n\nProblemas de sueño reportados:");
            for (String problema : problemasSueno) {
                perfil.append("\n• ").append(problema);
            }
        } else {
            perfil.append("\n\nNo ha reportado problemas de sueño.");
        }

        return perfil.toString();
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getGenero() { return genero; }
    public double getPeso() { return peso; }
    public double getAltura() { return altura; }
    public List<String> getProblemasSueno() { return new ArrayList<>(problemasSueno); }

    public void setProblemasSueno(List<String> problemasSueno) {
        this.problemasSueno = problemasSueno != null ? new ArrayList<>(problemasSueno) : new ArrayList<>();
    }

    public void agregarProblemaSueno(String problema) {
        if (!problemasSueno.contains(problema)) {
            problemasSueno.add(problema);
        }
    }

    public void eliminarProblemaSueno(String problema) {
        problemasSueno.remove(problema);
    }
}