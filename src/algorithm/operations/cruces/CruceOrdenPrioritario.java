package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;
import auxiliar.MyRandom;

public class CruceOrdenPrioritario extends Cruce {

	public static final String name = "Cruce Orden Prioritario";

	public CruceOrdenPrioritario() {
		super.name = name; 
	}

	int numSelected = 4;

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {
		IndividuoVuelo padre1 = (IndividuoVuelo) individuoPrev;
		IndividuoVuelo padre2 = (IndividuoVuelo) individuo;
		List<Integer> selected = new ArrayList<>(numSelected);
		MyRandom.getRandomNoRepeat(selected, numSelected, 0, padre2.getSize() - 1);

		List<Integer> newlist = new ArrayList<>(numSelected);
		List<Integer> newlistPrev = new ArrayList<>(numSelected);
		for (int j = 0; j < selected.size(); j++) {
			newlist.add(padre2.get(selected.get(j)));
			newlistPrev.add(padre1.get(selected.get(j)));
		}

		int indice = 0;
		int indicePrev = 0;
		for (int j = 0; j < padre2.getSize(); j++) {

			if (newlistPrev.contains(padre2.get(j))) {
				padre2.set(j, newlistPrev.get(indice));
				indice++;
			}

			if (newlist.contains(padre1.get(j))) {
				padre1.set(j, newlist.get(indicePrev));
				indicePrev++;
			}
		}
	}

}
