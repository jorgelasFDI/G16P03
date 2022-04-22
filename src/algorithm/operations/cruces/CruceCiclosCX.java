package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.List;

import algorithm.individuos.Individuo;

public class CruceCiclosCX extends Cruce {
	
	public static final String name = "Cruce por ciclos";

	public CruceCiclosCX() {
		super.name = name; 
	}

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {
		List<Boolean> inCycle = new ArrayList<>(individuoPrev.getSize());      //Tienen el mismo tama�o
		//List<Boolean> padre2 = new ArrayList<>(antiguoPrev.getSize());
		
		//3� Vamos asignando los genes del ciclo(de momento solo en la lista de booleans) para los nuevos individuos descendientes
		//Se itera hasta que acaban los ciclos
		// Boolean continua = true;
		for(int j = 0; j < individuoPrev.getSize(); j++) {
			inCycle.add(false);
		}
		int idx1 = 0;
		while(true) {
			inCycle.set(idx1, true);           
			Boolean found = false;
			int pos = individuo.getGen(idx1);
			for(int j = 0; j < individuoPrev.getSize() && !found; j++) 
				if(individuoPrev.getGen(j) == pos) {
					idx1 = j;     found = true;
				}
			if(inCycle.get(idx1)) break;		

		}

		//4� Una vez seleccionados los vuelos del ciclo, cruzamos los vuelos no colocados en los 2 descendientes
		for(int j = 0; j < individuoPrev.getSize(); j++) {
			if(!inCycle.get(j))
			individuoPrev.swapBit(j, individuo);
		}
		
	}

}
