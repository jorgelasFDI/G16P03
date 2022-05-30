package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import auxiliar.binary.GenRange;

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
