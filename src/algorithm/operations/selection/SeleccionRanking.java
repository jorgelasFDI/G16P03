package algorithm.operations.selection;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class SeleccionRanking extends Operation {

	public static final String name = "Seleccion por Ranking";
	static private final double beta = 1.5;

	public SeleccionRanking() {
		super.name = name; 
	} 
	
	@Override
	public void operationInstance(List<Individuo> poblacion) {
		// TODO Auto-generated method stub
		rankingPuntuacion(poblacion);
		Operation seleccion = new SeleccionRuleta();
		Operation.operate(seleccion, poblacion);
	}

	private void rankingPuntuacion(List<Individuo> poblacion) {
		// TODO Auto-generated method stub
		double puntAcum = 0.0;
		
		for(int i = 0; i < poblacion.size(); i++) {
			double punt = (double)1/poblacion.size();
			
			punt = (double)punt * (beta - (2 * (beta - 1) * ((i - 1) / ((double)poblacion.size() - 1))));
			
			//punt *= 2 * (beta - 1);
			//punt = beta - punt;
			//punt = (double)punt * ((double)1 / poblacion.size());
			
			poblacion.get(i).setPuntuacionAcumulada(puntAcum);
			poblacion.get(i).setPuntuacion(punt);
			
			puntAcum += punt;
		}
	}

}
