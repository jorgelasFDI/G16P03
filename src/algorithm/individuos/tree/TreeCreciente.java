package algorithm.individuos.tree;

import auxiliar.MyRandom;

public class TreeCreciente extends GenTree {

	@Override
	public Node inicializacion(Node root, int altura) {    
		// TODO Auto-generated method stub
		if(altura < alturaTree) {          //Se puede añadir o funcion o terminal
			if(MyRandom.getInstance().nextBoolean()) {         //Si esta a true se añade una funcion, si no se añade un terminal
				String value = funciones[MyRandom.getInstance().nextInt(funciones.length)];
				root = new Node(value);    //Con sus ramas inicialmente vacias
				if(value.equals("NOT")) {
					Node ctr = inicializacion(root.center, altura + 1);
					root.center = ctr;
				}
				else {	
					//Genera arbol izquierdo
					root.left = inicializacion(root.left, altura + 1);
					//Genera arbol derecho
					root.right = inicializacion(root.right, altura + 1);
					
					if(value.equals("IF")) {
						Node ctr = inicializacion(root.center, altura + 1);
						root.center = ctr;
					}
				}
			}
			else {
				String value = terminales[MyRandom.getInstance().nextInt(terminales.length)];
				root = new Node(value);
			}
		} 
		else {          //Se añade terminal
			String value = terminales[MyRandom.getInstance().nextInt(terminales.length)];
			root = new Node(value);
		}
		
		return root;
	}

}
