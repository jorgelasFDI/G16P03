package algorithm.operations.selection;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class SeleccionTruncamiento extends Operation {

	public static final String name = "Seleccion Truncamiento";

	public SeleccionTruncamiento() {
		super.name = name;
	}
	
	private double trunc = 0.2;

	@Override
	public void operationInstance(List<Individuo> poblacion) {
		int indTrunc = (int) (trunc * poblacion.size());
		int aux = poblacion.size() - 1;
		
		for(int i = 0; i < indTrunc; i++) {
			poblacion.set(aux - i, poblacion.get(i).copy());
		}
	}

}
