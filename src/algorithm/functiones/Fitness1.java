package algorithm.functiones;

import java.util.ArrayList;
import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.Vuelo;

public class Fitness1 extends Fitness {

    public static final String name = "Funcion 1";

    public Fitness1() {
        super(true, name);
    }

    @Override
    public double fitnessInstance(Individuo cromosoma) {
        double total = 0.0;
        List<Double> anteriorTLA = new ArrayList<>(Vuelo.getNumPistas());
        for (int i = 0; i < Vuelo.getNumPistas(); i++) anteriorTLA.add(0.0);
        List<String> anteriorTipoAvion = new ArrayList<>();
        for(int i = 0; i < Vuelo.getNumPistas(); i++) {
        	anteriorTipoAvion.add("");
        }
        for(int gen: cromosoma) {

            Vuelo vuelo = cromosoma.getVuelo(gen);
            double TLA = vuelo.calculaTLA(anteriorTLA, anteriorTipoAvion);
            double retraso = vuelo.getMinRetraso();
            total += Math.pow(retraso, 2);

            anteriorTLA.set(vuelo.getPista(), TLA);
            anteriorTipoAvion.set(vuelo.getPista(), vuelo.getTipoAvion());
        }
        
        return total;
    }
    
}
