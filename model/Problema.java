package model;
    import java.util.ArrayList;

    public class Problema {
        private ArrayList<String> problemasSueno;

        public Problema() {
            problemasSueno = new ArrayList<>();
            problemasSueno.add("Insomnio");
            problemasSueno.add("Pesadillas");
            problemasSueno.add("Apnea del sueño");
            problemasSueno.add("Narcolepsia");
        }

        public ArrayList<String> getProblemasSueno() {
            return problemasSueno;
        }
    }
