import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class VistaSueno {
    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Registrar nuevo sueño");
        System.out.println("2. Ver registros anteriores");
        System.out.println("3. Ver perfil del usuario");
        System.out.println("4. Editar perfil del usuario");
        System.out.println("5. Ver análisis del sueño");
        System.out.println("6. Ver recomendaciones personalizadas");
        System.out.println("7. Ver gráfica de sueño");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
        
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Ingrese un número válido: ");
        }
        int opcion = sc.nextInt();
        sc.nextLine(); 
        return opcion;
    }

    public String leerNombre() {
        System.out.print("Nombre: ");
        return sc.nextLine();
    }

    public int leerEdad() {
        int edad;
        do {
            System.out.print("Edad (1-110): ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.print("Ingrese una edad válida (1-110): ");
            }
            edad = sc.nextInt();
            sc.nextLine();
        } while (edad <= 0 || edad > 110);
        return edad;
    }

    public String leerGenero() {
        String genero;
        do {
            System.out.print("Género (hombre/mujer): ");
            genero = sc.nextLine().trim().toLowerCase();
        } while (!genero.equals("hombre") && !genero.equals("mujer"));
        return genero;
    }

    public double leerPeso() {
        double peso;
        do {
            System.out.print("Peso (30-300 kg): ");
            while (!sc.hasNextDouble()) {
                sc.next();
                System.out.print("Ingrese un peso válido (30-300 kg): ");
            }
            peso = sc.nextDouble();
            sc.nextLine();
        } while (peso < 30 || peso > 300);
        return peso;
    }

    public double leerAltura() {
        double altura;
        do {
            System.out.print("Altura (0.5-2.5 m): ");
            while (!sc.hasNextDouble()) {
                sc.next();
                System.out.print("Ingrese una altura válida (0.5-2.5 m): ");
            }
            altura = sc.nextDouble();
            sc.nextLine();
        } while (altura < 0.5 || altura > 2.5);
        return altura;
    }

    public LocalDate leerFecha() {
        LocalDate fecha = null;
        boolean valido = false;
        do {
            System.out.print("Fecha (YYYY-MM-DD): ");
            String entrada = sc.nextLine();
            try {
                fecha = LocalDate.parse(entrada);
                valido = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        } while (!valido);
        return fecha;
    }

    public LocalTime leerHoraDormir() {
        LocalTime hora = null;
        boolean valido = false;
        do {
            System.out.print("Hora dormir (HH:MM): ");
            String entrada = sc.nextLine();
            try {
                hora = LocalTime.parse(entrada);
                valido = true;
            } catch (Exception e) {
                System.out.println("Formato de hora inválido. Intente nuevamente.");
            }
        } while (!valido);
        return hora;
    }

    public LocalTime leerHoraDespertar() {
        LocalTime hora = null;
        boolean valido = false;
        do {
            System.out.print("Hora despertar (HH:MM): ");
            String entrada = sc.nextLine();
            try {
                hora = LocalTime.parse(entrada);
                valido = true;
            } catch (Exception e) {
                System.out.println("Formato de hora inválido. Intente nuevamente.");
            }
        } while (!valido);
        return hora;
    }

    public int leerCalidad() {
        int calidad;
        do {
            System.out.print("Calidad del sueño (1-10): ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.print("Ingrese una calidad válida (1-10): ");
            }
            calidad = sc.nextInt();
            sc.nextLine();
        } while (calidad < 1 || calidad > 10);
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
        ControladorSueno app = new ControladorSueno(vista);
        app.ejecutar();
    }
}