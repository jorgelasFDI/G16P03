package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import algorithm.AlgoritmoGenetico;
import algorithm.individuos.Individuo;
import algorithm.individuos.Vuelo;
import algorithm.population.Generaciones;

public class BestIndTableModel extends AbstractTableModel implements Observers{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Individuo ind;
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
		
		int idx = ind.getGen(columnIndex); 
		
		return getRealValue(rowIndex, idx);
	}
	
	public Object getRealValue(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		Vuelo vuelo = ind.getVuelo(columnIndex);
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
		ind = new Individuo(mejorIndividuo);
		numColumnas = ind.getSize();
		columnNames.clear();
		for(int i = 0; i < mejorIndividuo.getSize(); i++) {
			columnNames.add("Vuelo " + mejorIndividuo.getGen(i));
		}
		fireTableStructureChanged();
	}

}
