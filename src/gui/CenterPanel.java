package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import algorithm.AlgoritmoGenetico;
import algorithm.individuos.Individuo;
import algorithm.population.Generaciones;

public class CenterPanel extends JPanel implements Observers{
    
    /**
	 * 
	 */
	
	private Plot2DPanel plot = new Plot2DPanel();
	double[] x = new double[100];
    double[] y = new double[]{1, 2, 3, 4, 5, 6, 7, 8};
	private static final long serialVersionUID = 1L;

	public CenterPanel(JFrame frame, AlgoritmoGenetico alg) {
		alg.addObserver(this);
		plot.addLegend("SOUTH"); 
		
		//plot.addHistogramPlot("Algoritmo gen�tico", x, y);
		
		plot.setVisible(true);
		
		plot.setPreferredSize(new Dimension(700, 500));
		
		add(plot);
    }

	@Override
	public void actualizaView(View view) {

	}

	@Override
	public void actualizaVista(Generaciones generaciones, Individuo mejorIndividuo) {
		// TODO Auto-generated method stub
		plot.removeAllPlots();
		
		int size = generaciones.getMediaAptitud().length;
		double [] ejeX = new double[size];
		
		for(int i = 0; i < size; i++) {
			ejeX[i] = i + 1;
		}
		
		plot.addLegend("SOUTH");
		plot.addLinePlot("Mejor absoluto", Color.BLUE, ejeX, generaciones.getMejorAbsoluto());
		plot.addLinePlot("Mejor Generacion", Color.RED, ejeX, generaciones.getMejorAptitud());
		plot.addLinePlot("Media generacion", Color.GREEN, ejeX, generaciones.getMediaAptitud());
	}

}
