package algorithm.operations.cruces;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoReal;

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
            double aux = padre1.get(j);
            padre1.set(j, alpha*aux + (1-alpha)*padre2.get(j));
            padre2.set(j, alpha*padre2.get(j) + (1-alpha)*aux);
        }
    }
    
}
