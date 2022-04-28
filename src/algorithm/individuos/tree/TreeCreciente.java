package algorithm.individuos.tree;

import auxiliar.MyRandom;

public class TreeCreciente extends GenTree {

	private static double prob = 0.75;
	
	public TreeCreciente(int alturaTree) {
		super(alturaTree);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node inicializacion(Node raiz, int altura) {    
		// TODO Auto-generated method stub
		if(altura < alturaTree) {          //Se puede añadir o funcion o terminal
			if(MyRandom.getInstance().nextDouble() < prob) {         //Si esta a true se añade una funcion, si no se añade un terminal
				raiz = generaArbolFuncion(altura, raiz);
			}
			else {
				raiz = generaTerminal(raiz);
			}
		} 
		else {          //Se añade terminal
			raiz = generaTerminal(raiz);
		}
		
		return new Node(raiz);
	}

}
