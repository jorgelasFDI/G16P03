package gui;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.javatuples.Pair;

import algorithm.individuos.Individuo;
import algorithm.population.Generaciones;
import algorithm.population.Poblacion;

public interface View {

    public List<Pair<List<Pair<String, JComponent>>, JPanel>> getPanelList();

    public Poblacion getPoblacion(int size, double eliteSize);
    
    public JPanel getBottomPanel(Generaciones generaciones, Individuo mejorIndividuo);

}
