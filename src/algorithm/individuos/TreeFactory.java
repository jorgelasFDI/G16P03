package algorithm.individuos;

import algorithm.individuos.tree.GenTree;
import algorithm.individuos.tree.TreeCompleto;
import algorithm.individuos.tree.TreeCreciente;
import algorithm.individuos.tree.TreeRampedAndHalf;

public class TreeFactory {

	private static TreeFactory instance = null;
	
	public static TreeFactory getInstance() {
		if(instance == null) {
			instance = new TreeFactory();
		}
		
		return instance;
	}
	
	public GenTree treeGenerator(String type) {
		if(type == "Completo")
			return new TreeCompleto();
		else if(type == "Creciente")
			return new TreeCreciente();
		else
			return new TreeRampedAndHalf();
	}
}
