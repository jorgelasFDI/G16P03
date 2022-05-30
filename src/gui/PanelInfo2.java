package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.javatuples.Pair;

import algorithm.AlgoritmoGenetico;
import algorithm.functions.*;
import algorithm.individuos.Individuo;
import auxiliar.vuelo.Vuelo;
import algorithm.operations.Operation;
import algorithm.population.Generaciones;
import algorithm.population.GeneratePoblacion;
import algorithm.population.Poblacion;
import auxiliar.MyGui;
import auxiliar.vuelo.RandomGenerator;

public class PanelInfo2 implements View {

    private JComboBox<Operation> seleccionComboBox;
    private JComboBox<Operation> mutacionComboBox;
    private JComboBox<Operation> cruceComboBox;
    private JComboBox<Function> functionComboBox;
	private String type;

	private JTextField mutacionTextField = new JTextField("50");
	private JTextField cruceTextField = new JTextField("70");
	private JTextField presionTextField = new JTextField("1.5");

    public PanelInfo2(JComboBox<Operation> seleccionComboBox, JComboBox<Operation> mutacionComboBox,
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

		/*	"W"	"G"	"P"
		""  0.0	0.0	0.0
		"W"	1.0	1.5	2.0
		"G"	1.0	1.5	1.5
		"P"	1.0	1.0	1.0
		*/
		Map<String, Map<String, Double>> matrizSEP = new HashMap<>() {{
			put("", new HashMap<String, Double>() {{
				put("W", 0.0); put("G", 0.0); put("P", 0.0);
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

		List<Vuelo> vuelos = new ArrayList<>();

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

		Function function = (Function) functionComboBox.getSelectedItem();
		Poblacion poblacion = new Poblacion(size, eliteSize, (Operation) cruceComboBox.getSelectedItem(), (Operation) mutacionComboBox.getSelectedItem(), (Operation) seleccionComboBox.getSelectedItem(), null);
		poblacion.generaPoblacion((new GeneratePoblacion()).generaPoblacion(type, null, size, null, null, vuelos, function, poblacion), function);
		return poblacion;
	}

	@Override
	public JPanel getBottomPanel(Generaciones generaciones, Individuo mejorIndividuo, AlgoritmoGenetico alg) {
		// TODO Auto-generated method stub
		if (mejorIndividuo == null) return new JPanel();

		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		TitledBorder border = BorderFactory.createTitledBorder(
    			BorderFactory.createLineBorder(Color.black, 2),
    			"Mejor individuo",
    			TitledBorder.LEFT, TitledBorder.TOP);
		panel.setBorder(border);
		// TODO complete
		BestIndTableModel bm = new BestIndTableModel(mejorIndividuo);
		JTable bt = new JTable(bm);
		//bt.setMinimumSize(new Dimension(200, 100));
		JScrollPane scroll = new JScrollPane(bt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel.add(scroll);
		
		return panel;
	}
}
