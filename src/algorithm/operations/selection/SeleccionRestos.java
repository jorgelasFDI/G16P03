package algorithm.operations.selection;

import java.util.ArrayList;
import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;
import algorithm.population.Poblacion;

public class SeleccionRestos extends Operation {

	public static final String name = "Seleccion Restos";

	public SeleccionRestos() {
		super.name = name; 
	}

	@Override
	public void operationInstance(List<Individuo> poblacion) {
		int ind = poblacion.size() - 1;
		double limit = 1.0;
		Operation seleccion = new SeleccionEstocastica();

		List<Individuo> nuevaPoblacion = new ArrayList<>();
		while(poblacion.get(ind).getPuntuacion() * poblacion.size() < limit)
			nuevaPoblacion.add(poblacion.get(ind--).copy());

		Poblacion.evalua(nuevaPoblacion, nuevaPoblacion.get(0).getAptitud() >= nuevaPoblacion.get(nuevaPoblacion.size() - 1).getAptitud(), null, null, 0);
		if (nuevaPoblacion.size() > 0) Operation.operate(seleccion, nuevaPoblacion);

		for (int i = ind + 1, j = 0; i < poblacion.size(); i++, j++)
			poblacion.set(i, nuevaPoblacion.get(j).copy());
	}

}
