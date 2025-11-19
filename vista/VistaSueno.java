package vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Map;

import model.Problema;

public class VistaSueno {
    private Scanner sc;

    public VistaSueno() {
        sc = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerNombre() {
        System.out.print("Nombre: ");
        return sc.nextLine();
    }

    public int leerEdad() {
        System.out.print("Edad: ");
        return Integer.parseInt(sc.nextLine());
    }

    public String leerGenero() {
        System.out.print("Género: ");
        return sc.nextLine();
    }

    public double leerPeso() {
        System.out.print("Peso (kg): ");
        return Double.parseDouble(sc.nextLine());
    }

    public double leerAltura() {
        System.out.print("Altura (m): ");
        return Double.parseDouble(sc.nextLine());
    }

    public LocalDate leerFecha() {
        System.out.print("Fecha (YYYY-MM-DD): ");
        return LocalDate.parse(sc.nextLine());
    }

    public LocalTime leerHoraDormir() {
        System.out.print("Hora de dormir (HH:MM): ");
        return LocalTime.parse(sc.nextLine());
    }

    public LocalTime leerHoraDespertar() {
        System.out.print("Hora de despertar (HH:MM): ");
        return LocalTime.parse(sc.nextLine());
    }

    public int leerCalidad() {
        System.out.print("Calidad del sueño (0-10): ");
        return Integer.parseInt(sc.nextLine());
    }

    public String leerObservaciones() {
        System.out.print("Observaciones: ");
        return sc.nextLine();
    }

    public int mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Registrar sueño");
        System.out.println("2. Ver registros");
        System.out.println("3. Ver perfil");
        System.out.println("4. Editar perfil");
        System.out.println("5. Ver tendencia");
        System.out.println("6. Ver recomendaciones");
        System.out.println("7. Ver gráfica");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(sc.nextLine());
    }

    // Nuevo método para leer problema
    public String leerProblema() {
        System.out.println("Problemas de sueño disponibles:");
        int i = 1;
        for (Map.Entry<String, Integer> entry : Problema.getListaProblemas().entrySet()) {
            System.out.println(i + ". " + entry.getKey() + " (penalización: -" + entry.getValue() + ")");
            i++;
        }
        System.out.print("Seleccione el número del problema: ");
        int opcion = Integer.parseInt(sc.nextLine());

        // Convertir la opción en el problema seleccionado
        String[] problemas = Problema.getListaProblemas().keySet().toArray(new String[0]);
        if (opcion >= 1 && opcion <= problemas.length) {
            return problemas[opcion - 1];
        } else {
            System.out.println("Opción inválida. Se asignará 'Sin problemas'.");
            return "Sin problemas";
        }
    }
}

