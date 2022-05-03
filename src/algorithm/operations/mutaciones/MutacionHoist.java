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
		// TODO Auto-generated method stub
		IndividuoTree tIndividuo = (IndividuoTree) individuo;
		Tree<String> node = tIndividuo.get(0);
		if(individuo.getSize() > 1) {
			node = tIndividuo.getRandomNode("NODE");
			if(node != null) {
				tIndividuo.set(0, node);
			}
		}
	}
	
}
