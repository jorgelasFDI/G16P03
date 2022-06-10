package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;

//Cruce con codificaci�n ordinal

public class CruceCO extends Cruce {
	
	public static final String name = "Cruce con codificacion ordinal";

	public CruceCO() {
		super.name = name; 
	}
	
	private List<Integer> transform(IndividuoVuelo ind) {
		List<Integer> ordinalPadre = new ArrayList<>();
		List<Integer> listaDinamica = inicializaLista(ind.getSize());
		for(int j = 0; j < ind.getSize(); j++) {
			int idx = 0; int vuelo = ind.get(j);
			for (Integer i: listaDinamica) {
				if (vuelo == i) break;
				idx++;
			} ordinalPadre.add(idx);
			listaDinamica.remove(idx);
		} return ordinalPadre;
	}
	
	private void assign(List<Integer> ordinalPadre, IndividuoVuelo ind) {
		List<Integer> listaDinamica = inicializaLista(ind.getSize());
		for(int j = 0; j < ind.getSize(); j++) {
			int vuelo = ordinalPadre.get(j);
			ind.set(j, listaDinamica.get(vuelo));
			listaDinamica.remove(vuelo);
		}
	}

	private List<Integer> inicializaLista(int size) {
		List<Integer> listaDinamica = new LinkedList<>();
		for(int j = 0; j < size; j++) {
			listaDinamica.add(j + 1);
		} return listaDinamica;
	}

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {
		IndividuoVuelo padre1 = (IndividuoVuelo) individuoPrev;
		IndividuoVuelo padre2 = (IndividuoVuelo) individuo;
		
		//3� Se rellenan las listas ordinales de los progenitores
		List<Integer> ordinalPadre1 = transform(padre1);      //Saca la lista ordinal del padre1
		List<Integer> ordinalPadre2 = transform(padre2);      //Saca la lista ordinal del padre2
		
		//4� Se crea el punto de corte para realizar un cruze monopunto
		int indice = random.nextInt(padre2.getSize() - 1);
		for (int j = 0; j <= indice; j++) {
			int aux = ordinalPadre1.get(j);
			ordinalPadre1.set(j, ordinalPadre2.get(j));
			ordinalPadre2.set(j, aux);
        }

		assign(ordinalPadre1, padre1);
		assign(ordinalPadre2, padre2);
		
	}
}
