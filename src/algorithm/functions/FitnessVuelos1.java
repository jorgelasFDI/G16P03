package algorithm.functions;

import java.util.ArrayList;
import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;
import auxiliar.vuelo.Vuelo;
import algorithm.population.Poblacion;

public class FitnessVuelos1 extends Function {

    public static final String name = "Funcion 1";

    public FitnessVuelos1() {
        super(true, name, null, null);
    }

    @Override
    public double fitnessInstance(Individuo individuo, Poblacion poblacion) {
        
        IndividuoVuelo cromosoma = (IndividuoVuelo) individuo;

        double total = 0.0;
        List<Double> anteriorTLA = new ArrayList<>(Vuelo.getNumPistas());
        for (int i = 0; i < Vuelo.getNumPistas(); i++) anteriorTLA.add(0.0);
        List<String> anteriorTipoAvion = new ArrayList<>();
        for(int i = 0; i < Vuelo.getNumPistas(); i++) {
        	anteriorTipoAvion.add("");
        }
        for(int gen: cromosoma) {

            Vuelo vuelo = cromosoma.getObject(gen);
            double TLA = vuelo.calculaTLA(anteriorTLA, anteriorTipoAvion);
            double retraso = vuelo.getMinRetraso();
            total += Math.pow(retraso, 2);

            anteriorTLA.set(vuelo.getPista(), TLA);
            anteriorTipoAvion.set(vuelo.getPista(), vuelo.getTipoAvion());
        }
        
        return total;
    }

    @Override
    public double bloatingInstance(Individuo individuo, Poblacion poblacion) {
        // TODO Auto-generated method stub
        return individuo.getAptitudRevisada();
    }
    
}
