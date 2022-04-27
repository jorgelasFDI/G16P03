package algorithm.individuos;

import algorithm.individuos.tree.GenTree;

public class Individuo implements Comparable<Individuo> {

	//public static String terminales[];

	private GenTree cromosoma = null;
	private int aptitud;
    private double puntuacion; 
    private double puntuacionAcumulada;
	private double aptitudRevisada;

	public Individuo(Individuo individuo) {
		//function = individuo.getFunction();
		//size = individuo.getSize();
		aptitud = individuo.getAptitud();
		aptitudRevisada = individuo.getAptitudRevisada();
        puntuacion = individuo.getPuntuacion();
        puntuacionAcumulada = individuo.getPuntuacionAcumulada();
        cromosoma = individuo.getCromosoma();
	}

	public Individuo(String type, int alturaArbol) {     //le pasamos el tipo de arbol que tendrá, que podrá ser completo, creciente o Ramped&Half
		GenTree.setAlturaTree(alturaArbol);
		cromosoma = TreeFactory.getInstance().treeGenerator(type);     //Genera el árbol

	}

	public GenTree getCromosoma() {
		return cromosoma;
	}

	public void setCromosoma(GenTree cromosoma) {
		this.cromosoma = cromosoma;
	}

	public int fitness() {
		return 0;
	}

	public void setAptitud(int aptitud) {
		this.aptitud = aptitud;
	}

	public int getAptitud() {
		return aptitud;
	}

	/*public TreeGenerator getFunction() {
		return function;
	}
	
	/*public double getFenotipo(int idx) {
		return genes.get(idx);
	}

	public Vuelo getVuelo(int gen) {
		return genesToObjects.get(gen);
	}*/

	/*public void setGen(int idx, int value) {
		genes.set(idx, value);
	}

	public int getGen(int idx) {
		return genes.get(idx);
	}*/
	
	public void swapTree(int idx, Individuo other) {
		swapTree(idx, other, idx);
	}

	private void swapTree(int idx, Individuo other, int j) {
		/*
		int aux = other.getGen(j);
		other.setGen(j, genes.get(idx));
		setGen(idx, aux);
		*/
	}
	
	public int getAltura(){
		return cromosoma.getAltura();
	}
	
    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public double getPuntuacionAcumulada() {
        return puntuacionAcumulada;
    }

    public void setPuntuacionAcumulada(double puntuacionAcumulada) {
        this.puntuacionAcumulada = puntuacionAcumulada;
    }

	public double getAptitudRevisada() {
		return aptitudRevisada;
	}

	public void setAptitudRevisada(double aptitudRevisada) {
		this.aptitudRevisada = aptitudRevisada;
	}

	@Override
	public int compareTo(Individuo individuo) {
		// TODO Auto-generated method stub
		if (getAptitudRevisada() > individuo.getAptitudRevisada()) return 1;
        if (getAptitudRevisada() == individuo.getAptitudRevisada()) return 0;
        return -1;
	}

	public void print() {
		// TODO Auto-generated method stub
		/*
		for(int i = 0; i < genes.size(); i++) {
			System.out.print(genes.get(i) + " ");
		}
		System.out.println(); 
		for(int i = 0; i < genes.size(); i++) {
			System.out.print(genesToObjects.get(genes.get(i)).getTLA() + " ");
		}
		System.out.println(); 		
		for(int i = 0; i < genes.size(); i++) {
			System.out.print(genesToObjects.get(genes.get(i)).getPista() + " ");
		}
		System.out.println(); 
		
		System.out.println("APTITUD INDIVIDUO --> " + this.getAptitud());
		//System.out.println();
		//System.out.println();*/
	}

	/*public List<Integer> getGenes() {
		return genes;
	}

	@Override
	public Iterator<Integer> iterator() {
		return genes.iterator();
	}*/

}
