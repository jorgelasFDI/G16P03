package algorithm.operations.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import auxiliar.MyRandom;

public class CrucePMX extends Cruce {

    public static final String name = "Cruce PMX";

	public CrucePMX() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        List<Integer> selected = new ArrayList<>(2);
        Pair<Integer, Integer> range = MyRandom.getRandomNoRepeat(selected, 2, 0, individuo.getSize() - 1);
        int first = range.getValue0();
        int last = range.getValue1();

        Map<Integer, Integer> prevMap = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = first; j <= last; j++) prevMap.put(individuo.getGen(j), individuoPrev.getGen(j));
        for (int j = first; j <= last; j++) map.put(individuoPrev.getGen(j), individuo.getGen(j));
        
        for (int j = first; j <= last; j++) individuo.swapBit(j, individuoPrev);

        for (int j = 0; j < first; j++) {
            while (prevMap.containsKey(individuoPrev.getGen(j))) individuoPrev.setGen(j, prevMap.get(individuoPrev.getGen(j)));
            while (map.containsKey(individuo.getGen(j))) individuo.setGen(j, map.get(individuo.getGen(j)));
        }

        for (int j = last + 1; j < individuo.getSize(); j++) {
            while (prevMap.containsKey(individuoPrev.getGen(j))) individuoPrev.setGen(j, prevMap.get(individuoPrev.getGen(j)));
            while (map.containsKey(individuo.getGen(j))) individuo.setGen(j, map.get(individuo.getGen(j)));
        }
    }

}
