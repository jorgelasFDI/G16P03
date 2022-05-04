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
        IndividuoTree individuo = (IndividuoTree) cromosoma;	
		for(int i = 0; i < LogicalNode.combinaciones.size(); i++) {
			if (execFunction(individuo.get(0), LogicalNode.combinaciones.get(i), individuo) == Binary.toBool(LogicalNode.solution.get(i)))
				total++;
		}
		// double value = ((double) total) / (double) LogicalNode.combinaciones.size();
		double value = total;
		return bloating(individuo.get(0), (double) total);
    }
    
    /*private double bloating(IndividuoTree individuo, double total) {
    	// double n = 5;            //Con este parï¿½metro indicamos que la mitad de los individuos grandes(con mï¿½s nodos que la media) muere
    	
    	//Si el arbol actual tiene tamaï¿½o mï¿½s grande que la media de la poblaciï¿½n, entonces tiene un 50% de probabilidades de morir
    	if(individuo.get(0).depth() > individuo.getMaxDepth() && MyRandom.getInstance().nextDouble() < 0.5) {
    		return 0;
    	} return total;
    }*/
    
    private double bloating(Tree<String> cromosoma, double total) { 
    	//Si la correlacion es buena entre las diferentes profundidades de los individuos no se modificará
    	//la profundidad, en cambio si es mala se penalizará al individuo
    	double correlacion = Poblacion.getCorrelacion();
    	
    	//Solo penaliza si la correlación es negativa
    	if(correlacion < 0)
    		return total + correlacion * cromosoma.depth();
    	
    	return total;
    }
}
