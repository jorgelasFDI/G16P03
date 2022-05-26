package algorithm.operations.mutaciones;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import algorithm.individuos.gen.Gen;
import algorithm.individuos.gen.GenRange;
import algorithm.operations.Operation;

public class MutacionUniforme extends Mutacion {

    public static final String name = "Mutacion Uniforme";

	public MutacionUniforme() {
		super.name = name; 
    }

    @Override
    public void mutar(Individuo individuo) {
        GenRange range;
        Double gen;
        IndividuoReal real = (IndividuoReal) individuo;
        for (int j = 0; j < real.getSize(); j++) {
            gen = real.get(j);
            range = gen.getRange();
            for (int z = 0; z < gen.getSize(); z++) {
                individuo.setBit(z, random.nextDouble()*(range.getMax() - range.getMin()) + range.getMin());
            }
        }
    }

}
