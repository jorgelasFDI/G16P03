package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import algorithm.AlgoritmoGenetico;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoVuelo;
import algorithm.population.Generaciones;
import auxiliar.vuelo.Vuelo;

public class BestIndTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndividuoVuelo ind;
	private int numFilas = 3;
	List<String> fileNames = Arrays.asList("Identificador", "Tiempo de llegada", "Pista");
	List<String> columnNames = new ArrayList<>();
	

	public BestIndTableModel(Individuo mejorIndividuo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//this.setValueAt("Vuelo", 0, 0);
		//this.setValueAt("Identificador", 1, 0);
		//this.setValueAt("Tiempo de llegada", 2, 0);
		//this.setValueAt("Pista", 3, 0);
		
		for(int i = 0; i < fileNames.size(); i++) 
			this.setValueAt(fileNames.get(i), i, 0);
		
		ind = new IndividuoVuelo((IndividuoVuelo) mejorIndividuo);
		columnNames.clear();
		
		columnNames.add("Vuelos");
		for(int i = 0; i < mejorIndividuo.getSize(); i++) {
			columnNames.add("" + mejorIndividuo.get(i));
		}
		fireTableStructureChanged();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
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
		
		if(columnIndex == 0)
			return fileNames.get(rowIndex);
		
		int idx = (int) ind.get(columnIndex - 1); 
		
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

}
