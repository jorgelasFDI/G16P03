package algorithm.operations.cruces;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.MyRandom;

public class CruceIntercambioArboles extends Cruce {
	public static final String name = "Cruce por intercambio de �rboles";

	public CruceIntercambioArboles() {
		super.name = name; 
	}
	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {

		double prob = 0.9;

		IndividuoTree padre1 = (IndividuoTree) individuoPrev;
		IndividuoTree padre2 = (IndividuoTree) individuo;

		int nodo_cruce1 = MyRandom.getRandomInt(1, padre1.getSize() - 1);
 		int nodo_cruce2 = MyRandom.getRandomInt(1, padre2.getSize() - 1);
 		
 		padre1.swapGen(nodo_cruce1, nodo_cruce2, padre2);
	}

}
