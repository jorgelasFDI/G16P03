package algorithm.operations.mutaciones;

import java.util.Iterator;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.Binary;
import auxiliar.tree.LogicalNode;
import auxiliar.tree.Tree;

public class MutacionTerminal extends Mutacion {
	
	private double prob = 0.25;
	
	public static final String name = "Mutacion terminal";

	public MutacionTerminal() {
		super.name = name; 
	} 
	
	@Override
	public void mutar(IndividuoTree individuo, Tree<String> root) {
		// TODO Auto-generated method stub
		LogicalNode obj = individuo.getObject(root.data);
		
		if (obj.type == "LEAF" && prob > random.nextDouble()) {
			obj
			return;
		}
		
		
        //Si el nodo es una funcion se mira en sus sub-arboles recursivamente
		//Boolean x = execFunction(root.get(0), combinacion, cromosoma);
		//Boolean y = execFunction(root.get(1), combinacion, cromosoma);
		//Boolean z = execFunction(root.get(2), combinacion, cromosoma);

	}

}
