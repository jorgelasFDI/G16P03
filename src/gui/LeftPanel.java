package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import algorithm.operations.cruces.*;
import algorithm.individuos.tree.GenTree;
import algorithm.operations.mutaciones.*;
import algorithm.operations.Operation;
import algorithm.population.Poblacion;
import auxiliar.MyRandom;
import auxiliar.RandomGenerator;
import algorithm.operations.selection.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		new MutacionFuncional(),
		new MutacionSubArbol()
	);

	// CRUCES
	private JComboBox<Operation> cruceComboBox = new JComboBox<>();

	private List<Operation> crucesGenericos = Arrays.asList(
		new CruceIntercambioArboles()
	);
	
	private Map<String, Map<String, Double>> matrizSEP;

	// FUNCIONES
	/*private List<GenTree> functions = Arrays.asList(
		new Tree(),
		new Fitness2()
	);*/ 
	private List<String> tipos = Arrays.asList("Completo", "Creciente", "RampdedAndHalf");
	private JComboBox<String> typeComboBox = new JComboBox<>(tipos.toArray(new String[0]));
	//private JComboBox<String> typeComboBox = new JComboBox<>(types.toArray(types[0]));
	
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
		
	public LeftPanel(MainFrame mainFrame) {
    	this.frame = mainFrame;
    	
    	inicializaDatos();
		initGUI();
	}
	
	private void inicializaDatos() {       //Provisional hasta que podamos modificar los parametros de entrada, primero comprobar que funciona el algoritmo

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
		// SET LAYOUTS
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		rangesPanelWrap.setLayout(new BoxLayout(rangesPanelWrap, BoxLayout.Y_AXIS));
		rangesPanel.setLayout(new BoxLayout(rangesPanel, BoxLayout.Y_AXIS));

		// ADD COMPONENTS
		addComponentPanel(Arrays.asList(
			new Pair<>("Tipo de ·rbol", typeComboBox)
		), globalMargin, this, null);

		addComponentPanel(sizeTextField, "Tama√±o poblaci√≥n", globalMargin, this, null);
		addComponentPanel(generacionesTextField, "Numero de generaciones", globalMargin, this, null);
		addComponentPanel(alturaSpinner, "Altura del ·rbol", globalMargin, this, null);
		
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
			int altura = (int) alturaSpinner.getModel().getValue();
			//function = (TreeGenerator) functionComboBox.getSelectedItem();

			int size = Integer.parseInt(sizeTextField.getText());
			int numGeneraciones = Integer.parseInt(generacionesTextField.getText());

			double probElite = Double.parseDouble(eliteTextField.getText())/100.0;
			double probMutacion = Double.parseDouble(mutacionTextField.getText())/100.0;
			double probCruce = Double.parseDouble(cruceTextField.getText())/100.0;
			double presion = Double.parseDouble(presionTextField.getText());
			
			mut.setProb(probMutacion);
			cruz.setProb(probCruce);
			
			Poblacion poblacion = new Poblacion(size, probElite, cruz, mut, select, presion, type, altura);
			frame.setPoblacion(poblacion);
			frame.run(numGeneraciones);
		
        });
    }

}
