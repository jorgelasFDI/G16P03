package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import algorithm.AlgoritmoGenetico;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;
import algorithm.population.Generaciones;
import auxiliar.vuelo.Vuelo;

public class BestIndTableModel extends AbstractTableModel implements Observers{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndividuoVuelo ind;
	private int numColumnas;
	private int numFilas = 3;
	List<String> columnNames = new ArrayList<>();
	
	public BestIndTableModel(AlgoritmoGenetico alg) {alg.addObserver(this);}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return numColumnas;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return numFilas;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		int idx = (int) ind.get(columnIndex); 
		
		return getRealValue(rowIndex, idx);
	}
	
	public Object getRealValue(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		Vuelo vuelo = ind.getObject(columnIndex);
		switch(rowIndex) {
		case 0: 
			return vuelo.getIdVuelo();
		case 1: 
			return vuelo.getTLA();
		case 2: 
			return vuelo.getPista();
		}
		return null;
	}

	@Override
	public void actualizaVista(Generaciones generaciones, Individuo mejorIndividuo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ind = new IndividuoVuelo((IndividuoVuelo) mejorIndividuo);
				numColumnas = ind.getSize();
				columnNames.clear();
				for(int i = 0; i < mejorIndividuo.getSize(); i++) {
					columnNames.add("Vuelo " + mejorIndividuo.get(i));
				}
				fireTableStructureChanged();
	}

	@Override
	public void actualizaView(View view) {
		// TODO Auto-generated method stub
		
	}

}
