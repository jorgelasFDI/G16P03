package algorithm.population;

public class Generaciones {

	private double[] mejorAptitud;
	private double[] mediaAptitud;
	private double[] mejorAbsoluto;
	private int ind;
	
	public Generaciones(int numGeneraciones) {
		ind = 0;
		mejorAptitud = new double[numGeneraciones];
		mediaAptitud = new double[numGeneraciones];
		mejorAbsoluto = new double[numGeneraciones];
	}
	
	public double[] getMejorAptitud() {
		return mejorAptitud;
	}
	
	public double[] getMediaAptitud() {
		return mediaAptitud;
	}

	public double[] getMejorAbsoluto() {
		return mejorAbsoluto;
	}
	
	public double getMediaFinal() {
		return mediaAptitud[mediaAptitud.length - 1];
	}

	public void addGeneracion(Generacion g) {
		mejorAptitud[ind] = g.getMejorAptitud();
		mediaAptitud[ind] = g.getMediaAptitud();
		mejorAbsoluto[ind] = g.getMejorAbsoluto();
		ind++;
	}
}
