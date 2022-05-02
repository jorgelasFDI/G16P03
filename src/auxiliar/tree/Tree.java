package auxiliar.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import auxiliar.MyMath;

public class Tree<T> implements Iterable<Tree<T>> {

	public T data;
	public int index;
	public Tree<T> parent;
	public List<Tree<T>> children;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	List<Tree<T>> elementsIndex;

	public Tree() {
		this.data = null;
		this.children = new LinkedList<Tree<T>>();
		this.elementsIndex = new LinkedList<Tree<T>>();
		this.elementsIndex.add(this);
		this.index = 0;
	}

	public Tree(T data) {
		this.data = data;
		this.children = new LinkedList<Tree<T>>();
		this.elementsIndex = new LinkedList<Tree<T>>();
		this.elementsIndex.add(this);
	}

	public Tree(Tree<T> other) {
		this(other.data);
		Iterator<Tree<T>> childrenIter = other.children.iterator();
		while (childrenIter.hasNext()) {
			addChild(childrenIter.next());
		}
	}

	private Tree<T> addChild(Tree<T> other) {
		Tree<T> childNode = new Tree<T>(other);
		childNode.parent = this;
		childNode.index = children.size();
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public Tree<T> addChild(T child) {

		if (this.isRoot() && this.data == null) {
			this.data = child;
			return this;
		}

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

	public int depth() {
		if (this.isRoot())
			return 1;
		return parent.depth() + 1;
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

	public Tree<T> get(int idx) {
		Iterator<Tree<T>> iter = iterator();
		for (int i = 0; i < idx; i++)
			iter.next();
		return iter.next();
	}

	public Tree<T> set(int idx, Tree<T> other) {
		Tree<T> node = get(idx);
		node.parent.setChild(other, node.index);
		return node;
	}
	
	public Tree<T> getChild(int i) {
		if (children.size() <= i || i < 0) return null; 
		return children.get(i);
	}
	
	public void setChild(Tree<T> other, int idx){       //idx puede ser 0, 1, 2
		children.set(idx, other);
		other.index = idx;
		other.parent = this;
	}

	public int numNodos() {
		if (children.isEmpty()) return 1;
		int value = 1;
		for(Tree<T> child: children){
			value += child.numNodos();
		} return value;
	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

	@Override
	public Iterator<Tree<T>> iterator() {
		return new TreeIter<T>(this);
	}

}
