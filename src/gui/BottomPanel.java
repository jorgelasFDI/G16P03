package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import algorithm.AlgoritmoGenetico;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import algorithm.population.Generaciones;
import auxiliar.tree.LogicalNode;

public class BottomPanel extends JPanel implements Observers {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Individuo mejorIndividuo;
	TitledBorder border;
	JLabel label;
    
    public BottomPanel(JFrame frame, AlgoritmoGenetico alg) {
    	setLayout(new BorderLayout());
    	border = BorderFactory.createTitledBorder(
    			BorderFactory.createLineBorder(Color.black, 2),
    			"Mejor individuo",
    			TitledBorder.LEFT, TitledBorder.TOP);
		setBorder(border);
		alg.addObserver(this);
		label = new JLabel("Profundidad del individuo");
		this.add(label);
    }


	@Override
	public void actualizaVista(Generaciones generaciones, Individuo mejorIndividuo) {
		// TODO Auto-generated method stub
		border.setTitle("Mejor individuo con aptitud: " + (int) (mejorIndividuo.getAptitud() * LogicalNode.combinaciones.size()) + "/" + LogicalNode.combinaciones.size());
		label.setText(String.valueOf(((IndividuoTree) mejorIndividuo).get(0).depth()));
		this.repaint();
		//updateUI();
	}

}
