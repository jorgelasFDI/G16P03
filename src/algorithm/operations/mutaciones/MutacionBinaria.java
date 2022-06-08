package algorithm.operations.mutaciones;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import algorithm.operations.Operation;

public class MutacionBinaria extends Operation {

    public static final String name = "Mutacion Binaria";

	public MutacionBinaria() {
		super.name = name; 
	}

    @Override
    public void operationInstance(List<Individuo> poblacion) {
        for(int i = 0; i < poblacion.size(); i++) {  //(int i = 0; i < size; i++)
			Individuo individuo = poblacion.get(i);
			mutar(individuo);
        }
    }

    public void mutar(Individuo individuo) {
        IndividuoBinary bin = (IndividuoBinary) individuo;
        for(int j = 0; j < individuo.getSize(); j++){
            if (random.nextDouble() < prob) {
                bin.flipBit(j);
			}
        }
    }

}
