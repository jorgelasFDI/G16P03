package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.javatuples.Pair;

import algorithm.functions.*;
import algorithm.operations.Operation;
import algorithm.population.Poblacion;

public class LeftPanel2 implements View {

    private JComboBox<Operation> seleccionComboBox;
    private JComboBox<Operation> mutacionComboBox;
    private JComboBox<Operation> cruceComboBox;
    private JComboBox<Function> functionComboBox;

	private JTextField mutacionTextField = new JTextField("50");
	private JTextField cruceTextField = new JTextField("70");
	private JTextField presionTextField = new JTextField("1.5");

    public LeftPanel2(JComboBox<Operation> seleccionComboBox, JComboBox<Operation> mutacionComboBox,
            JComboBox<Operation> cruceComboBox, JComboBox<Function> functionComboBox) {
        this.seleccionComboBox = seleccionComboBox;
        this.cruceComboBox = cruceComboBox;
        this.mutacionComboBox = mutacionComboBox;
        this.functionComboBox = functionComboBox;
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
				new Pair<>("Probabilidad", mutacionTextField)
			), null),
            new Pair<>(Arrays.asList(
				new Pair<>("Cruces", cruceComboBox),
				new Pair<>("Probabilidad", cruceTextField)
			), null)
        );
    }

	@Override
	public Poblacion getPoblacion() {
		return null;
	}
}
