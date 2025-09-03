import java.util.ArrayList;
import java.util.List;

public class Problema {

    
    private List<String> problemaSueno;

    public Problema(String problemaSueno) {
        this.problemaSueno = new ArrayList<>();
        this.problemaSueno.add(problemaSueno);
    }
    public static Problema newProblemaSuenoInsomnio() {
        return new Problema("Insomnio");
    }

    public static Problema newProblemaSuenoPesadillas() {
        return new Problema("Pesadillas");
    }

    public static Problema newProblemaSuenoApnea() {
        return new Problema("Apnea de sueño");
    }

    public static Problema newProblemaSuenoNarcolepsia() {
        return new Problema("Narcolepsia");
    }

    public List<String> getProblemaSueno() {
        return problemaSueno;
    }
    public void agregarProblema(String problema) {
        this.problemaSueno.add(problema);
    }
}