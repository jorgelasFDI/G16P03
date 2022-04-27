package algorithm.population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class Poblacion implements Iterable<Individuo> {

	private Operation cruce;
	private Operation mutacion;
	private Operation seleccion;

	private int size;
	private int eliteSize;
	private double presion;
	private List<Individuo> poblacion;
	private List<Individuo> elite;

	private double mediaAptitud;
	private Individuo mejorIndividuo;
	private boolean minimizar = true;
	

	public void cruzar() {
		cruce.operationInstance(poblacion);
	}

	public void seleccionar() {
		seleccion.operationInstance(poblacion);
	}

	public void mutar() {
		mutacion.operationInstance(poblacion);
	}

	public Poblacion(int size, double eliteSize, Operation cruce, Operation mutacion, Operation seleccion, double presion, String type) {
		this.cruce = cruce;
		this.mutacion = mutacion;
		this.seleccion = seleccion;
		this.size = size;
		this.presion = presion;
		poblacion = new ArrayList<>(size);
		this.eliteSize = (int) Math.floor(eliteSize * size);
		mejorIndividuo = null;
		mediaAptitud = 0.0;

		for (int i = 0; i < size; i++) {
            poblacion.add(new Individuo(mejorIndividuo));
        } 
		
		evalua();
	}

	public void evalua() {
		Pair<Double, Individuo> pareja = evalua(poblacion, minimizar, mejorIndividuo, presion);
		mediaAptitud = pareja.getValue0();
		mejorIndividuo = pareja.getValue1();
	}
	
	public static Pair<Double, Individuo> evalua(List<Individuo> poblacion, boolean minimizar, Individuo mejorIndividuo, Double presion) {

		// REVISION
		double fmax = Double.NEGATIVE_INFINITY;
		double fmin = Double.POSITIVE_INFINITY;
		int sumAptitud = 0;
		for (Individuo i: poblacion) {
			int rawAptitud = i.fitness();
			/*sumAptitud += rawAptitud;
			i.setAptitud(rawAptitud);
			if (rawAptitud > fmax) fmax = rawAptitud;
			if (rawAptitud < fmin) fmin = rawAptitud;*/
		}  fmax *= 1.05;
		double mediaAptitud = sumAptitud / poblacion.size(); 

		int sumAptitudRevisada = 0;
		for (Individuo i: poblacion) {
			if (minimizar) i.setAptitudRevisada(fmax - i.getAptitud());
			else i.setAptitudRevisada(Math.abs(fmin) + i.getAptitud());
			sumAptitudRevisada += i.getAptitudRevisada();
		} double mediaAptitudRevisada = sumAptitudRevisada / poblacion.size();

		// ORDENAR
		Poblacion.sort(poblacion);
		Individuo mejorGeneracion = poblacion.get(0);       //El individuo con mejor aptitud SIEMPRE se guarda en la 1a posicion
		Individuo peorGeneracion = poblacion.get(poblacion.size() - 1);
		if(mejorIndividuo == null)
			mejorIndividuo = new Individuo(mejorGeneracion);
		else if(minimizar && mejorGeneracion.getAptitud() < mejorIndividuo.getAptitud())
			mejorIndividuo = new Individuo(mejorGeneracion);
		else if(!minimizar && mejorGeneracion.getAptitud() > mejorIndividuo.getAptitud())
			mejorIndividuo = new Individuo(mejorGeneracion);

		// ESCALADO	
		if (presion != null) {
			double aMax = ((presion - 1)*mediaAptitudRevisada)/(mejorGeneracion.getAptitudRevisada() - mediaAptitudRevisada);
			double bMax = (1 - aMax)*mediaAptitudRevisada;

			double aMin = mediaAptitudRevisada/(mediaAptitudRevisada - peorGeneracion.getAptitudRevisada());
			double bMin = (1 - aMin)*mediaAptitudRevisada;

			sumAptitudRevisada = 0;
			for (Individuo i: poblacion) {
				double value = aMax*i.getAptitudRevisada() + bMax;
				if (value < 0) value = aMin*i.getAptitudRevisada() + bMin;
				if (value < 0) value = 0;
				i.setAptitudRevisada(value);
				sumAptitudRevisada += i.getAptitudRevisada();
			}
		}

		// NORMAL
		double puntAcum = 0.0;
		for(Individuo i: poblacion) {
			i.setPuntuacion(i.getAptitudRevisada() / sumAptitudRevisada);
			puntAcum += i.getPuntuacion();
			i.setPuntuacionAcumulada(puntAcum);
		}

		return new Pair<>(mediaAptitud, mejorIndividuo);
	}

	public void generarElite() {
		Poblacion.sort(poblacion);
		elite = new ArrayList<>(eliteSize);
		for (int i = 0; i < eliteSize; i++) {
			elite.add(new Individuo(poblacion.get(i)));
		}
	}

	public void introducirElite() {
		Poblacion.sort(poblacion);
		for (int i = 1; i <= elite.size(); i++) {
			poblacion.set(poblacion.size() - i, new Individuo(elite.get(i - 1)));
		}
	}

	public Individuo getMejorIndividuo() {
		return new Individuo(mejorIndividuo);
	}

	public Individuo get(int index) {
		return new Individuo(poblacion.get(index));
	}

	public void set(int index, Individuo element) {
		poblacion.set(index, element);
	}

	public int size() {
		return size;
	}
	
	public double getMediaAptitud() {
		return mediaAptitud;
	}
	
	public double getMejorAbsoluto() {
		return mejorIndividuo.getAptitud();
	}

	public static void sort(List<Individuo> poblacion) {
		Collections.sort(poblacion, Collections.reverseOrder());
	}

	public void print() {
		System.out.println("PRINTING");
		for (Individuo ind: poblacion) {
			ind.print();
		}
	}

	@Override
	public Iterator<Individuo> iterator() {
		return poblacion.iterator();
	}
}
