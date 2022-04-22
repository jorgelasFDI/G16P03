package algorithm.population;

public class Generacion {

	private double mejorAptitud;
	private double mediaAptitud;
	private double mejorAbsoluto;
	
	public Generacion(double mejorAptitud, double mediaAptitud, double mejorAbsoluto) {
		super();
		this.mejorAptitud = mejorAptitud;
		this.mediaAptitud = mediaAptitud;
		this.mejorAbsoluto = mejorAbsoluto;
	}

	public double getMejorAptitud() {
		return mejorAptitud;
	}

	public double getMediaAptitud() {
		return mediaAptitud;
	}
	
	public double getMejorAbsoluto() {
		return mejorAbsoluto;
	}

}
