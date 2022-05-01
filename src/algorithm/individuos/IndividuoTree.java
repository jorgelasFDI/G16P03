package algorithm.individuos;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;

import algorithm.functions.Function;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;
import auxiliar.tree.Tree;

public class IndividuoTree extends Individuo<String, LogicalNode, Tree<String>> {

	// FUNCTIONS IMPLEMENTED BY EVERY INDIVIDUAL

	public IndividuoTree(IndividuoTree individuo) {
		super(individuo);
	}

	public IndividuoTree(Function function) {
		super(function);
	}

	@Override
	public IndividuoTree copy() {
		return new IndividuoTree(this);
	}

	@Override
    public Tree<String> copyGenes() {
		return null;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void swapGen(int idx, int j, Individuo<String, LogicalNode, Tree<String>> other) {
		Iterator<Tree<String>> thisIter = iterable.iterator();
		Iterator<Tree<String>> otherIter = other.iterator();
		
		for (int i = 0; i <= idx; i++) thisIter.hasNext();
		Tree<String> thisChild = thisIter.next();
		Tree<String> thisParent = thisChild.parent;

		for (int i = 0; i <= j; i++) otherIter.hasNext();
		Tree<String> otherChild = otherIter.next();
		Tree<String> otherParent = otherChild.parent;

		int aux = otherChild.index;
		otherChild.index = thisChild.index;
		thisChild.index = aux;

		otherParent.setGen(thisChild, thisChild.index);
		thisParent.setGen(otherChild, otherChild.index);
	}

	// CUSTOM FUNCTIONS FOR THIS INDIVIDUAL

	public void init(int depth, BiFunction<Integer, Integer, Boolean> isLeaf, Map<String, LogicalNode> map) {
		iterable = new Tree<>(null);
		setGenesToObjects(map);
		size = IndividuoTree.init((Tree<String>) iterable, 1, depth, isLeaf);
	}

	private static int init(Tree<String> tree, int current, int depth, BiFunction<Integer, Integer, Boolean> isLeaf) {
	
		if (isLeaf.apply(current, depth)) {
			tree.addChild(MyRandom.getRandomValueInSet(LogicalNode.terminales.keySet()));
			return 1;
		}

		Tree<String> child = tree.addChild(MyRandom.getRandomValueInSet(LogicalNode.funciones.keySet()));

		int value = 1;
		switch (child.data) {
			case "NOT":
				tree.updateNumNodos(child.getNumNodos());
                value += init(child, current + 1, depth, isLeaf);
				break;
			case "IF":
                value += init(child, current + 1, depth, isLeaf);           
                value += init(child, current + 1, depth, isLeaf);
                value += init(child, current + 1, depth, isLeaf);
				break;
			default:
				value += init(child, current + 1, depth, isLeaf);
				value += init(child, current + 1, depth, isLeaf);
				break;
		} return value;
	}
}
