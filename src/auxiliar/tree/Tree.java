package auxiliar.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree<T> implements Iterable<Tree<T>> {

	public T data;
	public int index;
	public Tree<T> parent;
	public List<Tree<T>> children;
	public int depth;
	public int numNodos;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	private List<Tree<T>> elementsIndex;

	public Tree(T data) {
		this.data = data;
		this.children = new LinkedList<Tree<T>>();
		this.elementsIndex = new LinkedList<Tree<T>>();
		this.elementsIndex.add(this);
		this.numNodos = 1;
	}

	public Tree<T> addChild(T child) {
		Tree<T> childNode = new Tree<T>(child);
		childNode.parent = this;
		childNode.index = children.size();
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public boolean hasChildren() {
		return children.size() == 0 ? false : true;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(Tree<T> node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public Tree<T> findTreeNode(Comparable<T> cmp) {
		for (Tree<T> element : this.elementsIndex) {
			T data = element.data;
			if (cmp.compareTo(data) == 0)
				return element;
		} return null;
	}
	
	public int getNumNodos(){
		return numNodos;
	}
	
	public void updateNumNodos(int n){
		numNodos += n;
	}
	
	public Tree<T> getGen(int idx){
		return children.get(idx);
	}
	
	public void setGen(Tree<T> other, int idx){       //idx puede ser 0, 1, 2
		children.set(idx, other);
	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

	@Override
	public Iterator<Tree<T>> iterator() {
		TreeIter<T> iter = new TreeIter<T>(this);
		return iter;
	}

	public Tree<T> get(int i) {
		if (children.size() <= i || i < 0) return null; 
		return children.get(i);
	}

}
