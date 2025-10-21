import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class VistaSueno {
    private final Scanner sc = new Scanner(System.in);

    
    private static final String VERDE = "\u001B[32m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String ROJO = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    
    public int mostrarMenu() {
        int opcion;
        do {
            mostrarTitulo("MENÚ PRINCIPAL");
            System.out.println("1. Registrar nuevo sueño");
            System.out.println("2. Ver registros anteriores");
            System.out.println("3. Ver perfil del usuario");
            System.out.println("4. Editar perfil del usuario");
            System.out.println("5. Ver análisis del sueño");
            System.out.println("6. Ver recomendaciones personalizadas");
            System.out.println("7. Ver gráfica de sueño");
            System.out.println("8. Salir");

            opcion = leerEntero("Seleccione una opción", 1, 8);
        } while (opcion < 1 || opcion > 8);

        return opcion;
    }

    
    public String leerNombre() {
        System.out.print("Nombre: ");
        return sc.nextLine().trim();
    }

    public int leerEdad() {
        return leerEntero("Edad", 1, 110);
    }

    public String leerGenero() {
        String genero;
        do {
            System.out.print("Género (hombre/mujer): ");
            genero = sc.nextLine().trim().toLowerCase();
            if (!genero.equals("hombre") && !genero.equals("mujer")) {
                System.out.println(ROJO + "Ingrese 'hombre' o 'mujer'." + RESET);
            }
        } while (!genero.equals("hombre") && !genero.equals("mujer"));
        return genero;
    }

    public double leerPeso() {
        return leerDouble("Peso (kg)", 30, 300);
    }

    public double leerAltura() {
        return leerDouble("Altura (m)", 0.5, 2.5);
    }

    
    public LocalDate leerFecha() {
        while (true) {
            System.out.print("Fecha (YYYY-MM-DD): ");
            String entrada = sc.nextLine().trim();
            try {
                return LocalDate.parse(entrada);
            } catch (Exception e) {
                System.out.println(ROJO + "Formato de fecha inválido. Ejemplo: 2025-10-20" + RESET);
            }
        }
    }

    public LocalTime leerHoraDormir() {
        return leerHora("Hora de dormir");
    }

    public LocalTime leerHoraDespertar() {
        return leerHora("Hora de despertar");
    }

    private LocalTime leerHora(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (HH:MM): ");
            String entrada = sc.nextLine().trim();
            try {
                return LocalTime.parse(entrada);
            } catch (Exception e) {
                System.out.println(ROJO + "Formato de hora inválido. Ejemplo: 23:15" + RESET);
            }
        }
    }

    public int leerCalidad() {
        return leerEntero("Calidad del sueño (1-10)", 1, 10);
    }

    public String leerObservaciones() {
        System.out.print("Observaciones: ");
        return sc.nextLine().trim();
    }

    
    private int leerEntero(String mensaje, int min, int max) {
        int valor;
        do {
            System.out.print(mensaje + " (" + min + "-" + max + "): ");
            while (!sc.hasNextInt()) {
                sc.next(); // limpiar entrada
                System.out.print("Ingrese un número válido (" + min + "-" + max + "): ");
            }
            valor = sc.nextInt();
            sc.nextLine();
            if (valor < min || valor > max) {
                System.out.println(ROJO + "Valor fuera de rango." + RESET);
            }
        } while (valor < min || valor > max);
        return valor;
    }

    private double leerDouble(String mensaje, double min, double max) {
        double valor;
        do {
            System.out.print(mensaje + " (" + min + "-" + max + "): ");
            while (!sc.hasNextDouble()) {
                sc.next(); // limpiar entrada
                System.out.print("Ingrese un número válido (" + min + "-" + max + "): ");
            }
            valor = sc.nextDouble();
            sc.nextLine();
            if (valor < min || valor > max) {
                System.out.println(ROJO + "Valor fuera de rango." + RESET);
            }
        } while (valor < min || valor > max);
        return valor;
    }

    
    public void mostrarMensaje(String msg) {
        System.out.println(AMARILLO + msg + RESET);
    }

    public void mostrarTitulo(String titulo) {
        System.out.println(VERDE + "\n=== " + titulo + " ===" + RESET);
    }

    
    public static void main(String[] args) {
        VistaSueno vista = new VistaSueno();
        ControladorSueno app = new ControladorSueno(vista);
        app.ejecutar();
    }
}
