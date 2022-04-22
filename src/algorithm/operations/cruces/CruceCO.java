package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import algorithm.individuos.Individuo;

//Cruce con codificaci�n ordinal

public class CruceCO extends Cruce {
	
	public static final String name = "Cruce con codificacion ordinal";

	public CruceCO() {
		super.name = name; 
	}
	
	private void check(List<Integer> listaDinamica, List<Integer> ordinalPadre, Individuo ind) {
		for(int j = 0; j < ind.getSize(); j++) {
			int vuelo = ind.getGen(j);
			
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
	
	private void assign(List<Integer> listaDinamica, Individuo ind) {
		for(int j = 0; j < ind.getSize(); j++) {
			int vuelo = ind.getGen(j);
			
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
			ind.setGen(idx, ind.getGen(idx));
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
		List<Integer> listaDinamica = new LinkedList<>();
				
		inicializaLista(listaDinamica, individuo.getSize());
		
		List<Integer> ordinalPadre1 = new ArrayList<>();
		List<Integer> ordinalPadre2 = new ArrayList<>();
		
		//3� Se rellenan las listas ordinales de los progenitores
		check(listaDinamica, ordinalPadre1, individuoPrev);      //Saca la lista ordinal del padre1
		inicializaLista(listaDinamica, individuo.getSize());
		check(listaDinamica, ordinalPadre2, individuo);      //Saca la lista ordinal del padre2
		
		//4� Se crea el punto de corte para realizar un cruze monopunto
		int punto = random.nextInt(individuo.getSize());
		
		//Cruze monopunto --> Se intercambian los genes que est�n detr�s del punto de corte
		for(int j = punto; j < individuo.getSize(); j++) {
			int aux = ordinalPadre1.get(j);
			ordinalPadre1.set(j, ordinalPadre2.get(j));
			ordinalPadre2.set(j, aux);
		}
		inicializaLista(listaDinamica, individuo.getSize());
		
		//--------------------------------------------------
		
		assign(listaDinamica, individuoPrev);
		inicializaLista(listaDinamica, individuo.getSize());
		assign(listaDinamica, individuo);
		
	}
}
