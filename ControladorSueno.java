import java.time.LocalDate;
import java.time.LocalTime;

public class ControladorSueno {
    private Usuario usuario;
    private AnalisisSueno analisis;
    private VistaSueno vista;

    public ControladorSueno(VistaSueno vista) {
        this.vista = vista;
        this.analisis = new AnalisisSueno();
        crearUsuario();
    }

    private void crearUsuario() {
        vista.mostrarMensaje("Ingrese datos del usuario:");
        String nombre = vista.leerNombre();
        int edad = vista.leerEdad();
        String genero = vista.leerGenero();
        double peso = vista.leerPeso();
        double altura = vista.leerAltura();

        usuario = new Usuario(nombre, edad, genero, peso, altura);
        vista.mostrarMensaje("Usuario creado correctamente.\n");
    }

    private void editarUsuario() {
        vista.mostrarMensaje("Actualizar datos del usuario:");
        String nombre = vista.leerNombre();
        int edad = vista.leerEdad();
        String genero = vista.leerGenero();
        double peso = vista.leerPeso();
        double altura = vista.leerAltura();

        usuario.actualizarDatos(nombre, edad, genero, peso, altura);
        vista.mostrarMensaje("Perfil actualizado correctamente.\n");
    }

    private void registrarSueno() {
        vista.mostrarMensaje("Registrar nuevo sueño:");
        LocalDate fecha = vista.leerFecha();
        LocalTime dormir = vista.leerHoraDormir();
        LocalTime despertar = vista.leerHoraDespertar();
        int calidad = vista.leerCalidad();
        String obs = vista.leerObservaciones();

        RegistroSueno registro = new RegistroSueno(usuario, fecha, dormir, despertar, calidad, obs);
        analisis.agregarRegistro(registro);
        vista.mostrarMensaje("Registro agregado correctamente.\n");
    }

    public void ejecutar() {
        boolean salir = false;
        while (!salir) {
            int op = vista.mostrarMenu();
            switch (op) {
                case 1: 
                    registrarSueno(); 
                    break;
                case 2:
                    for (RegistroSueno r : analisis.getRegistros())
                        vista.mostrarMensaje(r.mostrarResumen());
                    break;
                case 3: 
                    vista.mostrarMensaje(usuario.mostrarPerfil()); 
                    break;
                case 4: 
                    editarUsuario(); 
                    break;
                case 5: 
                    vista.mostrarMensaje(analisis.mostrarTendencia()); 
                    break;
                case 6: 
                    vista.mostrarMensaje(analisis.generarRecomendaciones(usuario)); 
                    break;
                case 7: 
                    // Mostrar gráfica usando JavaFX
                    Grafica.mostrarGrafica(analisis);
                    break;
                case 8: 
                    salir = true; 
                    vista.mostrarMensaje("Saliendo del programa..."); 
                    break;
                default: 
                    vista.mostrarMensaje("Opción inválida. Intente nuevamente."); 
                    break;
            }
        }
    }
}