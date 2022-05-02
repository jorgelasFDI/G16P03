package algorithm.individuos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import algorithm.functions.Function;

public abstract class Individuo<TYPE, OBJ, ITER> implements Comparable<Individuo<TYPE, OBJ, ITER>>, Iterable<ITER> {

	protected Iterable<ITER> iterable = null;
	protected Map<TYPE, OBJ> genesToObjects = new HashMap<>();

	protected double aptitud;
    protected double puntuacion; 
    protected double puntuacionAcumulada;
	protected double aptitudRevisada;

	protected int size = 0;
	protected Function function;

	public Individuo(Function function) {
		this.function = function;
	}

	public Individuo(Individuo<TYPE, OBJ, ITER> individuo) {
		function = individuo.getFunction();
		aptitud = individuo.getAptitud();
		aptitudRevisada = individuo.getAptitudRevisada();
        puntuacion = individuo.getPuntuacion();
        puntuacionAcumulada = individuo.getPuntuacionAcumulada();
		size = individuo.getSize();
		iterable = individuo.copyGenes();
	}

	public abstract Iterable<ITER> copyGenes();
	public abstract Individuo<TYPE, OBJ, ITER> copy();
	public abstract int getSize();
	public abstract void swapGen(int idx, int j, Individuo<TYPE, OBJ, ITER> other);
	public abstract ITER get(int i);
	public abstract void set(int i, ITER other);

	public void swapGen(int idx, Individuo<TYPE, OBJ, ITER> other) {
		swapGen(idx, idx, other);
	}

	public void swapGen(int idx, int j) {
		swapGen(idx, j, this);
	}

	public double fitness() {
		return function.fitnessInstance(this);
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}

	public double getAptitud() {
		return aptitud;
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

	public void setObject(TYPE key, OBJ value) {
		genesToObjects.put(key, value);
	}

	public OBJ getObject(TYPE key) {
		return genesToObjects.get(key);
	}

	public void setGenesToObjects(Map<TYPE, OBJ> genesToObjects) {
		this.genesToObjects = genesToObjects;
	}

	public Map<TYPE, OBJ> getGenesToObjects() {
		return genesToObjects;
	}

	@Override
	public int compareTo(Individuo<TYPE, OBJ, ITER> individuo) {
		if (getAptitudRevisada() > individuo.getAptitudRevisada()) return 1;
        if (getAptitudRevisada() == individuo.getAptitudRevisada()) return 0;
        return -1;
	}

	@Override
	public Iterator<ITER> iterator() {
		return iterable.iterator();
	}

}
