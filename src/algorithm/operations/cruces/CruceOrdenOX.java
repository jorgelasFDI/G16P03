package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import algorithm.individuos.IndividuoVuelo;
import auxiliar.MyRandom;

public class CruceOrdenOX extends Cruce {

    public static final String name = "Cruce por orden OX";

	public CruceOrdenOX() {
		super.name = name; 
	}

    private void mutateInd(IndividuoVuelo individuo, Map<Integer, Integer> map, int last) {
        IndividuoVuelo newIndividuo = (IndividuoVuelo) individuo.copy();
        
        for (int j = 0, k = 0; k < individuo.getSize(); ) {
            int idxJ = (j + last + 1)%individuo.getSize();
            int idxK = (k + last + 1)%individuo.getSize();
            int gen = individuo.get(idxJ);
            if (map.containsKey(idxK)) {
                newIndividuo.set(idxK, map.get(idxK));
                k++;
            } else if (!map.containsValue(gen)) {
                newIndividuo.set(idxK, gen);
                k++; j++;
            } else j++;
        }

        for (int j = 0; j < individuo.getSize(); j++)  individuo.set(j, newIndividuo.get(j));
    }

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {

		IndividuoVuelo padre1 = (IndividuoVuelo) individuoPrev;
		IndividuoVuelo padre2 = (IndividuoVuelo) individuo;
		
		List<Integer> selected = new ArrayList<>(2);
		Pair<Integer, Integer> range = MyRandom.getRandomNoRepeat(selected, 2, 0, individuo.getSize() - 1);
		int first = range.getValue0();
		int last = range.getValue1();

		Map<Integer, Integer> map = new HashMap<>(padre2.getSize());
		Map<Integer, Integer> mapPrev = new HashMap<>(padre1.getSize());
		for (int j = first; j <= last; j++) {
			map.put(j, padre1.get(j));
			mapPrev.put(j, padre2.get(j));
		}

		mutateInd(padre2, map, last);
		mutateInd(padre1, mapPrev, last);
	}

    
}
