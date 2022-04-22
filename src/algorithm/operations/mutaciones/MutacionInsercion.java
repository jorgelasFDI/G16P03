package algorithm.operations.mutaciones;

import java.util.HashMap;
import java.util.Map;

import algorithm.individuos.Individuo;

public class MutacionInsercion extends Mutacion {

	public static final String name = "Mutacion por insercion";

	public MutacionInsercion() {
		super.name = name; 
	}

	private int numSelected = 2;

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
	public void mutar(Individuo individuo) {

		Map<Integer, Integer> indexArray = new HashMap<>(numSelected);
		while (indexArray.size() < numSelected) {
			int value = -1;
			int idx = random.nextInt(individuo.getSize() - 1);
			if (indexArray.containsKey(idx)) continue;

			while (true) {
				value = individuo.getGen(random.nextInt(individuo.getSize() - 1));
				if (!indexArray.containsValue(value)) break;
			} 
			
			indexArray.put(idx, value);
		}

		mutateInd(individuo, indexArray, -1);
	}

}
