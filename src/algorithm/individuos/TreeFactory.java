package algorithm.individuos;

import algorithm.individuos.tree.GenTree;
import algorithm.individuos.tree.TreeCompleto;
import algorithm.individuos.tree.TreeCreciente;

public class TreeFactory {

	private static TreeFactory instance = null;
	
	public static TreeFactory getInstance() {
		if(instance == null) {
			instance = new TreeFactory();
		}
		
		return instance;
	}
	
	public GenTree treeGenerator(String type, int alturaTree) {
		if(type == "Completo")
			return new TreeCompleto(alturaTree);
		else
			return new TreeCreciente(alturaTree);
	}
}
