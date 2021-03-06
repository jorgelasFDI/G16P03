package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;
import auxiliar.tree.Tree;

public class MutacionFunctional extends Mutacion {
	
	public static final String name = "Mutacion funcional";

	public MutacionFunctional() {
		super.name = name; 
	} 
	
	@Override
	public void mutar(Individuo individuo) {
		
 		IndividuoTree tIndividuo = (IndividuoTree) individuo;
		Tree<String> node = tIndividuo.getRandomNode("NODE", 0, individuo.getSize() - 1);

		if(node != null) {
			String key = MyRandom.getRandomValueInSet(LogicalNode.funciones.keySet());
			while (LogicalNode.funcionesOperandos.get(key) != tIndividuo.getObject(node.data).index) {
				key = MyRandom.getRandomValueInSet(LogicalNode.funciones.keySet());
			}; node.data = key;
		}	
	}

}
