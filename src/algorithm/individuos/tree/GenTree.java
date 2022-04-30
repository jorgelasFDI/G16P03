package algorithm.individuos.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;

import auxiliar.Binary;
import auxiliar.MyRandom;

public abstract class GenTree {
	
	//TreeGenerator treeGenerator;
	Node root;              //Raiz del arbol GenTree. Inicialmente a null

	private interface TernaryOperator<T> {
		public T ternary(T x, T y, T z);
	}
	
	public int alturaTree;
	public static final Map<String, Integer> terminales = new HashMap<>(){{
		put("A0", 0);
		put("A1", 1);
		put("D0", 2);
		put("D1", 3);
		put("D2", 4);
		put("D3", 5);
	}};
	
	public static final Map<String, TernaryOperator<Boolean>> funciones = new HashMap<>(){{
		put("AND", (a, b, c) -> (a && b));
		put("OR", (a, b, c) -> (a || b));
		put("NOT", (a, b, c) -> (!a));
		put("IF", (a, b, c) -> (a ? b : c));
	}};
		
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
			
		Node(String value) { 
			this.value = value; 
			left = null; 
			right = null; 
			center = null;
			num_nodos = 1;
			profundidad = 1;
		}   
		
		Node(Node node) { 
			this.value = node.value; 
			this.left = node.left; 
			this.right = node.right; 
			this.center = node.center;
			this.num_nodos = node.num_nodos;
			this.profundidad = node.profundidad;
		}
	}
		
	public abstract Node inicializacion(Node root, int altura);

	public boolean execFunction(ArrayList<Integer> combinacion){    
		return execFunction(root, combinacion);
	}

	private boolean execFunction(Node root, ArrayList<Integer> combinacion) {      //Devuelve la aptitud recorriendo el árbol recursivamente
		if (terminales.containsKey(root.value)) 
			return Binary.toBool(combinacion.get(terminales.get(root.value)));
		
         //Si el nodo es una funcion se mira en sus sub-arboles recursivamente
		boolean x = execFunction(root.left, combinacion);
		boolean y = execFunction(root.center, combinacion);
		boolean z = execFunction(root.right, combinacion);

		return funciones.get(root.value).ternary(x, y, z);
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
		String value = MyRandom.getRandomValueInSet(funciones.keySet());
		raiz = new Node(value);    //Con sus ramas inicialmente vacias
		int prof = 0;
		
		if(value.equals("NOT")) {
			raiz.left = inicializacion(raiz.left, altura + 1);
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
		String value = MyRandom.getRandomValueInSet(terminales.keySet());
		return new Node(value);
	}

}
