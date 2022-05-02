package algorithm.operations.mutaciones;

import java.util.Iterator;
import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import algorithm.operations.Operation;
import auxiliar.tree.Tree;

public abstract class Mutacion extends Operation {

    @Override
    public void operationInstance(List<Individuo> poblacion) {
        for(int i = 0; i < poblacion.size(); i++) {  //(int i = 0; i < size; i++)
			Individuo individuo = poblacion.get(i);
			IndividuoTree ind = (IndividuoTree) individuo;

	        Tree<String> root = null;
	        Iterator<Tree<String>> iter = individuo.iterator();
	        if (iter.hasNext() && iter.hasNext()) root = iter.next();
	        
			if (random.nextDouble() < prob) {
                mutar(ind, root);
                individuo.setAptitud(individuo.fitness());
			}
        }
    }

    public abstract void mutar(IndividuoTree individuo, Tree<String> root);
    
}
