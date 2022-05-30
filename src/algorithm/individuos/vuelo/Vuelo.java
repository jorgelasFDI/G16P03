package algorithm.individuos.vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vuelo {

    private String idVuelo;
    private String tipoAvion;
    private List<Double> tiemposEstimados;
    private static int numPistas;
    private double minTEL = Double.POSITIVE_INFINITY;
    private double TLA = Double.POSITIVE_INFINITY;
    private int pistaAsignada = -1;
    private static Map<String, Map<String, Double>> matrizSEP;

    public Vuelo(String idVuelo, String tipoAvion, List<Double> tiemposEstimados, Map<String, Map<String, Double>> matrizSEP) {
		this.idVuelo = idVuelo;
		this.tipoAvion = tipoAvion;
		this.tiemposEstimados = tiemposEstimados;
        Vuelo.matrizSEP = matrizSEP;
        Vuelo.numPistas = tiemposEstimados.size();
        for (double tiempo: tiemposEstimados)
            this.minTEL = Math.min(minTEL, tiempo);
	}

    public Vuelo(Vuelo vuelo) {
        this.idVuelo = vuelo.getIdVuelo();
        this.tipoAvion = vuelo.getTipoAvion();
        this.tiemposEstimados = vuelo.getTiemposEstimados();
        this.TLA = vuelo.getTLA();
        this.pistaAsignada = vuelo.getPista();
        for (double tiempo: tiemposEstimados)
            this.minTEL = Math.min(minTEL, tiempo);
    }

    public void setMinTEL(double minTEL) {
        this.minTEL = minTEL;
    }

    public double getMinTEL() {
        return minTEL;
    }

    public String getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(String idVuelo) {
		this.idVuelo = idVuelo;
	}

	public String getTipoAvion() {
		return tipoAvion;
	}

	public void setTipoAvion(String tipoAvion) {
		this.tipoAvion = tipoAvion;
	}

	public List<Double> getTiemposEstimados() {
		return new ArrayList<>(tiemposEstimados);
	}

	public void setTiemposEstimados(List<Double> tiemposEstimados) {
		this.tiemposEstimados = tiemposEstimados;
	}

    public double getMinRetraso() {
        return TLA - minTEL;
    }

    public double getRetraso() {
        return TLA - tiemposEstimados.get(pistaAsignada);
    }

    public static int getNumPistas() {
        return numPistas;
    }
    
    public static void setNumPistas(int numPistas) {
    	Vuelo.numPistas = numPistas;
    }

    public int getPista() {
        return pistaAsignada;
    }

    public double calculaTLA(List<Double> anteriorTLA, List<String> anteriorTipoAvion) {
		
        TLA = Double.POSITIVE_INFINITY;
        for (int i = 0; i < tiemposEstimados.size(); i++) {       //Para todas las pistas
            double tiempo = tiemposEstimados.get(i);
            double parcial = Math.max(anteriorTLA.get(i) + separacion(tipoAvion, anteriorTipoAvion.get(i)), tiempo);

            if (TLA > parcial) {
                TLA = parcial;
                pistaAsignada = i;
            }

        } return TLA;
	}
    
    public double separacion(String gen, String anterior) {
		return matrizSEP.get(anterior).get(gen);
	}

	public double getTLA() {
		// TODO Auto-generated method stub
		return TLA;
	}
	
	public void addTiempoEstimado(double idx) {
		tiemposEstimados.add(idx);
		Vuelo.setNumPistas(tiemposEstimados.size());
	}
	
	public void removeTiempoEstimado() {
		tiemposEstimados.remove(tiemposEstimados.size() - 1);
		Vuelo.setNumPistas(tiemposEstimados.size());
	}

}
