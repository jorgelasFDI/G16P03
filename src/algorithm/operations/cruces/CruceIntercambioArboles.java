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

		IndividuoTree padre1 = (IndividuoTree) individuoPrev;
		IndividuoTree padre2 = (IndividuoTree) individuo;

		int num_nodos = Math.min(padre1.getSize(), padre2.getSize());
 		int nodo_cruce1 = MyRandom.getRandomInt(1, num_nodos - 1);
 		int nodo_cruce2 = MyRandom.getRandomInt(1, num_nodos - 1);
 		
 		padre1.swapGen(nodo_cruce1, nodo_cruce2, padre2); 
	}

}
