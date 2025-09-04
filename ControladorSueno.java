import java.time.LocalDate;
import java.time.LocalTime;

public class ControladorSueno {
    private Usuario usuario;
    private AnalisisSueno analisis;
    private VistaSueno vistaSueno;

    // Constructor recibe la vista creada en mainSS
    public ControladorSueno(VistaSueno vistaSueno) {
        this.vistaSueno = vistaSueno;
        this.analisis = new AnalisisSueno();
        crearUsuario();
    }

    // Crear usuario pidiendo datos a la vista
    private void crearUsuario() {
        vistaSueno.mostrarMensaje("Ingrese los datos del usuario:");
        String nombre = vistaSueno.leerNombre();
        int edad = vistaSueno.leerEdad();
        String genero = vistaSueno.leerGenero();
        double peso = vistaSueno.leerPeso();
        double altura = vistaSueno.leerAltura();

        this.usuario = new Usuario(nombre, edad, genero, peso, altura);
        vistaSueno.mostrarMensaje("Usuario creado correctamente.\n");
    }

    // Editar perfil del usuario
    private void editarUsuario() {
        vistaSueno.mostrarMensaje("Actualizar datos del usuario:");
        String nombre = vistaSueno.leerNombre();
        int edad = vistaSueno.leerEdad();
        String genero = vistaSueno.leerGenero();
        double peso = vistaSueno.leerPeso();
        double altura = vistaSueno.leerAltura();

        usuario.actualizarDatos(nombre, edad, genero, peso, altura);
        vistaSueno.mostrarMensaje("Perfil actualizado correctamente.\n");
    }

    private void registrarSueno() {
        vistaSueno.mostrarMensaje("Registrar nuevo sueño:");
        LocalDate fecha = vistaSueno.leerFecha();
        LocalTime dormir = vistaSueno.leerHoraDormir();
        LocalTime despertar = vistaSueno.leerHoraDespertar();
        int calidad = vistaSueno.leerCalidad();
        String observaciones = vistaSueno.leerObservaciones();

        RegistroSueno registro = new RegistroSueno(usuario, fecha, dormir, despertar, calidad, observaciones);
        analisis.agregarRegistro(registro);
        vistaSueno.mostrarMensaje("Registro agregado correctamente.\n");
    }

    public void ejecutar() {
        boolean salir = false;

        while (!salir) {
            int opcion = vistaSueno.mostrarMenu();

            switch (opcion) {
                case 1:
                    registrarSueno();
                    break;
                case 2:
                    vistaSueno.mostrarMensaje(usuario.mostrarPerfil());
                    break;
                case 3:
                    editarUsuario();
                    break;
                case 4:
                    vistaSueno.mostrarMensaje(analisis.mostrarTendencia());
                    break;
                case 5:
                    vistaSueno.mostrarMensaje(analisis.generarRecomendaciones(usuario));
                    break;
                case 6:
                    vistaSueno.mostrarMensaje("Función de gráfica aún no implementada.");
                    break;
                case 7:
                    salir = true;
                    vistaSueno.mostrarMensaje("Saliendo del programa...");
                    break;
                default:
                    vistaSueno.mostrarMensaje("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
