package algorithm.operations.mutaciones;

import java.util.ArrayList;
import java.util.List;

import algorithm.individuos.Individuo;
import auxiliar.MyRandom;

public class MutacionHeuristica extends Mutacion {

	public static final String name = "Mutacion por heuristica";

	public MutacionHeuristica() {
		super.name = name; 
	}

	private Individuo individuo;
	private List<Integer> selected;
	int numSelected = 3;

	private void compareIndividuo(Individuo copied) {
		copied.setAptitud(copied.fitness());
        if (copied.getAptitud() < individuo.getAptitud())
			for (int i = 0; i < copied.getSize(); i++) 
				individuo.set(i, copied.get(i));
    }

	private void heapPermutation(Individuo copied, int size) {

        if (size == 1) compareIndividuo(copied);
 
        for (int i = 0; i < size; i++) {
            heapPermutation(copied, size - 1);
            if (size % 2 == 1) copied.swapGen(selected.get(0), selected.get(size - 1));
            else copied.swapGen(selected.get(i), selected.get(size - 1));
        }
    }

	@Override
	public void mutar(Individuo individuo) {
		this.individuo = individuo;
		selected = new ArrayList<>(numSelected);
		MyRandom.getRandomNoRepeat(selected, numSelected, 0, individuo.getSize() - 1);
		heapPermutation(individuo.copy(), numSelected);
	}

}
