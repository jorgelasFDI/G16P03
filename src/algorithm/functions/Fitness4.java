package algorithm.functions;

import java.util.ArrayList;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import algorithm.individuos.IndividuoReal;
import auxiliar.binary.Gen;
import auxiliar.binary.GenRange;
import algorithm.population.Poblacion;


public class Fitness4 extends Function {

	public Fitness4() {
        super(true, "function real", null, new ArrayList<>());
		ranges.add(new GenRange(0, Math.PI));
	}

	@Override
	public double fitnessInstance(Individuo cromosoma, Poblacion poblacion) {
		double total = 0.0;
		int size = 0;
		if (cromosoma instanceof IndividuoBinary) {
			size = ((IndividuoBinary) cromosoma).getNumGenes();
		} else if (cromosoma instanceof IndividuoReal) {
			size = cromosoma.getSize();
		}

		for(int i = 0; i < size; i++) {
			total += Math.sin(((Gen)cromosoma.get(i)).getFenotipo()) * Math.pow(Math.sin(((i + 2)
				   * Math.pow(((Gen)cromosoma.get(i)).getFenotipo(), 2))/Math.PI), 20);
		}
		
		return -total;
	}

	@Override
	public double bloatingInstance(Individuo individuo, Poblacion poblacion) {
		// TODO Auto-generated method stub
		return individuo.getAptitudRevisada();
	}

}
