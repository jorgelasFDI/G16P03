package algorithm.operations.mutaciones;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public abstract class Mutacion extends Operation {

    @Override
    public void operationInstance(List<Individuo> poblacion) {
        for(int i = 0; i < poblacion.size(); i++) {  //(int i = 0; i < size; i++)
			Individuo individuo = poblacion.get(i);
			if (random.nextDouble() < prob) {
                mutar(individuo);
                individuo.setAptitud(individuo.fitness());
			}
        }
    }

    public abstract void mutar(Individuo individuo);
    
}
