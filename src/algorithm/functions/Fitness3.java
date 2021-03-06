package algorithm.functions;

import java.util.ArrayList;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import auxiliar.binary.Gen;
import auxiliar.binary.GenRange;
import algorithm.population.Poblacion;

public class Fitness3 extends Function {

	public Fitness3() {
        super(true, "function binary 3", 2, new ArrayList<>());
		ranges.add(new GenRange(-512.0, 512.0));
        ranges.add(new GenRange(-512.0, 512.0));
	}

	@Override
	public double fitnessInstance(Individuo cromosoma, Poblacion poblacion) {
		double gen1 = ((Gen) cromosoma.get(0)).getFenotipo();
        double gen2 = ((Gen) cromosoma.get(1)).getFenotipo();
		
		return -(gen2 + 47)*Math.sin(Math.sqrt(Math.abs(gen2 + gen1/2 + 47)))
				- gen1*Math.sin(Math.sqrt(Math.abs(gen1 - (gen2 + 47))));
	}

	@Override
	public double bloatingInstance(Individuo individuo, Poblacion poblacion) {
		// TODO Auto-generated method stub
		return individuo.getAptitudRevisada();
	}

}
