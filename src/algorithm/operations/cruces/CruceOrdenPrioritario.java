package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.List;

import algorithm.individuos.Individuo;
import auxiliar.MyRandom;

public class CruceOrdenPrioritario extends Cruce {

	public static final String name = "Cruce Orden Prioritario";

	public CruceOrdenPrioritario() {
		super.name = name; 
	}

	int numSelected = 4;

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {
		List<Integer> selected = new ArrayList<>(numSelected);
		MyRandom.getRandomNoRepeat(selected, numSelected, 0, individuo.getSize() - 1);

		List<Integer> newlist = new ArrayList<>(numSelected);
		List<Integer> newlistPrev = new ArrayList<>(numSelected);
		for (int j = 0; j < selected.size(); j++) {
			newlist.add(individuo.getGen(selected.get(j)));
			newlistPrev.add(individuoPrev.getGen(selected.get(j)));
		}

		int indice = 0;
		int indicePrev = 0;
		for (int j = 0; j < individuo.getSize(); j++) {

			if (newlistPrev.contains(individuo.getGen(j))) {
				individuo.setGen(j, newlistPrev.get(indice));
				indice++;
			}

			if (newlist.contains(individuoPrev.getGen(j))) {
				individuoPrev.setGen(j, newlist.get(indicePrev));
				indicePrev++;
			}
		}
	}

}
