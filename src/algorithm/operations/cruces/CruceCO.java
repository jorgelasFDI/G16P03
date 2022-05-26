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
	
	private void check(List<Integer> listaDinamica, List<Integer> ordinalPadre, IndividuoVuelo ind) {
		for(int j = 0; j < ind.getSize(); j++) {
			int vuelo = ind.get(j);
			
			Iterator<Integer> it = listaDinamica.iterator();
			Boolean found = false;
			int idx = 0;
			while(it.hasNext() && !found) {
				int v = it.next();
				if(vuelo == v)
					found = true;
				else
					idx++;
			}
			ordinalPadre.add(idx + 1);
			listaDinamica.remove(idx);
		}
	}
	
	private void assign(List<Integer> listaDinamica, IndividuoVuelo ind) {
		for(int j = 0; j < ind.getSize(); j++) {
			int vuelo = ind.get(j);
			
			Iterator<Integer> it = listaDinamica.iterator();
			Boolean found = false;
			int idx = 0;
			while(it.hasNext() && !found) {
				int v = it.next();
				if(vuelo == v)
					found = true;
				else
					idx++;
			}
			ind.set(idx, ind.get(idx));
			listaDinamica.remove(idx);
		}
	}

	private void inicializaLista(List<Integer> listaDinamica, int size) {
		listaDinamica.clear();
		for(int j = 0; j < size; j++) {
			listaDinamica.add(j + 1);
		}
	}

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {
		IndividuoVuelo padre1 = (IndividuoVuelo) individuoPrev;
		IndividuoVuelo padre2 = (IndividuoVuelo) individuo;

		List<Integer> listaDinamica = new LinkedList<>();
				
		inicializaLista(listaDinamica, padre2.getSize());
		
		List<Integer> ordinalPadre1 = new ArrayList<>();
		List<Integer> ordinalPadre2 = new ArrayList<>();
		
		//3� Se rellenan las listas ordinales de los progenitores
		check(listaDinamica, ordinalPadre1, padre1);      //Saca la lista ordinal del padre1
		inicializaLista(listaDinamica, padre2.getSize());
		check(listaDinamica, ordinalPadre2, padre2);      //Saca la lista ordinal del padre2
		
		//4� Se crea el punto de corte para realizar un cruze monopunto
		int punto = random.nextInt(padre2.getSize());
		
		//Cruze monopunto --> Se intercambian los genes que est�n detr�s del punto de corte
		for(int j = punto; j < padre2.getSize(); j++) {
			int aux = ordinalPadre1.get(j);
			ordinalPadre1.set(j, ordinalPadre2.get(j));
			ordinalPadre2.set(j, aux);
		}
		inicializaLista(listaDinamica, padre2.getSize());
		
		//--------------------------------------------------
		
		assign(listaDinamica, padre1);
		inicializaLista(listaDinamica, padre2.getSize());
		assign(listaDinamica, padre2);
		
	}
}
