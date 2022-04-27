package algorithm.individuos.tree;

public class TreeCompleto extends GenTree {

	public TreeCompleto(int alturaTree) {
		super(alturaTree);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Node inicializacion(Node raiz, int altura) {
		// TODO Auto-generated method stub
		if(altura < alturaTree) {          //Se siguen creando sub-arboles o funciones
			raiz = generaArbolFuncion(altura, raiz);
		}
		else {             //Se crean las hojas o terminales
			raiz = generaTerminal(raiz);
		}
		
		return new Node(raiz);
	}

}
