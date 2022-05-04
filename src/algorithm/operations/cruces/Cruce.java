package algorithm.operations.cruces;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public abstract class Cruce extends Operation {

    @Override
    public void operationInstance(List<Individuo> poblacion) {
        Individuo individuoPrev = null;
        for (int i = 0; i < poblacion.size(); i++) {
            Individuo individuo = poblacion.get(i);
            if (random.nextDouble() < prob) {
                if (individuoPrev == null) {
                    individuoPrev = individuo;
                    continue;
                }
				
				cruzar(individuoPrev, individuo);

                individuoPrev = null;
            }
        }
        
    }

    public abstract void cruzar(Individuo individuoPrev, Individuo individuo);
    
}
