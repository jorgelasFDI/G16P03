package algorithm.operations.selection;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class SeleccionTorneoProbabilistico extends Operation {

	public static final String name = "Seleccion Torneo Probabilistico";

	public SeleccionTorneoProbabilistico() {
		super.name = name; 
	}


	@Override
	public void operationInstance(List<Individuo> poblacion) {

		/*
		 * Se diferencia en el paso de selecci�n del ganador del torneo.
			En vez de escoger siempre el mejor se genera un n�mero aleatorio del intervalo
			[0..1], si es mayor que un par�metro p se escoge el individuo m�s alto y en
			caso contrario el menos apto. Generalmente p toma valores en el rango [0.5 ,1]
		 */
		double p = 0.5;
		List<Individuo> antiguaPoblacion = copy(poblacion);
		int elegido = -1, otro = -1;
		
		for(int i = 0; i < poblacion.size(); i++) {
			elegido = random.nextInt(poblacion.size());
			while(otro != elegido)
				otro = random.nextInt(poblacion.size());
			
			double d = random.nextDouble();
			
			if(d >= p)
				poblacion.set(i, antiguaPoblacion.get(elegido).copy());
			else
				poblacion.set(i, antiguaPoblacion.get(otro).copy());
			
			elegido = -1;    otro = -1;
		}
	}

}
