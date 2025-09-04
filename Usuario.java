public class Usuario {
    private String nombre;
    private int edad;
    private String genero;
    private double peso;
    private double altura;

    public Usuario(String nombre, int edad, String genero, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
    }

    public String mostrarPerfil() {
        return "Perfil Usuario:\n" +
               "Nombre: " + nombre +
               "\nEdad: " + edad +
               "\nGénero: " + genero +
               "\nPeso: " + peso + " kg" +
               "\nAltura: " + altura + " m";
    }

    public void actualizarDatos(String nombre, int edad, String genero, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getGenero() { return genero; }
    public double getPeso() { return peso; }
    public double getAltura() { return altura; }
}
