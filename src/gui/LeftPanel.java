package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import algorithm.operations.cruces.*;
import algorithm.functiones.*;
import algorithm.individuos.Vuelo;
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
		new MutacionIntercambio(),
		new MutacionHeuristica(),
		new MutacionInsercion(),
		new MutacionInversion()
	);

	// CRUCES
	private JComboBox<Operation> cruceComboBox = new JComboBox<>();

	private List<Operation> crucesGenericos = Arrays.asList(
		new CruceCiclosCX(),
		new CruceCO(),
		new CruceOrdenOX(),
		new CruceOrdenPrioritario(),
		new CrucePMX(),
		new CrucePosicionPrioritaria()
	);
	
	private Map<String, Map<String, Double>> matrizSEP;

	// FUNCIONES
	private List<Fitness> functions = Arrays.asList(
		new Fitness1(),
		new Fitness2()
	); private JComboBox<Fitness> functionComboBox = new JComboBox<>(functions.toArray(new Fitness[0]));
	
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

	private Fitness function;
	private int numPistas = 3;
	private int numVuelos = 12;
	private int numPistasProv = numPistas;
	private int numVuelosProv = numVuelos;
	
	private JSpinner numPistaSpinner = new JSpinner(new SpinnerNumberModel(numPistas, numPistas, 10, 1));
	private JSpinner numVuelosSpinner = new JSpinner(new SpinnerNumberModel(numVuelos, numVuelos, 50, 1));
	
	private List<Vuelo> vuelos = new ArrayList<>();
	
	public LeftPanel(MainFrame mainFrame) {
    	this.frame = mainFrame;
    	
    	inicializaDatos();
		initGUI();
	}
	
	private void inicializaDatos() {       //Provisional hasta que podamos modificar los parametros de entrada, primero comprobar que funciona el algoritmo
    	
		matrizSEP = new HashMap<>() {{
			put("", new HashMap<String, Double>() {{
				put("P", 0.0); put("W", 0.0); put("G", 0.0);
			}});
			put("W", new HashMap<String, Double>() {{
				put("W", 1.0); put("G", 1.5); put("P", 2.0); 
			}});
			put("G", new HashMap<String, Double>() {{
				put("W", 1.0); put("G", 1.5); put("P", 1.5); 
			}});
			put("P", new HashMap<String, Double>() {{
				put("W", 1.0); put("G", 1.0); put("P", 1.0); 
			}});
			
		}};

		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "W", new ArrayList<Double>(Arrays.asList(11.0, 10.0, 9.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "G", new ArrayList<Double>(Arrays.asList(15.0, 17.0, 19.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "W", new ArrayList<Double>(Arrays.asList(6.0, 7.0, 8.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "W", new ArrayList<Double>(Arrays.asList(6.0, 7.0, 8.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "P", new ArrayList<Double>(Arrays.asList(9.0, 12.0, 15.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "W", new ArrayList<Double>(Arrays.asList(7.0, 6.0, 5.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "G", new ArrayList<Double>(Arrays.asList(15.0, 17.0, 19.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "W", new ArrayList<Double>(Arrays.asList(6.0, 7.0, 8.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "W", new ArrayList<Double>(Arrays.asList(6.0, 7.0, 8.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "P", new ArrayList<Double>(Arrays.asList(9.0, 12.0, 15.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "W", new ArrayList<Double>(Arrays.asList(7.0, 6.0, 5.0)), matrizSEP));
		vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), "G", new ArrayList<Double>(Arrays.asList(9.0, 7.0, 5.0)), matrizSEP));

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
			new Pair<>("Funcion", functionComboBox)
		), globalMargin, this, null);

		addComponentPanel(sizeTextField, "Tamaño población", globalMargin, this, null);
		addComponentPanel(generacionesTextField, "Numero de generaciones", globalMargin, this, null);
		
		addComponentPanel(numPistaSpinner, "Numero de pistas", globalMargin, this, null);
		addComponentPanel(numVuelosSpinner, "Numero de vuelos", globalMargin, this, null);
		
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
        
        numPistaSpinner.addChangeListener(a -> {
        	numPistasProv = (int) numPistaSpinner.getModel().getValue();
        });
        
        numVuelosSpinner.addChangeListener(a -> {
        	numVuelosProv = (int) numVuelosSpinner.getModel().getValue();
        });
    	
		// ENTER BUTTON TO START EVERYTHING
    	add(enter);
        enter.addActionListener(e -> {
            Operation cruz = (Operation) cruceComboBox.getSelectedItem();
            Operation mut = (Operation) mutacionComboBox.getSelectedItem();
			Operation select = (Operation) seleccionComboBox.getSelectedItem();
			function = (Fitness) functionComboBox.getSelectedItem();

			int size = Integer.parseInt(sizeTextField.getText());
			int numGeneraciones = Integer.parseInt(generacionesTextField.getText());

			double probElite = Double.parseDouble(eliteTextField.getText())/100.0;
			double probMutacion = Double.parseDouble(mutacionTextField.getText())/100.0;
			double probCruce = Double.parseDouble(cruceTextField.getText())/100.0;
			double presion = Double.parseDouble(presionTextField.getText());
			
			if(numVuelosProv != numVuelos)
				modificaVuelos();
			
			if(numPistasProv != numPistas)
				modificaTEstimados();
			
			mut.setProb(probMutacion);
			cruz.setProb(probCruce);
			
			Poblacion poblacion = new Poblacion(size, probElite, function, cruz, mut, select, presion, vuelos);
			frame.setPoblacion(poblacion);
			frame.run(numGeneraciones);
		
        });
    }

	private void modificaVuelos() {
		int diff = numVuelosProv - numVuelos;
		
		if(diff < 0) {
			int j = numVuelos;
			
			for(int i = 0; i < Math.abs(diff); i++)
				vuelos.remove(j - (i + 1));
		}
		else {
			for(int i = 0; i < diff; i++) {
				List<Double> tEstimados = new ArrayList<>();
				for(int j = 0; j < numPistasProv; j++)
					tEstimados.add((double) MyRandom.getInstance().nextInt(24));
				vuelos.add(new Vuelo(RandomGenerator.generateRandomString(), MyRandom.getSizeVuelo(), tEstimados, matrizSEP));
			}
		}
		
		numVuelos = numVuelosProv;
	}
	
	private void modificaTEstimados() {
		int diff = numPistasProv - numPistas;
		
		for(int i = 0; i < numVuelosProv; i++) {
			if(diff < 0) {
				
				for(int m = 0; m < Math.abs(diff); m++)
					vuelos.get(i).removeTiempoEstimado();
			}
			else {
				for(int m = 0; m < diff; m++)
					vuelos.get(i).addTiempoEstimado((double) MyRandom.getInstance().nextInt(24));
			}
		}
		
		numPistas = numPistasProv;
	}
}
