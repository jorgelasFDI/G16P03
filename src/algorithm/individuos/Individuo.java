package algorithm.individuos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import algorithm.functiones.Fitness;
import auxiliar.MyRandom;

public class Individuo implements Comparable<Individuo>, Iterable<Integer> {

	private int size = 0;

	private List<Integer> genes = new ArrayList<>();
	private Map<Integer, Vuelo> genesToObjects = new HashMap<>();

	private Fitness function;
	private double aptitud;
    private double puntuacion; 
    private double puntuacionAcumulada;
	private double aptitudRevisada;

	public Individuo(Individuo individuo) {
		function = individuo.getFunction();
		size = individuo.getSize();
		aptitud = individuo.getAptitud();
		aptitudRevisada = individuo.getAptitudRevisada();
        puntuacion = individuo.getPuntuacion();
        puntuacionAcumulada = individuo.getPuntuacionAcumulada();
		
		for (int i = 0; i < size; i++) genesToObjects.put(i + 1, new Vuelo(individuo.getVuelo(i + 1)));
		for (int i = 0; i < size; i++) genes.add(individuo.getGen(i));
	}

	public Individuo(Fitness function, List<Vuelo> objects) {     //params guarda la tolerancia[0] y el n� de vuelos[1]
		this.size = objects.size();
		this.function = function;
		
		for(int i = 0; i < size; i++) genesToObjects.put(i + 1, new Vuelo(objects.get(i)));
		MyRandom.getRandomNoRepeat(genes, size, 1, size); // genes vacio -> 0 - 11

	}

	public Map<Integer, Vuelo> getGenesToObjects() {
		return genesToObjects;
	}

	public double fitness() {
		return function.fitnessInstance(this);
	}

	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}

	public double getAptitud() {
		return aptitud;
	}

	public Fitness getFunction() {
		return function;
	}
	
	public double getFenotipo(int idx) {
		return genes.get(idx);
	}

	public Vuelo getVuelo(int gen) {
		return genesToObjects.get(gen);
	}

	public void setGen(int idx, int value) {
		genes.set(idx, value);
	}

	public int getGen(int idx) {
		return genes.get(idx);
	}
	
	public void swapBit(int idx, Individuo other) {
		swapBit(idx, other, idx);
	}

	public void swapBit(int idx, int j) {
		swapBit(idx, this, j);
	}

	private void swapBit(int idx, Individuo other, int j) {
		int aux = other.getGen(j);
		other.setGen(j, genes.get(idx));
		setGen(idx, aux);
	}
	
	public int getSize(){
		return genes.size();
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

	public List<Integer> getGenes() {
		return genes;
	}

	@Override
	public Iterator<Integer> iterator() {
		return genes.iterator();
	}

	
	
}
