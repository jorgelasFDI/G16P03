package algorithm.operations.cruces;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class CruceMonopunto extends Cruce {

    public static final String name = "Cruce Monopunto";

	public CruceMonopunto() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        int indice = random.nextInt(individuo.getSize());
        for (int j = 0; j < indice; j++) {
            individuoPrev.swapGen(j, individuo);
        }
    }

}
