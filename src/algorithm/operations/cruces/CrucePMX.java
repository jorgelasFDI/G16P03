package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;
import auxiliar.MyRandom;

public class CrucePMX extends Cruce {

    public static final String name = "Cruce PMX";

	public CrucePMX() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        IndividuoVuelo padre1 = (IndividuoVuelo) individuoPrev;
		IndividuoVuelo padre2 = (IndividuoVuelo) individuo;
        List<Integer> selected = new ArrayList<>(2);
        Pair<Integer, Integer> range = MyRandom.getRandomNoRepeat(selected, 2, 0, individuo.getSize() - 1);
        int first = range.getValue0();
        int last = range.getValue1();

        Map<Integer, Integer> prevMap = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = first; j <= last; j++) prevMap.put(padre2.get(j), padre1.get(j));
        for (int j = first; j <= last; j++) map.put(padre1.get(j), padre2.get(j));
        
        for (int j = first; j <= last; j++) padre2.swapGen(j, padre1);

        for (int j = 0; j < first; j++) {
            while (prevMap.containsKey(padre1.get(j))) padre1.set(j, prevMap.get(padre1.get(j)));
            while (map.containsKey(padre2.get(j))) padre2.set(j, map.get(padre2.get(j)));
        }

        for (int j = last + 1; j < padre2.getSize(); j++) {
            while (prevMap.containsKey(padre1.get(j))) padre1.set(j, prevMap.get(padre1.get(j)));
            while (map.containsKey(padre2.get(j))) padre2.set(j, map.get(padre2.get(j)));
        }
    }

}
