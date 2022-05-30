package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import algorithm.AlgoritmoGenetico;
import algorithm.individuos.Individuo;
import algorithm.population.Generaciones;

public class BottomPanel extends JPanel implements Observers {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private View view;
    
    public BottomPanel(JFrame frame, AlgoritmoGenetico alg) {
		alg.addObserver(this);
    }

	@Override
	public void actualizaView(View view) {
		this.view = view;
		actualizaVista(null, null);
	}

	@Override
	public void actualizaVista(Generaciones generaciones, Individuo mejorIndividuo) {
		removeAll();
		add(view.getBottomPanel(generaciones, mejorIndividuo));
		updateUI();
	}

}
