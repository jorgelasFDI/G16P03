package gui;

import algorithm.individuos.Individuo;
import algorithm.population.Generaciones;

public interface Observers {
	public void actualizaVista(Generaciones generaciones, Individuo mejorIndividuo);
}
