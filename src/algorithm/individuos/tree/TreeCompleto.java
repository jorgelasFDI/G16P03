package algorithm.individuos.tree;

import auxiliar.MyRandom;

public class TreeCompleto extends GenTree {

	@Override
	public Node inicializacion(Node root, int altura) {
		// TODO Auto-generated method stub
				if(altura < alturaTree) {          //Se siguen creando sub-arboles o funciones
					String value = funciones[MyRandom.getInstance().nextInt(funciones.length)];
					//arbol.setNewBranch(root, value);           //Crea la nueva rama asignando valor a su raíz	
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
				else {             //Se crean las hojas o terminales
					String value = terminales[MyRandom.getInstance().nextInt(terminales.length)];
					root = new Node(value);
				}
				
				return root;
	}

}
