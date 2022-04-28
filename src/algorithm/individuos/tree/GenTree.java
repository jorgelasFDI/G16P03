package algorithm.individuos.tree;

import auxiliar.MyRandom;

public abstract class GenTree {
	
	//TreeGenerator treeGenerator;
	Node root;              //Raiz del arbol GenTree. Inicialmente a null
	
	public int alturaTree = 0;
	public static final String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
	public static final String funciones[] = { "AND", "OR", "NOT", "IF" };
	
	public GenTree(int alturaTree) {
		super();
		this.root = null;
		this.alturaTree = alturaTree;
		root = inicializacion(root, 1);      //Genera el árbol
	}

	class Node {    
	    	String value; 
	        Node left, right, center; 
	    	int num_nodos; // número de nodos 
	    	int profundidad; // profundidad del árbol
	          
	        Node(String value){ 
	            this.value = value; 
	            left = null; 
	            right = null; 
	            center = null;
	            num_nodos = 1;
	            profundidad = 1;
	        }   
	        
	        Node(Node node){ 
	            this.value = node.value; 
	            this.left = node.left; 
	            this.right = node.right; 
	            this.center = node.center;
	            this.num_nodos = node.num_nodos;
	            this.profundidad = node.profundidad;
	        } 
	}
		
	public abstract Node inicializacion(Node root, int altura);

	public Boolean getAptitud(){    
		return getAptitud(root);
	}
	
	private Boolean getAptitud(Node root) {      //Devuelve la aptitud recorriendo el árbol recursivamente
		return true;
	}
	
	public int getAltura() {
		return root.profundidad;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	public Node generaArbolFuncion(int altura, Node raiz) {
		String value = funciones[MyRandom.getInstance().nextInt(funciones.length)];
		raiz = new Node(value);    //Con sus ramas inicialmente vacias
		int prof = 0;
		
		if(value.equals("NOT")) {
			raiz.center = inicializacion(raiz.center, altura + 1);
		}
		else {	
			//Genera arbol izquierdo
			Node izq = inicializacion(raiz.left, altura + 1);
			raiz.left = izq;    raiz.num_nodos += izq.num_nodos;
			prof = izq.profundidad;

			//Genera arbol derecho
			Node dcho = inicializacion(raiz.right, altura + 1);
			raiz.right = dcho;     raiz.num_nodos += dcho.num_nodos;
			
			if(prof < dcho.profundidad) {
				prof = dcho.profundidad;
			}
			
			if(value.equals("IF")) {
				raiz.center = inicializacion(raiz.center, altura + 1);
				
				if(prof < raiz.center.profundidad) {
					prof = raiz.center.profundidad;
				}
			}
		}
		raiz.profundidad = prof + 1;
		
		return raiz;
	}
	
	public Node generaTerminal(Node raiz) {
		String value = terminales[MyRandom.getInstance().nextInt(terminales.length)];
		return new Node(value);
	}

}
