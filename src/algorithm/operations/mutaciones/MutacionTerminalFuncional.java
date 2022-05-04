package algorithm.operations.mutaciones;

import java.util.Set;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;
import auxiliar.tree.Tree;

public class MutacionTerminalFuncional extends Mutacion {
	
	public static final String name = "Mutacion terminal-funcional";

	public MutacionTerminalFuncional() {
		super.name = name; 
	} 
	
	@Override
	public void mutar(Individuo individuo) {
		
 		IndividuoTree tIndividuo = (IndividuoTree) individuo;
		Tree<String> node = tIndividuo.getRandomNode(null, 0, individuo.getSize() - 1);

		String key;
		if (LogicalNode.funciones.containsKey(node.data)) {
			key = MyRandom.getRandomValueInSet(LogicalNode.funciones.keySet());
			while (LogicalNode.funcionesOperandos.get(key) != tIndividuo.getObject(node.data).index) {
				key = MyRandom.getRandomValueInSet(LogicalNode.funciones.keySet());
			};
		} else key = MyRandom.getRandomValueInSet(LogicalNode.terminales.keySet());
		node.data = key;
			
	}

}
