package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import algorithm.AlgoritmoGenetico;
import algorithm.functions.*;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import auxiliar.binary.GenRange;
import algorithm.operations.Operation;
import algorithm.population.Generaciones;
import algorithm.population.GeneratePoblacion;
import algorithm.population.Poblacion;
import auxiliar.MyGui;

public class PanelInfo1 implements View {

	protected JComboBox<Operation> seleccionComboBox;
    protected JComboBox<Operation> mutacionComboBox;
    protected JComboBox<Operation> cruceComboBox;
    protected JComboBox<Function> functionComboBox;
    protected String type;
    protected Double presion;

    protected JPanel rangesPanelWrap = new JPanel();
    protected JPanel rangesPanel = new JPanel();
    protected JSpinner numGenesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
    protected List<Pair<JSpinner, JSpinner>> rangesSpinners = new ArrayList<Pair<JSpinner,JSpinner>>();

    protected JTextField mutacionTextField = new JTextField("5");
	protected JTextField cruceTextField = new JTextField("60");
	protected JTextField presionTextField = new JTextField("1.5");
	protected JTextField toleranciaTextField = new JTextField("0.005");
	protected Integer numGenes = null;

    public PanelInfo1(JComboBox<Operation> seleccionComboBox, JComboBox<Operation> mutacionComboBox,
            JComboBox<Operation> cruceComboBox, JComboBox<Function> functionComboBox, String type) {
		functionComboBox.addActionListener(e -> setRanges());
		numGenesSpinner.addChangeListener(e -> updateNumGenes((int) numGenesSpinner.getValue()));
        this.seleccionComboBox = seleccionComboBox;
        this.cruceComboBox = cruceComboBox;
        this.mutacionComboBox = mutacionComboBox;
        this.functionComboBox = functionComboBox;
		this.type = type;

		rangesPanelWrap.setLayout(new BoxLayout(rangesPanelWrap, BoxLayout.Y_AXIS));
		rangesPanel.setLayout(new BoxLayout(rangesPanel, BoxLayout.Y_AXIS));
    }

	protected void addRanges(int start, int end) {
		JPanel horizontalPanel;
		SpinnerNumberModel spinner1;
		SpinnerNumberModel spinner2;
		Function function = (Function) functionComboBox.getSelectedItem();
		List<GenRange> ranges = function.getRanges();
		for (int i = start; i < end; i++) {
			int idx = i;
			if (function.getNumVars() == null) idx = 0;
			spinner1 = new SpinnerNumberModel(ranges.get(idx).getMin(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.1);
			spinner2 = new SpinnerNumberModel(ranges.get(idx).getMax(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.1);
			
			horizontalPanel = new JPanel();
			MyGui.addLabel("Gen " + (i + 1), horizontalPanel);
			rangesSpinners.add(new Pair<JSpinner,JSpinner>(new JSpinner(spinner1), new JSpinner(spinner2)));
			horizontalPanel.add(rangesSpinners.get(i).getValue0());
			horizontalPanel.add(rangesSpinners.get(i).getValue1());
			rangesPanel.add(horizontalPanel);
		}
	}

	private void updateNumGenes(int n) {
		
		if (n - numGenes > 0) {
			addRanges(numGenes, n);
		} else for (int i = rangesSpinners.size() - 1; i >= n; i--) {
			rangesSpinners.remove(rangesSpinners.size() - 1);
			rangesPanel.remove(i);
		} numGenes = n;
		rangesPanelWrap.updateUI();
	}
    
    private void setRanges() {

		Function function = (Function) functionComboBox.getSelectedItem();
		if (function.getRanges() == null) return;

		rangesSpinners.clear();
		rangesPanel.removeAll();
		rangesPanelWrap.removeAll();
		numGenesSpinner.setValue(1);

		if (function.getNumVars() == null) {
			MyGui.addLabel("Number of Genes", rangesPanelWrap);
			rangesPanelWrap.add(numGenesSpinner);
			numGenes = 1;
		} else numGenes = function.getNumVars();

		addRanges(0, numGenes);

		rangesPanelWrap.add(rangesPanel);
		rangesPanelWrap.updateUI();
    }

    @Override
    public List<Pair<List<Pair<String, JComponent>>, JPanel>> getPanelList() {
        setRanges();
        return Arrays.asList(
            new Pair<>(Arrays.asList(
				new Pair<>("Funcion", functionComboBox),
				new Pair<>("Tolerancia", toleranciaTextField),
				new Pair<>("", rangesPanelWrap)
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
		List<GenRange> ranges = new ArrayList<>();
		double tolerancia = Double.parseDouble(toleranciaTextField.getText());
		for (Pair<JSpinner, JSpinner> rangePair: rangesSpinners) {
			GenRange range = new GenRange((Double) rangePair.getValue0().getValue(), (Double) rangePair.getValue1().getValue());
			ranges.add(range);
		} Function function = (Function) functionComboBox.getSelectedItem();
		Operation cruce = (Operation) cruceComboBox.getSelectedItem();
		Operation mutacion = (Operation) mutacionComboBox.getSelectedItem();
		Operation seleccion = (Operation) seleccionComboBox.getSelectedItem();
		cruce.setProb(Double.parseDouble(cruceTextField.getText())/100.0);
		mutacion.setProb(Double.parseDouble(mutacionTextField.getText())/100.0);
		presion = Double.parseDouble(presionTextField.getText());
		if (presion < 1) presion = null;
		Poblacion poblacion = new Poblacion(size, eliteSize, cruce, mutacion, seleccion, presion);
		poblacion.generaPoblacion((new GeneratePoblacion()).generaPoblacion(type, null, size, tolerancia, ranges, null, function, poblacion), function);
		return poblacion;
	}

	@Override
	public JPanel getBottomPanel(Generaciones generaciones, Individuo mejorIndividuo, AlgoritmoGenetico alg) {
		JPanel verticalPanel = new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
		
		
		if(mejorIndividuo != null) {
			MyGui.addLabel("Solucion: " + mejorIndividuo.getAptitud(), verticalPanel);
			MyGui.addLabel("Media aptitud final: " + generaciones.getMediaFinal(), verticalPanel);
			String variables = "Variables: ";
			IndividuoBinary bin = (IndividuoBinary) mejorIndividuo;
			
			for(int i = 0; i < bin.getNumGenes(); i++)
				variables += "Variable X" + (i + 1) + ": " + bin.get(i).getFenotipo() + "  ";
			
			MyGui.addLabel(variables, verticalPanel);
		}

		return verticalPanel;
	}
    

}
