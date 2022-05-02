package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import auxiliar.MyRandom;
import auxiliar.tree.Tree;

public class MutacionSubArbol extends Mutacion {
	
	public static final String name = "Mutacion por sub-�rbol";

	public MutacionSubArbol() {
		super.name = name; 
	} 
	
	@Override
	public void mutar(Individuo individuo) {
		IndividuoTree individuoTree = (IndividuoTree) individuo;
		int nodoIdx = MyRandom.getRandomInt(1, individuo.getSize() - 1);
		Tree<String> other = new Tree<>();
		Tree<String> nodo = individuoTree.get(nodoIdx);
		IndividuoTree.init(other, nodo.depth(), individuoTree.getMaxDepth(), individuoTree.getBiFunction());
		nodo.parent.setChild(other, nodo.index);
		individuoTree.setSize(individuoTree.getSize() + other.numNodos() - nodo.numNodos());
	}

}
