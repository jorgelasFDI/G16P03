package gui;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.javatuples.Pair;

import algorithm.population.Poblacion;

public interface View {

    public List<Pair<List<Pair<String, JComponent>>, JPanel>> getPanelList();

    public Poblacion getPoblacion();
    
}
