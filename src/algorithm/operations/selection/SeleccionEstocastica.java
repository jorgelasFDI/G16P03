package algorithm.operations.selection;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class SeleccionEstocastica extends Operation {

	public static final String name = "Seleccion Estocastica";

	public SeleccionEstocastica() {
		super.name = name; 
	}

	@Override
	public void operationInstance(List<Individuo> poblacion) {
		// TODO Auto-generated method stub
		List<Individuo> nuevaPoblacion = copy(poblacion);
		double prob = (1.0/(double)poblacion.size()) * random.nextDouble();
		double aux = prob;
		int posElegida = 0;
		for(int i = 0; i < poblacion.size(); i++) {
			while(posElegida < poblacion.size() - 1 && prob > poblacion.get(posElegida).getPuntuacionAcumulada()) posElegida++;
			poblacion.set(i, nuevaPoblacion.get(posElegida).copy());    //new Individuo(nuevaPoblacion.get(posElegida)
			prob += aux;          //Comprobar si sale de rango
		}
	}

}
