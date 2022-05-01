package algorithm.operations.selection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import algorithm.individuos.Individuo;
import algorithm.operations.Operation;

public class SeleccionTorneoDeterministico extends Operation {

	public static final String name = "Seleccion Torneo Deterministico";

	public SeleccionTorneoDeterministico() {
		super.name = name; 
	}

	@Override
	public void operationInstance(List<Individuo> poblacion) {

		int nIndividuos = 2;
		List<Individuo> antiguaPoblacion = copy(poblacion);
		List<Boolean> selected = new ArrayList<>(poblacion.size());
		Queue<Integer> colaSelected = new LinkedList<>();    //Creada para que no se repitan los individuos del conjunto seleccionado para el torneo
		
		for(int i = 0; i < poblacion.size(); i++)
			selected.add(false);
		
		for(int i = 0; i < poblacion.size(); i++) {
			while(colaSelected.size() < nIndividuos) {
				int n = random.nextInt(poblacion.size());
				if(!selected.get(n)) {
					colaSelected.add(n);     selected.set(n, true);
				}
			}
			
			Individuo mejorConjunto = null;
			for(Integer ind: colaSelected) {
				if(mejorConjunto == null || mejorConjunto.getAptitudRevisada() < antiguaPoblacion.get(ind).getAptitudRevisada()) {
					mejorConjunto = antiguaPoblacion.get(ind);     				
				}

			}
			poblacion.set(i, mejorConjunto.copy());
			
			for(Integer ind: colaSelected) {
				selected.set(ind, false);
			}
			
			colaSelected.clear();
		}
	}

}
