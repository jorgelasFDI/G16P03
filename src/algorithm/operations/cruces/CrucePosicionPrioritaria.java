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

	private int numSelected = 3;

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

        List<Integer> selected = new ArrayList<>(numSelected);
        Pair<Integer, Integer> range = MyRandom.getRandomNoRepeat(selected, numSelected, 0, individuo.getSize() - 1);
        int last = range.getValue1();

        Map<Integer, Integer> map = new HashMap<>(padre2.getSize());
        Map<Integer, Integer> mapPrev = new HashMap<>(padre1.getSize());
        for (int j = 0; j < selected.size(); j++) {
            map.put(selected.get(j), padre1.get(selected.get(j)));
            mapPrev.put(selected.get(j), padre2.get(selected.get(j)));
        }

        mutateInd(padre2, map, last);
        mutateInd(padre1, mapPrev, last);
    }

}
