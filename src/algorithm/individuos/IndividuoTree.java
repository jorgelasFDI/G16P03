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
	public Tree<String> get(int idx) {
		return ((Tree<String>) iterable).get(idx);
	}

	@Override
	public void set(int idx, Tree<String> other) {
		Tree<String> node = ((Tree<String>) iterable).set(idx, other);
		this.size += other.numNodos() - node.numNodos();
	}

	@Override
	public void swapGen(int idx, int j, Individuo<String, LogicalNode, Tree<String>> other) {
		Tree<String> otherChild = other.get(j);
		Tree<String> thisChild = this.get(idx);
		Tree<String> otherParent = otherChild.parent;
		Tree<String> thisParent = thisChild.parent;
		int auxIdx = otherChild.index;
		thisParent.setChild(otherChild, thisChild.index);
		otherParent.setChild(thisChild, auxIdx);
		this.size += otherChild.numNodos() - thisChild.numNodos();
		other.size += thisChild.numNodos() - otherChild.numNodos();
	}

	// CUSTOM FUNCTIONS FOR THIS INDIVIDUAL

	private double maxDepth;
	private BiFunction<Integer, Double, Boolean> isLeaf;
	private double mediaAptitud;

	public double getMaxDepth() {
		return maxDepth;
	}

	public BiFunction<Integer, Double, Boolean> getBiFunction() {
		return isLeaf;
	}

	public void init(double depth, BiFunction<Integer, Double, Boolean> isLeaf, Map<String, LogicalNode> map) {
		iterable = new Tree<>();
		setGenesToObjects(map);
		maxDepth = depth;
		this.isLeaf = isLeaf;
		try {
			size = IndividuoTree.init((Tree<String>) iterable, 1, depth, isLeaf);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public static int init(Tree<String> tree, int current, double depth, BiFunction<Integer, Double, Boolean> isLeaf) throws Exception {
	 
		if (isLeaf.apply(current, depth)) {
			Tree<String> child = tree.addChild(MyRandom.getRandomValueInSet(LogicalNode.terminales));
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

	public Tree<String> getRandomNode(String type, int min, int max) {
		int nodoIdx = MyRandom.getRandomInt(min, max); // 10 -> 5 pero que sea del tipo LEAF
		Iterator<Tree<String>> iter = iterator();
		Tree<String> nodo = null;
		int i = 0;
		while (i < nodoIdx || nodo == null && i < size) {
			Tree<String> other = iter.next();
			if (getObject(other.data).type == type || type == null)
				nodo = other;
			i++;
		} return nodo;
	}

	public void setMaxDepth(double depth) {
		maxDepth = depth;
	}

	public double getMediaAptitud() {
		return mediaAptitud;
	}

	public void setMediaAptitud(double mediaAptitud) {
		this.mediaAptitud = mediaAptitud;
	}
}
