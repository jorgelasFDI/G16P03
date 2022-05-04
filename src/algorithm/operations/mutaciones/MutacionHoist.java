package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.tree.Tree;

public class MutacionHoist extends Mutacion {
	
	public static final String name = "Mutacion Hoist";

	public MutacionHoist() {
		super.name = name; 
	} 
	@Override
	public void mutar(Individuo individuo) {
		IndividuoTree tIndividuo = (IndividuoTree) individuo;
		Tree<String> node = tIndividuo.get(0);
		if(individuo.getSize() > 1) {
			node = tIndividuo.getRandomNode("NODE", 1, individuo.getSize() - 1);
			if(node != null) {
				node.parent = null;
				tIndividuo.setIterable(node);
				tIndividuo.setSize(node.numNodos());
			}
		}
	}
	
}
