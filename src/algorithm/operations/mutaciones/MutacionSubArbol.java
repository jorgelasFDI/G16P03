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
		IndividuoTree.init(other, individuoTree.get(nodoIdx).depth() + 1, individuoTree.getMaxDepth(), individuoTree.getBiFunction());
		individuoTree.set(nodoIdx, other);
	}

}
