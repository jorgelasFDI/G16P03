package algorithm.individuos.tree;

import auxiliar.MyRandom;

public class TreeCreciente extends GenTree {

	public TreeCreciente(int alturaTree) {
		super(alturaTree);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node inicializacion(Node raiz, int altura) {    
		// TODO Auto-generated method stub
		if(altura < alturaTree) {          //Se puede añadir o funcion o terminal
			if(MyRandom.getInstance().nextBoolean()) {         //Si esta a true se añade una funcion, si no se añade un terminal
				generaArbolFuncion(altura, raiz);
			}
			else {
				generaTerminal(raiz);
			}
		} 
		else {          //Se añade terminal
			generaTerminal(raiz);
		}
		
		return new Node(root);
	}

}
