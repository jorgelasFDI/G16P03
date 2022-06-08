package algorithm.operations.cruces;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import algorithm.operations.Operation;
import auxiliar.binary.Gen;
import auxiliar.binary.GenRange;
import auxiliar.binary.RealGen;

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
            if (((RealGen)padre2.get(j)).getFenotipo() > max) max = padre2.get(j).getFenotipo();
            if (((RealGen)padre2.get(j)).getFenotipo() < min) min = padre2.get(j).getFenotipo();
            if (((RealGen)padre1.get(j)).getFenotipo() > max) max = padre1.get(j).getFenotipo();
            if (((RealGen)padre1.get(j)).getFenotipo() < min) min = padre1.get(j).getFenotipo();
        } double diff = max - min;

        max += diff*alpha;
        min -= diff*alpha;

        for (int z = 0; z < padre2.getSize(); z++) {
            GenRange range = padre1.get(z).getRange();
            padre1.set(z, new RealGen(random.nextDouble()*(max - min) + min, range));
            padre2.set(z, new RealGen(random.nextDouble()*(max - min) + min, range));
        }
    }
    
}
