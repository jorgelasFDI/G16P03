package algorithm.operations.mutaciones;

import java.util.ArrayList;
import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;
import auxiliar.MyRandom;

public class MutacionHeuristica extends Mutacion {

	public static final String name = "Mutacion por heuristica";

	public MutacionHeuristica() {
		super.name = name; 
	}

	private IndividuoVuelo individuo;
	private List<Integer> selected;
	private int numSelected = 3;

	private void compareIndividuo(IndividuoVuelo copied) {
		copied.setAptitud(copied.fitness());
        if (copied.getAptitud() < individuo.getAptitud() && individuo.getFunction().getMinimizar()) {
			for (int i = 0; i < selected.size(); i++)
				individuo.set(selected.get(i), copied.get(selected.get(i)));
		} else if (copied.getAptitud() > individuo.getAptitud() && !individuo.getFunction().getMinimizar()) {
			for (int i = 0; i < selected.size(); i++)
				individuo.set(selected.get(i), copied.get(selected.get(i)));
		}	
    }

	private void permutation(IndividuoVuelo copied, int size) {

        if (size == 1) compareIndividuo(copied);
 
        for (int i = 0; i < size; i++) {
            permutation(copied, size - 1);
            if (size % 2 == 1) copied.swapGen(selected.get(0), selected.get(size - 1));
            else copied.swapGen(selected.get(i), selected.get(size - 1));
        }
    }

	@Override
	public void mutar(Individuo cromosoma) {
		this.individuo = (IndividuoVuelo) cromosoma;
		individuo.setAptitud(individuo.fitness());
		selected = new ArrayList<>(numSelected);
		MyRandom.getRandomNoRepeat(selected, numSelected, 0, individuo.getSize() - 1);
		permutation((IndividuoVuelo) individuo.copy(), numSelected);
	}

}
