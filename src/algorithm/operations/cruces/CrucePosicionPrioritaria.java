package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import auxiliar.MyRandom;

public class CrucePosicionPrioritaria extends Cruce {

	public static final String name = "Cruce Posicion Prioritaria";

	public CrucePosicionPrioritaria() {
		super.name = name; 
	}

	private int numSelected = 3;

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

        List<Integer> selected = new ArrayList<>(numSelected);
        Pair<Integer, Integer> range = MyRandom.getRandomNoRepeat(selected, numSelected, 0, individuo.getSize() - 1);
        int last = range.getValue1();

        Map<Integer, Integer> map = new HashMap<>(individuo.getSize());
        Map<Integer, Integer> mapPrev = new HashMap<>(individuoPrev.getSize());
        for (int j = 0; j < selected.size(); j++) {
            map.put(selected.get(j), individuoPrev.getGen(selected.get(j)));
            mapPrev.put(selected.get(j), individuo.getGen(selected.get(j)));
        }

        mutateInd(individuo, map, last);
        mutateInd(individuoPrev, mapPrev, last);
    }

}
