package gui;
import java.awt.BorderLayout;import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import algorithm.AlgoritmoGenetico;
import algorithm.functions.*;
import algorithm.individuos.Individuo;
import algorithm.operations.Operation;
import algorithm.population.Generaciones;
import algorithm.population.GeneratePoblacion;
import algorithm.population.Poblacion;
import auxiliar.MyGui;
import auxiliar.binary.GenRange;

public class PanelInfo3 implements View {

    private JComboBox<Operation> seleccionComboBox;
    private JComboBox<Operation> mutacionComboBox;
    private JComboBox<Operation> cruceComboBox;
    private JComboBox<Function> functionComboBox;
    private JSpinner profundidadSpinner;
	private String type;
	private int profundidad;

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
		this.profundidadSpinner = new JSpinner(new SpinnerNumberModel(3, 3, 7, 1));
    }

    @Override
    public List<Pair<List<Pair<String, JComponent>>, JPanel>> getPanelList() {
        return Arrays.asList(
            new Pair<>(Arrays.asList(
				new Pair<>("Funcion", functionComboBox)
			), null),
            new Pair<>(Arrays.asList(
    				new Pair<>("Profundidad del arbol", functionComboBox)
    			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Selecciones", seleccionComboBox)
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
		Function function = (Function) functionComboBox.getSelectedItem();
		Operation cruce = (Operation) cruceComboBox.getSelectedItem();
		Operation mutacion = (Operation) mutacionComboBox.getSelectedItem();
		Operation seleccion = (Operation) seleccionComboBox.getSelectedItem();
		cruce.setProb(Double.parseDouble(cruceTextField.getText())/100.0);
		mutacion.setProb(Double.parseDouble(mutacionTextField.getText())/100.0);
		profundidad = (int) profundidadSpinner.getValue();
		Poblacion poblacion = new Poblacion(size, eliteSize, cruce, mutacion, seleccion, null);
		poblacion.generaPoblacion((new GeneratePoblacion()).generaPoblacion(type, profundidad, size, null, null, null, function, poblacion), function);
		return poblacion;
	}
	
    @Override
	public JPanel getBottomPanel(Generaciones generaciones, Individuo mejorIndividuo, AlgoritmoGenetico alg) {
		// TODO Auto-generated method stub
    	JPanel panel = new JPanel();
    	
    	panel.setLayout(new BorderLayout());
    	TitledBorder border = BorderFactory.createTitledBorder(
    			BorderFactory.createLineBorder(Color.black, 2),
    			"Mejor individuo",
    			TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(border);
		
		if(mejorIndividuo != null)
			MyGui.addLabel("La aptitud del individuo es: " + mejorIndividuo.getAptitud(), panel);
		
		return panel;
	}
}
