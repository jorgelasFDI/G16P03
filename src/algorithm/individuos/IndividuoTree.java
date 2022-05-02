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
		this.maxDepth = individuo.getMaxDepth();
		this.isLeaf = individuo.getBiFunction();
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
		return new Tree<String>((Tree<String>) iterable);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public Tree<String> get(int idx) {
		return ((Tree<String>) iterable).get(idx);
	}

	@Override
	public void set(int idx, Tree<String> other) {
		((Tree<String>) iterable).set(idx, other);
		this.size += other.depth() - ((Tree<String>) iterable).depth();
	}

	@Override
	public void swapGen(int idx, int j, Individuo<String, LogicalNode, Tree<String>> other) {
		Tree<String> otherChild = other.get(j);
		Tree<String> thisChild = this.get(idx);
		int auxIdx = otherChild.index;
		this.set(idx, otherChild);
		other.set(j, thisChild);
		thisChild.index = auxIdx;
	}

	// CUSTOM FUNCTIONS FOR THIS INDIVIDUAL

	private int maxDepth;
	private BiFunction<Integer, Integer, Boolean> isLeaf;

	public int getMaxDepth() {
		return maxDepth;
	}

	public BiFunction<Integer, Integer, Boolean> getBiFunction() {
		return isLeaf;
	}

	public void init(int depth, BiFunction<Integer, Integer, Boolean> isLeaf, Map<String, LogicalNode> map) {
		iterable = new Tree<>();
		setGenesToObjects(map);
		maxDepth = depth;
		this.isLeaf = isLeaf;
		size = IndividuoTree.init((Tree<String>) iterable, 1, depth, isLeaf);
	}

	public static int init(Tree<String> tree, int current, int depth, BiFunction<Integer, Integer, Boolean> isLeaf) {
	 
		if (isLeaf.apply(current, depth)) {
			Tree<String> child = tree.addChild(MyRandom.getRandomValueInSet(LogicalNode.terminales.keySet()));
			return 1;
		}

		Tree<String> child = tree.addChild(MyRandom.getRandomValueInSet(LogicalNode.funciones.keySet()));

		int value = 1;
		switch (child.data) {
			case "NOT":
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

	public Tree<String> getRandomNode(String type) {
		int nodoIdx = MyRandom.getRandomInt(1, size - 1); // 10 -> 5 pero que sea del tipo LEAF
		Iterator<Tree<String>> iter = iterator();
		Tree<String> nodo = null;
		int i = 0;
		while (i < nodoIdx || nodo == null && i < size) {
			Tree<String> other = iter.next();
			if (getObject(other.data).type == type) 
				nodo = other;
			i++;
		} return nodo;
	}
}
