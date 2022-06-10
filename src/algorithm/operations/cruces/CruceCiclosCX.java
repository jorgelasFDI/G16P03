package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;

public class CruceCiclosCX extends Cruce {
	
	public static final String name = "Cruce por ciclos";

	public CruceCiclosCX() {
		super.name = name; 
	}

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {
		IndividuoVuelo padre1 = (IndividuoVuelo) individuoPrev;
		IndividuoVuelo padre2 = (IndividuoVuelo) individuo;
		Boolean[] inCycle = new Boolean[individuoPrev.getSize()];      //Tienen el mismo tama�o
		Arrays.fill(inCycle, false);

		int idx = 0;
		while (true) {
			inCycle[idx] = true;
			int toFind = padre2.get(idx); idx = 0;
			for (Integer i: padre1) {
				if (toFind == i) break;
				idx++;
			} if (inCycle[idx]) break;
		}

		for(int j = 0; j < padre1.getSize(); j++) {
			if(!inCycle[j]) padre1.swapGen(j, padre2);
		}
		
	}

}
