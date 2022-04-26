package algorithm.individuos;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import algorithm.functiones.TreeGenerator;
import algorithm.individuos.tree.GenTree;
import auxiliar.MyRandom;

public class Individuo implements Comparable<Individuo> {

	//public static String terminales[];

	private GenTree cromosoma;
	private int hMaxima;
	//private Map<Integer, Vuelo> genesToObjects = new HashMap<>();

	private TreeGenerator function;
	private double aptitud;
    private double puntuacion; 
    private double puntuacionAcumulada;
	private double aptitudRevisada;

	public Individuo(Individuo individuo) {
		function = individuo.getFunction();
		//size = individuo.getSize();
		aptitud = individuo.getAptitud();
		aptitudRevisada = individuo.getAptitudRevisada();
        puntuacion = individuo.getPuntuacion();
        puntuacionAcumulada = individuo.getPuntuacionAcumulada();
		
		//for (int i = 0; i < size; i++) genesToObjects.put(i + 1, new Vuelo(individuo.getVuelo(i + 1)));
		//for (int i = 0; i < size; i++) genes.add(individuo.getGen(i));
	}

	public Individuo(TreeGenerator function) {     //params guarda la tolerancia[0] y el n� de vuelos[1]
		//this.size = objects.size();
		//this.hMaxima = hMaxima;
		this.function = function;
		
		cromosoma = new GenTree();
		cromosoma.generate(function);
		//for(int i = 0; i < size; i++) genesToObjects.put(i + 1, new Vuelo(objects.get(i)));
		//MyRandom.getRandomNoRepeat(genes, size, 1, size); // genes vacio -> 0 - 11
	}

	/*public Map<Integer, Vuelo> getGenesToObjects() {
		return genesToObjects;
	}*/

	public GenTree getCromosoma() {
		return cromosoma;
	}

	public void setCromosoma(GenTree cromosoma) {
		this.cromosoma = cromosoma;
	}

	public Boolean fitness() {
		return cromosoma.getAptitud();
	}

	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}

	public double getAptitud() {
		return aptitud;
	}

	public TreeGenerator getFunction() {
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
