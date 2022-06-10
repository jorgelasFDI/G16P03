package algorithm.operations.cruces;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import auxiliar.MyRandom;
import auxiliar.binary.GenRange;
import auxiliar.binary.RealGen;

public class CruceBLX extends Cruce {

    public static final String name = "Cruce BLX";

	public CruceBLX() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        IndividuoReal padre1 = (IndividuoReal) individuoPrev;
		IndividuoReal padre2 = (IndividuoReal) individuo;
        double alpha = 0.6;

        for (int i = 0; i < padre1.getSize(); i++) {
            double max = Math.max(padre1.get(i).getFenotipo(), padre2.get(i).getFenotipo());
            double min = Math.min(padre1.get(i).getFenotipo(), padre2.get(i).getFenotipo());
            double diff = max - min;
            max += diff*alpha; min -= diff*alpha;
            GenRange range = padre1.get(i).getRange();
            max = Math.min(max, range.getMax());
            min = Math.max(min, range.getMin());
            padre1.set(i, new RealGen(MyRandom.getRandomDouble(min, max), range));
            padre2.set(i, new RealGen(MyRandom.getRandomDouble(min, max), range));
        }
    }
    
}
