package gui;
import java.awt.BorderLayout;import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import algorithm.functions.*;
import algorithm.individuos.Individuo;
import algorithm.operations.Operation;
import algorithm.population.Generaciones;
import algorithm.population.Poblacion;
import auxiliar.MyGui;

public class PanelInfo3 implements View {

    private JComboBox<Operation> seleccionComboBox;
    private JComboBox<Operation> mutacionComboBox;
    private JComboBox<Operation> cruceComboBox;
    private JComboBox<Function> functionComboBox;
	private String type;

	private JTextField mutacionTextField = new JTextField("50");
	private JTextField cruceTextField = new JTextField("70");
	private JTextField presionTextField = new JTextField("1.5");

    public PanelInfo3(JComboBox<Operation> seleccionComboBox, JComboBox<Operation> mutacionComboBox,
            JComboBox<Operation> cruceComboBox, JComboBox<Function> functionComboBox, String type) {
        this.seleccionComboBox = seleccionComboBox;
        this.cruceComboBox = cruceComboBox;
        this.mutacionComboBox = mutacionComboBox;
        this.functionComboBox = functionComboBox;
		this.type = type;
    }

    @Override
    public List<Pair<List<Pair<String, JComponent>>, JPanel>> getPanelList() {
        return Arrays.asList(
            new Pair<>(Arrays.asList(
				new Pair<>("Funcion", functionComboBox)
			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Selecciones", seleccionComboBox),
				new Pair<>("Maxima presion selectiva", presionTextField)
			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Mutaciones", mutacionComboBox),
				new Pair<>("Probabilidad mutacion", mutacionTextField)
			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Cruces", cruceComboBox),
				new Pair<>("Probabilidad cruce", cruceTextField)
			), null)
        );
    }

	@Override
	public Poblacion getPoblacion(int size, double eliteSize) {
		return null;
	}
	
    @Override
	public JPanel getBottomPanel(Generaciones generaciones, Individuo mejorIndividuo) {
		// TODO Auto-generated method stub
    	JPanel panel = new JPanel();
    	
    	panel.setLayout(new BorderLayout());
    	TitledBorder border = BorderFactory.createTitledBorder(
    			BorderFactory.createLineBorder(Color.black, 2),
    			"Mejor individuo",
    			TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(border);
		MyGui.addLabel("La aptitud del individuo es: ", panel);
		
		return panel;
	}
}
