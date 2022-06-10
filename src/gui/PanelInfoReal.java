package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.javatuples.Pair;

import algorithm.AlgoritmoGenetico;
import algorithm.functions.Function;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import algorithm.individuos.IndividuoReal;
import algorithm.operations.Operation;
import algorithm.population.Generaciones;
import algorithm.population.GeneratePoblacion;
import algorithm.population.Poblacion;
import auxiliar.MyGui;
import auxiliar.binary.GenRange;

public class PanelInfoReal extends PanelInfo1 {

	public PanelInfoReal(JComboBox<Operation> seleccionComboBox, JComboBox<Operation> mutacionComboBox,
			JComboBox<Operation> cruceComboBox, JComboBox<Function> functionComboBox, String type) {
		super(seleccionComboBox, mutacionComboBox, cruceComboBox, functionComboBox, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public JPanel getBottomPanel(Generaciones generaciones, Individuo mejorIndividuo, AlgoritmoGenetico alg) {
		// TODO Auto-generated method stub
		JPanel verticalPanel = new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
		
		if(mejorIndividuo != null) {
			MyGui.addLabel("Solucion: " + mejorIndividuo.getAptitud(), verticalPanel);
			MyGui.addLabel("Media aptitud final: " + generaciones.getMediaFinal(), verticalPanel);
			String variables = "Variables: ";
			IndividuoReal real = (IndividuoReal) mejorIndividuo;
			
			for(int i = 0; i < real.getSize(); i++)
				variables += "Variable X" + (i + 1) + ": " + real.get(i).getFenotipo() + "  ";
			
			MyGui.addLabel(variables, verticalPanel);
		}

		return verticalPanel;
	}
}
