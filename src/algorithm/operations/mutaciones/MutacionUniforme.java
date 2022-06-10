package algorithm.operations.mutaciones;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import auxiliar.binary.GenRange;
import auxiliar.binary.RealGen;

public class MutacionUniforme extends Mutacion {

    public static final String name = "Mutacion Uniforme";

	public MutacionUniforme() {
		super.name = name; 
    }

    @Override
    public void operationInstance(List<Individuo> poblacion) {
        for(int i = 0; i < poblacion.size(); i++) {
			mutar(poblacion.get(i));
        }
    }

    public void mutar(Individuo individuo) {
        IndividuoReal real = (IndividuoReal) individuo;
        for(int j = 0; j < individuo.getSize(); j++){
            if (random.nextDouble() < prob) {
                GenRange range = real.get(j).getRange();
                real.set(j, new RealGen(random.nextDouble()*range.getDiff() + range.getMin(), range));
			}
        }
    }

}
