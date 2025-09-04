import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class VistaSueno {
    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Registrar nuevo sueño");
        System.out.println("2. Ver perfil de usuario");
        System.out.println("3. Editar perfil de usuario");
        System.out.println("4. Ver análisis del sueño");
        System.out.println("5. Ver recomendaciones");
        System.out.println("6. Ver gráfica");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
        
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Ingrese un número válido: ");
        }
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    // Métodos para leer datos
    public String leerNombre() {
        System.out.print("Nombre: ");
        return sc.nextLine();
    }

    public int leerEdad() {
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        return edad;
    }

    public String leerGenero() {
        System.out.print("Género: ");
        return sc.nextLine();
    }

    public double leerPeso() {
        System.out.print("Peso (kg): ");
        double peso = sc.nextDouble();
        sc.nextLine();
        return peso;
    }

    public double leerAltura() {
        System.out.print("Altura (m): ");
        double altura = sc.nextDouble();
        sc.nextLine();
        return altura;
    }

    public LocalDate leerFecha() {
        System.out.print("Fecha (YYYY-MM-DD): ");
        return LocalDate.parse(sc.nextLine());
    }

    public LocalTime leerHoraDormir() {
        System.out.print("Hora dormir (HH:MM): ");
        return LocalTime.parse(sc.nextLine());
    }

    public LocalTime leerHoraDespertar() {
        System.out.print("Hora despertar (HH:MM): ");
        return LocalTime.parse(sc.nextLine());
    }

    public int leerCalidad() {
        System.out.print("Calidad (1-10): ");
        int calidad = sc.nextInt();
        sc.nextLine();
        return calidad;
    }

    public String leerObservaciones() {
        System.out.print("Observaciones: ");
        return sc.nextLine();
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        VistaSueno vista = new VistaSueno();
        ControladorSueno app = new ControladorSueno(vista); // Se pasa la vista al controlador
        app.ejecutar();
    }
}
