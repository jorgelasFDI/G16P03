package algorithm.functions;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import algorithm.population.Poblacion;
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
        int averageSize;
        IndividuoTree individuo = (IndividuoTree) cromosoma;	
		for(int i = 0; i < LogicalNode.combinaciones.size(); i++) {
			if (execFunction(individuo.get(0), LogicalNode.combinaciones.get(i), individuo) == Binary.toBool(LogicalNode.solution.get(i)))
				total++;
		}
		return total;
		//return bloating(individuo.get(0), total);
    }
    
    private double bloating(Tree<String> cromosoma, int total) {
    	int n = 5;            //Con este parámetro indicamos que la mitad de los individuos grandes(con más nodos que la media) muere
    	
    	//Si el arbol actual tiene tamaño más grande que la media de la población, entonces tiene un 50% de probabilidades de morir
    	if(cromosoma.depth() > (Poblacion.getAverageDepth()) && MyRandom.getInstance().nextDouble() > (1 / n)) {
    		return (total / 2);
    	}
    	
    	return total;
    }
}
