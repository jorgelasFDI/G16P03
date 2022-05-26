package algorithm.operations.mutaciones;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;

public class MutacionBinaria extends Mutacion {

    public static final String name = "Mutacion Binaria";

	public MutacionBinaria() {
		super.name = name; 
	}

    @Override
    public void mutar(Individuo individuo) {
        IndividuoBinary bin = (IndividuoBinary) individuo;
        for(int j = 0; j < individuo.getSize(); j++){
            bin.flip(j);
        }
    }

}
