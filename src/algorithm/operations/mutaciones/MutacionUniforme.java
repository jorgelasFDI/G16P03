package algorithm.operations.mutaciones;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import algorithm.individuos.gen.Gen;
import algorithm.individuos.gen.GenRange;
import algorithm.operations.Operation;
import algorithm.population.Poblacion;

public class MutacionUniforme extends Mutacion {

    public static final String name = "Mutacion Uniforme";

	public MutacionUniforme() {
		super.name = name; 
    }

    @Override
    public void mutar(Individuo individuo) {
        IndividuoReal real = (IndividuoReal) individuo;
        for (int j = 0; j < real.getSize(); j++) {
            GenRange range = real.getObject((double) j);
            real.set(j, random.nextDouble()*(range.getMax() - range.getMin()) + range.getMin());
        }
    }

}
