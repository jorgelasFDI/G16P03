package algorithm.operations.cruces;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;
import auxiliar.binary.GenRange;
import auxiliar.binary.RealGen;

public class CruceAritmetico extends Cruce {

    public static final String name = "Cruce Aritmetico";

	public CruceAritmetico() {
		super.name = name; 
	}

    @Override
    public void cruzar(Individuo individuoPrev, Individuo individuo) {
        double alpha = 0.6;
        IndividuoReal padre1 = (IndividuoReal) individuoPrev;
		IndividuoReal padre2 = (IndividuoReal) individuo;
        for (int j = 0; j < padre2.getSize(); j++) {
            RealGen aux = padre1.get(j);
            GenRange range = aux.getRange();
            padre1.set(j, new RealGen(alpha*aux.getFenotipo() + (1-alpha)*padre2.get(j).getFenotipo(), range));
            padre2.set(j, new RealGen(alpha*padre2.get(j).getFenotipo() + (1-alpha)*aux.getFenotipo(), range));
        }
    }
    
}
