package algorithm.functions;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.Binary;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;
import auxiliar.tree.Tree;

public class Function1 extends Function {
	
    public Function1() {
        super(false, "Fitness 1");
    }

    public Boolean execFunction(Tree<String> root, List<Integer> combinacion, IndividuoTree cromosoma) {

		if (root == null)
			return null;

        LogicalNode obj = cromosoma.getObject(root.data);

		if (obj.type == "LEAF")
			return Binary.toBool(combinacion.get(obj.index));
		
        //Si el nodo es una funcion se mira en sus sub-arboles recursivamente
		Boolean x = execFunction(root.getChild(0), combinacion, cromosoma);
		Boolean y = execFunction(root.getChild(1), combinacion, cromosoma);
		Boolean z = execFunction(root.getChild(2), combinacion, cromosoma);

		return obj.ternary(x, y, z);
	}

    @Override
    public double fitnessInstance(Individuo cromosoma) {
        int total = 0;
        IndividuoTree individuo = (IndividuoTree) cromosoma;	
		for(int i = 0; i < LogicalNode.combinaciones.size(); i++) {
			if (execFunction(individuo.get(0), LogicalNode.combinaciones.get(i), individuo) == Binary.toBool(LogicalNode.solution.get(i)))
				total++;
		} return total;
    }
    
}
