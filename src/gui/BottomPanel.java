package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import algorithm.AlgoritmoGenetico;
import algorithm.individuos.Individuo;
import algorithm.population.Generaciones;

public class BottomPanel extends JPanel implements Observers {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Individuo mejorIndividuo;
	TitledBorder border;
    
    public BottomPanel(JFrame frame, AlgoritmoGenetico alg) {
    	setLayout(new BorderLayout());
    	border = BorderFactory.createTitledBorder(
    			BorderFactory.createLineBorder(Color.black, 2),
    			"Mejor individuo",
    			TitledBorder.LEFT, TitledBorder.TOP);
		setBorder(border);
		// TODO complete
		alg.addObserver(this);
		BestIndTableModel bm = new BestIndTableModel(alg);
		JTable bt =new JTable(bm);
		//bt.setMinimumSize(new Dimension(200, 100));
		JScrollPane scroll = new JScrollPane(bt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroll);
        //tablePanel = new JPanel();
		
		//addLabel("Solucion: ", tablePanel);
		//add(tablePanel);
    }


	@Override
	public void actualizaVista(Generaciones generaciones, Individuo mejorIndividuo) {
		// TODO Auto-generated method stub
		border.setTitle("Mejor individuo con aptitud: " + mejorIndividuo.getAptitud());
		this.repaint();
		//updateUI();
	}

}
