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

import algorithm.functions.*;
import algorithm.individuos.gen.GenRange;
import algorithm.operations.Operation;
import algorithm.population.GeneratePoblacion;
import algorithm.population.Poblacion;
import auxiliar.MyGui;

public class LeftPanel1 implements View {

    private JComboBox<Operation> seleccionComboBox;
    private JComboBox<Operation> mutacionComboBox;
    private JComboBox<Operation> cruceComboBox;
    private JComboBox<Function> functionComboBox;
	private String type;

    private JPanel rangesPanelWrap = new JPanel();
	private JPanel rangesPanel = new JPanel();
	private JSpinner numGenesSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
	private List<Pair<JSpinner, JSpinner>> rangesSpinners = new ArrayList<Pair<JSpinner,JSpinner>>();

	private JTextField mutacionTextField = new JTextField("50");
	private JTextField cruceTextField = new JTextField("70");
	private JTextField presionTextField = new JTextField("1.5");
	private Integer numGenes = null;

    public LeftPanel1(JComboBox<Operation> seleccionComboBox, JComboBox<Operation> mutacionComboBox,
            JComboBox<Operation> cruceComboBox, JComboBox<Function> functionComboBox, String type) {
        functionComboBox.addActionListener(e -> numGenes = ((Function) functionComboBox.getSelectedItem()).getNumVars());
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

	private void addRanges(int start, int end) {
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
    }

    @Override
    public List<Pair<List<Pair<String, JComponent>>, JPanel>> getPanelList() {
        setRanges();
        return Arrays.asList(
            new Pair<>(Arrays.asList(
				new Pair<>("Funcion", functionComboBox),
				new Pair<>("", rangesPanelWrap)
			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Selecciones", seleccionComboBox),
				new Pair<>("Maxima presion selectiva", presionTextField)
			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Mutaciones", mutacionComboBox),
				new Pair<>("Probabilidad", mutacionTextField)
			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Cruces", cruceComboBox),
				new Pair<>("Probabilidad", cruceTextField)
			), null)
        );
    }

	@Override
	public Poblacion getPoblacion(int size, double eliteSize) {
		List<GenRange> ranges = new ArrayList<>();
		for (Pair<JSpinner, JSpinner> rangePair: rangesSpinners) {
			GenRange range = new GenRange((Double) rangePair.getValue0().getValue(), (Double) rangePair.getValue1().getValue());
			ranges.add(range);
		} Function function = (Function) functionComboBox.getSelectedItem();
		Poblacion poblacion = new Poblacion(size, eliteSize, (Operation) cruceComboBox.getSelectedItem(), (Operation) mutacionComboBox.getSelectedItem(), (Operation) seleccionComboBox.getSelectedItem(), null);
		poblacion.generaPoblacion((new GeneratePoblacion()).generaPoblacion(type, null, size, 0.1, ranges, null, function, poblacion), function);
		return poblacion;
	}
    

}
