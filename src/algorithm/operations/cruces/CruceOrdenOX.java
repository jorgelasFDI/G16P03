package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import auxiliar.MyRandom;

public class CruceOrdenOX extends Cruce {

    public static final String name = "Cruce por orden OX";

	public CruceOrdenOX() {
		super.name = name; 
	}

    private void mutateInd(Individuo individuo, Map<Integer, Integer> map, int last) {
        Individuo newIndividuo = new Individuo(individuo);
        
        for (int j = 0, k = 0; k < individuo.getSize(); ) {
            int idxJ = (j + last + 1)%individuo.getSize();
            int idxK = (k + last + 1)%individuo.getSize();
            int gen = individuo.getGen(idxJ);
            if (map.containsKey(idxK)) {
                newIndividuo.setGen(idxK, map.get(idxK));
                k++;
            } else if (!map.containsValue(gen)) {
                newIndividuo.setGen(idxK, gen);
                k++; j++;
            } else j++;
        }

        for (int j = 0; j < individuo.getSize(); j++)  individuo.setGen(j, newIndividuo.getGen(j));
    }

	@Override
	public void cruzar(Individuo individuoPrev, Individuo individuo) {
		List<Integer> selected = new ArrayList<>(2);
		Pair<Integer, Integer> range = MyRandom.getRandomNoRepeat(selected, 2, 0, individuo.getSize() - 1);
		int first = range.getValue0();
		int last = range.getValue1();

		Map<Integer, Integer> map = new HashMap<>(individuo.getSize());
		Map<Integer, Integer> mapPrev = new HashMap<>(individuoPrev.getSize());
		for (int j = first; j <= last; j++) {
			map.put(j, individuoPrev.getGen(j));
			mapPrev.put(j, individuo.getGen(j));
		}

		mutateInd(individuo, map, last);
		mutateInd(individuoPrev, mapPrev, last);
	}

    
}
