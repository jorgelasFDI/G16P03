package algorithm.operations.selection;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class SeleccionRuleta extends Operation {

	public static final String name = "Seleccion Ruleta";

	public SeleccionRuleta() {
		super.name = name; 
	}

	@Override
	public void operationInstance(List<Individuo> poblacion) {
		List<Individuo> nuevaPoblacion = copy(poblacion);
		for(int i = 0; i < poblacion.size(); i++) {
			int posElegida = 0;
			double probRuleta = random.nextDouble();
			while(probRuleta > nuevaPoblacion.get(posElegida).getPuntuacionAcumulada()) posElegida++;
			poblacion.set(i, nuevaPoblacion.get(posElegida).copy());
		} //Se selecciona a los individuos mas aptos y se crea la nueva poblacion
	}

}
