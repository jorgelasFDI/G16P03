package main;

import algorithm.AlgoritmoGenetico;
import gui.MainFrame;

public class Main {

	private static AlgoritmoGenetico aGenetico = new AlgoritmoGenetico();
	private static MainFrame mainPanel = new MainFrame(aGenetico);

	public static void main(String[] args) {

		mainPanel.initGUI();
	}
}
