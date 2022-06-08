package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import algorithm.AlgoritmoGenetico;
import algorithm.population.Poblacion;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainFrame extends JFrame {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel bottomPanel;

	private int width;
	private int height;
	private AlgoritmoGenetico alg;

    public MainFrame(AlgoritmoGenetico alg) {
		this.alg = alg;
        width = 1000;
		height = 700;
		mainPanel = new CenterPanel(this, alg);
		bottomPanel = new BottomPanel(this, alg);
		leftPanel = new LeftPanel(this);
		bottomPanel.setPreferredSize(new Dimension(500, 100));
    }
    
    public void initGUI() {
		setTitle("Evolutiva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JScrollPane leftScroll = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftScroll.getVerticalScrollBar().setUnitIncrement(16);
		add(mainPanel, BorderLayout.CENTER);
		add(leftScroll, BorderLayout.WEST);
		add(bottomPanel, BorderLayout.SOUTH);
		pack();
		setSize(width, height);
		setVisible(true);
    }

    public void setPoblacion(Poblacion poblacion) {
        alg.setPoblacion(poblacion);
    }

    public void run(int numGeneraciones) {
        alg.run(numGeneraciones);
    }

	public void notifyViewChange(View view) {
        alg.notifyViewChange(view);
    }
}
