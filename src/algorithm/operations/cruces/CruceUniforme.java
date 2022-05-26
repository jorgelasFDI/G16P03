package algorithm.operations.cruces;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class CruceUniforme extends Cruce {

    public static final String name = "Cruce Uniforme";

	public CruceUniforme() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        for (int j = 0; j < individuo.getSize(); j++) {
            if (random.nextBoolean()) {
                individuoPrev.swapGen(j, individuo);
            }
        }
    }

}
