package algorithm.operations.cruces;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import algorithm.operations.Operation;

public class CruceBLX extends Cruce {

    public static final String name = "Cruce BLX";

	public CruceBLX() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        IndividuoReal padre1 = (IndividuoReal) individuoPrev;
		IndividuoReal padre2 = (IndividuoReal) individuo;
        double alpha = 0.6;
        for (int j = 0; j < padre2.getSize(); j++) {    //Extrae el mejor y peoe gen de los 2 individuos o cromosomas
            if (padre2.get(j) > max) max = padre2.get(j);
            if (padre2.get(j) < min) min = padre2.get(j);
            if (padre1.get(j) > max) max = padre1.get(j);
            if (padre1.get(j) < min) min = padre1.get(j);
        } double diff = max - min;

        max += diff*alpha;
        min -= diff*alpha;

        for (int z = 0; z < padre2.getSize(); z++) {
            padre1.set(z, random.nextDouble()*(max - min) + min);
            padre2.set(z, random.nextDouble()*(max - min) + min);
        }
    }
    
}
