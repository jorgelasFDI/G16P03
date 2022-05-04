package auxiliar.tree;

import java.util.Iterator;

public class TreeIter<T> implements Iterator<Tree<T>> {

	enum ProcessStages {
		ProcessParent, ProcessChildCurNode, ProcessChildSubNode
	}

	public TreeIter(Tree<T> treeNode) {
		if (treeNode.isRoot()) 
			treeNode = treeNode.children.get(0);
		hasNext = true;
		this.childrenCurNodeIter = treeNode.children.iterator();
		this.childrenSubNodeIter = null;
		this.next = treeNode;
	}

	private ProcessStages doNext;
	private boolean hasNext;
	private Tree<T> next;
	private Iterator<Tree<T>> childrenCurNodeIter;
	private Iterator<Tree<T>> childrenSubNodeIter;

	public boolean hasNext() {
		return hasNext;
	}

	public Tree<T> next() {
		if (!hasNext) return null;
		Tree<T> current = next;

		doNext = null; hasNext = false;
		if (childrenSubNodeIter != null && childrenSubNodeIter.hasNext()) {
			doNext = ProcessStages.ProcessChildSubNode;
			hasNext = true;
		} else if (childrenCurNodeIter.hasNext()) {
			doNext = ProcessStages.ProcessChildCurNode;
			hasNext = true;
		}

		if (doNext == ProcessStages.ProcessChildCurNode) 
			childrenSubNodeIter = childrenCurNodeIter.next().iterator();
		next = hasNext ? childrenSubNodeIter.next() : null;
		
		return current;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}