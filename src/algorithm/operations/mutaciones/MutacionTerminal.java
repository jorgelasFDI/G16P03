package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;
import auxiliar.tree.Tree;

public class MutacionTerminal extends Mutacion {
	
	private double prob = 0.25;
	
	public static final String name = "Mutacion terminal";

	public MutacionTerminal() {
		super.name = name; 
	} 
	
	@Override
	public void mutar(Individuo individuo) {
		
		IndividuoTree tIndividuo = (IndividuoTree) individuo;
		
		Tree<String> node = tIndividuo.getRandomNode("LEAF");
		node.data = MyRandom.getRandomValueInSet(LogicalNode.terminales.keySet());

	}

}
