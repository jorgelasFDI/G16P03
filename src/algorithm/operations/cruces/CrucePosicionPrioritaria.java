package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;
import auxiliar.MyRandom;

public class CrucePosicionPrioritaria extends Cruce {

	public static final String name = "Cruce Posicion Prioritaria";

	public CrucePosicionPrioritaria() {
		super.name = name; 
	}

	private void mutateInd(IndividuoVuelo individuo, Map<Integer, Integer> map, int start) {
        IndividuoVuelo newIndividuo = (IndividuoVuelo) individuo.copy();

        for (int j = start, k = start, counter = 0; counter < individuo.getSize(); ) {
            j %= individuo.getSize();
            k %= individuo.getSize();
            int gen = individuo.get(j);
            if (map.containsKey(k)) {
                newIndividuo.set(k, map.get(k));
                k++; counter++;
            } else if (!map.containsValue(gen)) {
                newIndividuo.set(k, gen);
                k++; j++; counter++;
            } else j++;
        }

        for (int j = 0; j < individuo.getSize(); j++)  individuo.set(j, newIndividuo.get(j));
    }

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        int numSelected = 3;
        IndividuoVuelo padre1 = (IndividuoVuelo) individuoPrev;
		IndividuoVuelo padre2 = (IndividuoVuelo) individuo;

        List<Integer> selected = new ArrayList<>(numSelected);
        Pair<Integer, Integer> range = MyRandom.getRandomNoRepeat(selected, numSelected, 0, individuo.getSize() - 1);
        int last = range.getValue1();

        Map<Integer, Integer> mapPrev = new HashMap<>(padre1.getSize());
        Map<Integer, Integer> map = new HashMap<>(padre2.getSize());
        for (int j = 0; j < numSelected; j++) {
            map.put(selected.get(j), padre1.get(selected.get(j)));
            mapPrev.put(selected.get(j), padre2.get(selected.get(j)));
        }

        mutateInd(padre1, mapPrev, last + 1);
        mutateInd(padre2, map, last + 1);
    }

}
