package algorithm.individuos.tree;

public abstract class GenTree {
	
	//TreeGenerator treeGenerator;
	Node root;              //Raiz del arbol GenTree. Inicialmente a null
	
	public static int alturaTree = 0;
	public static final String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
	public static final String funciones[] = { "AND", "OR", "NOT", "IF" };
	
	public GenTree() {
		super();
		this.root = null;
		inicializacion(root, 0);      //Genera el �rbol
	}

	class Node {    
	    String value; 
	        Node left, right, center; 
	    	int num_nodos; // n�mero de nodos 
	    	int profundidad; // profundidad del �rbol
	          
	        Node(String value){ 
	            this.value = value; 
	            left = null; 
	            right = null; 
	            center = null;
	        }   
	     /*public void setValue(String value) {
	    	 this.value = value;
	     }
	     
	     public String getValue() {
	    	 return value;
	     }*/
	}
		
	public abstract Node inicializacion(Node root, int altura);

	public Boolean getAptitud(){    
		return getAptitud(root);
	}
	
	private Boolean getAptitud(Node root) {      //Devuelve la aptitud recorriendo el �rbol recursivamente
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

	/*public void generarArbol() {
		inicializacion(root, 0);
	}*/
	
	public static void setAlturaTree(int altura) {
		alturaTree = altura;
	}

}
