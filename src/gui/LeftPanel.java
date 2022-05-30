package gui;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.javatuples.Pair;

import algorithm.operations.cruces.*;
import algorithm.operations.mutaciones.*;
import algorithm.functions.Fitness1;
import algorithm.functions.Fitness2;
import algorithm.functions.Fitness3;
import algorithm.functions.Fitness4;
import algorithm.functions.FitnessVuelos1;
import algorithm.functions.FitnessVuelos2;
import algorithm.functions.Function;
import algorithm.functions.FitnessTree;
import algorithm.operations.Operation;
import auxiliar.MyGui;
import algorithm.operations.selection.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

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

	private List<Operation> selecciones = new ArrayList<>();

	// MUTACIONES
	private JComboBox<Operation> mutacionComboBox = new JComboBox<>();

	private List<Operation> mutacionesGenericas = Arrays.asList();

	private List<Operation> mutaciones = new ArrayList<>();

	// CRUCES
	private JComboBox<Operation> cruceComboBox = new JComboBox<>();

	private List<Operation> crucesGenericos = Arrays.asList();

	private List<Operation> cruces = new ArrayList<>();

	// FUNCIONES

	private JComboBox<Function> functionComboBox = new JComboBox<>();

	private List<Function> funcionesGenericas = Arrays.asList();

	private List<Function> funciones = new ArrayList<>();

	// EVERYTHING ELSE
	private JButton enter = new JButton("Ejecutar");

	public LeftPanel(MainFrame mainFrame) {
    	this.frame = mainFrame;
		typeComboBox.addActionListener(e -> selectType((String) typeComboBox.getSelectedItem()));
		enter.addActionListener(e -> startAlg());
		selectType("Binary");
	}

	////////////////////////////////////////////////////////////////////////////////

	private List<String> tipos = Arrays.asList("Binary", "Completo", "RampedAndHalf", "Creciente", "Real", "Vuelo");
	private JComboBox<String> typeComboBox = new JComboBox<>(tipos.toArray(new String[0]));
	private int globalMargin = 20;
	private View view;

	///////////////////////////////////////////////////////////////////////

	private void treeType() {
		selecciones.addAll(Arrays.asList());
		mutaciones.addAll(Arrays.asList(
			new MutacionSubArbol(),	
			new MutacionTerminal(),
			new MutacionHoist(),
			new MutacionFunctional(),
			new MutacionTerminalFuncional()
		));
		cruces.addAll(Arrays.asList(
			new CruceIntercambioArboles()
		));
		funciones.addAll(Arrays.asList(
			new FitnessTree()
		));
	}

	private void realType() {
		selecciones.addAll(Arrays.asList());
		mutaciones.addAll(Arrays.asList(
			new MutacionUniforme()
		));
		cruces.addAll(Arrays.asList(
			new CruceMonopunto(),
			new CruceUniforme(),
			new CruceAritmetico(),
			new CruceBLX()
		));
		funciones.addAll(Arrays.asList(
			new Fitness1(),
			new Fitness2(),
			new Fitness3(),
			new Fitness4()
		));
	}

	private void vueloType() {
		selecciones.addAll(Arrays.asList());
		mutaciones.addAll(Arrays.asList(
			new MutacionIntercambio(),
			new MutacionHeuristica(),
			new MutacionInsercion(),
			new MutacionInversion()
		));
		cruces.addAll(Arrays.asList(
			new CruceCiclosCX(),
			new CruceCO(),
			new CruceOrdenOX(),
			new CruceOrdenPrioritario(),
			new CrucePMX(),
			new CrucePosicionPrioritaria()
		));
		funciones.addAll(Arrays.asList(
			new FitnessVuelos1(),
			new FitnessVuelos2()
		));
	}

	private void binaryType() {
		selecciones.addAll(Arrays.asList());
		mutaciones.addAll(Arrays.asList(
			new MutacionBinaria()
		));
		cruces.addAll(Arrays.asList(
			new CruceMonopunto(),
			new CruceUniforme()
		));
		funciones.addAll(Arrays.asList(
			new Fitness1(),
			new Fitness2(),
			new Fitness3(),
			new Fitness4()
		));
	}

	//////////////////////////////////////////////////////////////////////

	public void selectType(String type) {
		selecciones = new ArrayList<>(seleccionesGenericas);
		mutaciones = new ArrayList<>(mutacionesGenericas);
		cruces = new ArrayList<>(crucesGenericos);
		funciones = new ArrayList<>(funcionesGenericas);
		switch (type) {
			case "RampedAndHalf":
            case "Completo":
            case "Creciente":
				treeType();
				view = new LeftPanel3(seleccionComboBox, mutacionComboBox, cruceComboBox, functionComboBox, type);
				break;
            case "Real":
				realType();
				view = new LeftPanel1(seleccionComboBox, mutacionComboBox, cruceComboBox, functionComboBox, type);
				break;
            case "Binary":
				binaryType();
				view = new LeftPanel1(seleccionComboBox, mutacionComboBox, cruceComboBox, functionComboBox, type);
				break;
            case "Vuelo":
				vueloType(); 
				view = new LeftPanel2(seleccionComboBox, mutacionComboBox, cruceComboBox, functionComboBox, type);
				break;
            default: break;
        }

		seleccionComboBox.setModel(new DefaultComboBoxModel<>(selecciones.toArray(new Operation[0])));
		mutacionComboBox.setModel(new DefaultComboBoxModel<>(mutaciones.toArray(new Operation[0])));
		cruceComboBox.setModel(new DefaultComboBoxModel<>(cruces.toArray(new Operation[0])));
		functionComboBox.setModel(new DefaultComboBoxModel<>(funciones.toArray(new Function[0])));
		removeAll();
		initGUI();
	}

	private JTextField sizeTextField = new JTextField("100");
    private JTextField generacionesTextField = new JTextField("200");
    private JTextField eliteTextField = new JTextField("0");

    private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		MyGui.addComponentPanel(Arrays.asList(
			new Pair<>("Tipo", typeComboBox),
			new Pair<>("Tamaño", sizeTextField),
			new Pair<>("Numero de Generaciones", generacionesTextField),
			new Pair<>("% Elite", eliteTextField)
		), 0, null, this);
		MyGui.addAllPanels(view.getPanelList(), 0, globalMargin, this);
		add(enter);
    }

	public void startAlg() {
		frame.setPoblacion(view.getPoblacion(Integer.parseInt(sizeTextField.getText()), Double.parseDouble(eliteTextField.getText())));
		frame.run(Integer.parseInt(generacionesTextField.getText()));
	}
}
