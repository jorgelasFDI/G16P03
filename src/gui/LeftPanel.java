package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import algorithm.operations.cruces.*;
import algorithm.operations.mutaciones.*;
import algorithm.functions.Function;
import algorithm.functions.Function1;
import algorithm.operations.Operation;
import algorithm.population.Poblacion;
import algorithm.population.PoblacionTree;
import auxiliar.tree.LogicalNode;
import algorithm.operations.selection.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

//@SuppressWarnings("all")

public class LeftPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private MainFrame frame;

	// SELECCIONES
	private JComboBox<Operation> seleccionComboBox = new JComboBox<>();

	private List<Operation> seleccionesGenericas = Arrays.asList(
		new SeleccionRuleta(),
        new SeleccionEstocastica(),
        new SeleccionRestos(),
        new SeleccionTruncamiento(),
        new SeleccionTorneoDeterministico(),
        new SeleccionTorneoProbabilistico(),
        new SeleccionRanking()
	);

	// MUTACIONES
	private JComboBox<Operation> mutacionComboBox = new JComboBox<>();

	private List<Operation> mutacionesGenericas = Arrays.asList(
		new MutacionTerminal(),
		new MutacionFunctional(),
		new MutacionSubArbol()
	);

	// CRUCES
	private JComboBox<Operation> cruceComboBox = new JComboBox<>();

	private List<Operation> crucesGenericos = Arrays.asList(
		new CruceIntercambioArboles()
	);

	// FUNCIONES

	private JComboBox<Function> functionComboBox = new JComboBox<>();

	private List<Function> funcionesGenericas = Arrays.asList(
		new Function1()
	);

	private List<String> tipos = Arrays.asList("Completo", "Creciente", "RampedAndHalf");
	private JComboBox<String> typeComboBox = new JComboBox<>(tipos.toArray(new String[0]));
	
	private int altura = 3;
	private JSpinner alturaSpinner = new JSpinner(new SpinnerNumberModel(altura, 2, 10, 1));
	// TEXT FIELDS AND OTHERS
    private JTextField sizeTextField = new JTextField("100");
    private JTextField generacionesTextField = new JTextField("100");
    private JTextField eliteTextField = new JTextField("0");
	private JTextField mutacionTextField = new JTextField("5");
	private JTextField cruceTextField = new JTextField("60");
	private JTextField presionTextField = new JTextField("1.2");
	
	private JButton enter = new JButton("Ejecutar");
	private int globalMargin = 20;

	private JPanel rangesPanelWrap = new JPanel();
	private JPanel rangesPanel = new JPanel();

	private List<Integer> solution = new ArrayList<>(
		Arrays.asList(0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1)
	); private List<List<Integer>> combinaciones = new ArrayList<>();
	private List<String> allvalues = new ArrayList<>(Arrays.asList("A0", "A1", "D0", "D1", "D2", "D3", "AND", "OR", "IF", "NOT")); 

	public void generateCombinations() {
		int num_combinations = 64;
		
		for(int i = 0; i < num_combinations; i++) {
			String binary = Integer.toBinaryString(i);
			String[] combString = binary.split("");
			ArrayList<Integer> comb = new ArrayList<>();
			
			for(int j = 0; j < 6 - combString.length; j++)
				comb.add(0);
			
			for(int j = 0; j < combString.length; j++)
				comb.add(Integer.parseInt(combString[j]));
			
			combinaciones.add(comb);
		}

		for (String term: allvalues) {
			LogicalNode.add(term);
		}

	}
		
	public LeftPanel(MainFrame mainFrame) {
    	this.frame = mainFrame;
		generateCombinations();
		initGUI();
	}
     
    private void addLabel(String label, JPanel panel) {
		if (panel == null) panel = new JPanel();
        panel.add(new JLabel(label));
    }

    private JPanel addComponentPanel(JComponent box, String label, int margin, JPanel panel, JPanel parent) {
		return addComponentPanel(Arrays.asList(new Pair<>(label, box)), margin, panel, parent);
    }

	private JPanel addComponentPanel(List<Pair<String, JComponent>> box, int margin, JPanel panel, JPanel parent) {
        if (panel == null) {
			panel = new JPanel();
    		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		}  panel.add(Box.createVerticalStrut(margin));

    	for (Pair<String, JComponent> pair: box) {
			addLabel(pair.getValue0(), panel);
			panel.add(pair.getValue1());
		}
    	
		if (parent != null) parent.add(panel);
		return panel;
    }
    
    private void initGUI(){

    	seleccionComboBox.setModel(new DefaultComboBoxModel<Operation>(seleccionesGenericas.toArray(new Operation[0])));
		cruceComboBox.setModel(new DefaultComboBoxModel<Operation>(crucesGenericos.toArray(new Operation[0])));
		mutacionComboBox.setModel(new DefaultComboBoxModel<Operation>(mutacionesGenericas.toArray(new Operation[0])));
		functionComboBox.setModel(new DefaultComboBoxModel<Function>(funcionesGenericas.toArray(new Function[0])));
		// SET LAYOUTS
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		rangesPanelWrap.setLayout(new BoxLayout(rangesPanelWrap, BoxLayout.Y_AXIS));
		rangesPanel.setLayout(new BoxLayout(rangesPanel, BoxLayout.Y_AXIS));

		addComponentPanel(Arrays.asList(
			new Pair<>("Funcion", functionComboBox)
		), globalMargin, this, null);
		// ADD COMPONENTS
		addComponentPanel(Arrays.asList(
			new Pair<>("Tipo de �rbol", typeComboBox)
		), globalMargin, this, null);

		addComponentPanel(sizeTextField, "Tamaño población", globalMargin, this, null);
		addComponentPanel(generacionesTextField, "Numero de generaciones", globalMargin, this, null);
		addComponentPanel(alturaSpinner, "Altura del �rbol", globalMargin, this, null);
		
        addComponentPanel(Arrays.asList(
			new Pair<>("Seleccion", seleccionComboBox), 
			new Pair<>("Maxima presion selectiva", presionTextField)
		), globalMargin, this, null);
        
        addComponentPanel(Arrays.asList(
			new Pair<>("Tipo de cruce", cruceComboBox), 
			new Pair<>("Probabilidad de cruce", cruceTextField)
		), globalMargin, null, this);
        
        addComponentPanel(Arrays.asList(
			new Pair<>("Tipo de mutacion", mutacionComboBox), 
			new Pair<>("Probabilidad de mutacion", mutacionTextField)
		), globalMargin, null, this);
        
        addComponentPanel(eliteTextField, "% Elite", globalMargin, this, null);
    	
		// ENTER BUTTON TO START EVERYTHING
    	add(enter);
        enter.addActionListener(e -> {
            Operation cruz = (Operation) cruceComboBox.getSelectedItem();
            Operation mut = (Operation) mutacionComboBox.getSelectedItem();
			Operation select = (Operation) seleccionComboBox.getSelectedItem();
			String type = (String) typeComboBox.getSelectedItem();
			int depth = (int) alturaSpinner.getModel().getValue();
			Function1 function = (Function1) functionComboBox.getSelectedItem();
			LogicalNode.setCombinaciones(combinaciones);
			LogicalNode.setSolution(solution);

			int size = Integer.parseInt(sizeTextField.getText());
			int numGeneraciones = Integer.parseInt(generacionesTextField.getText());

			double probElite = Double.parseDouble(eliteTextField.getText())/100.0;
			double probMutacion = Double.parseDouble(mutacionTextField.getText())/100.0;
			double probCruce = Double.parseDouble(cruceTextField.getText())/100.0;
			double presion = Double.parseDouble(presionTextField.getText());
			
			mut.setProb(probMutacion);
			cruz.setProb(probCruce);

			Poblacion poblacion = new Poblacion(new PoblacionTree(), size, probElite, cruz, mut, select, presion);
			poblacion.generaPoblacion(type, depth, size, function);

			frame.setPoblacion(poblacion);
			frame.run(numGeneraciones);
		
        });
    }

}
