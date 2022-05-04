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
		int nodoIdx = MyRandom.getRandomInt(0, individuo.getSize() - 1);
		Tree<String> nodo = individuoTree.get(nodoIdx);
		Tree<String> other = new Tree<>();
		try {
			IndividuoTree.init(other, nodo.getLevel(), individuoTree.getMaxDepth(), individuoTree.getBiFunction());
			other = other.get(0);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		nodo.parent.setChild(other, nodo.index);
		individuoTree.setSize(individuoTree.getSize() + other.numNodos() - nodo.numNodos());
	}

}
