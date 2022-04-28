package algorithm;

import java.util.ArrayList;
import java.util.List;

import algorithm.population.Generacion;
import algorithm.population.Generaciones;
import algorithm.population.Poblacion;
import gui.Observers;

public class AlgoritmoGenetico {

    private Poblacion poblacion;
    private Generaciones generaciones;
    private int generacionActual;
    private List<Observers> observadores = new ArrayList<Observers>();
    
    public void addObserver(Observers o) {
    	observadores.add(o);
    }

    public void setPoblacion(Poblacion poblacion) {
        this.poblacion = poblacion;
    }

    public void run(int numGeneraciones) {
        generaciones = new Generaciones(numGeneraciones);
        generacionActual = 0;
        
        //poblacion.print();
        	
        generaciones.addGeneracion(new Generacion(poblacion.get(0).getAptitud(), poblacion.getMediaAptitud(), poblacion.getMejorAbsoluto()));
        while (generacionActual < numGeneraciones - 1) {
            // Generar Elite
            /*poblacion.generarElite();   //Poblacion ya ordenada por aptitud, sacar solo los n primeros
            // Seleccion
            poblacion.seleccionar();
            // Cruce
            poblacion.cruzar();
            // Mutacion
            poblacion.mutar();
            // Introducir Elite
            poblacion.introducirElite();
            // Evaluacion;      
            poblacion.evalua();
            
            generaciones.addGeneracion(new Generacion(poblacion.get(0).getAptitud(), poblacion.getMediaAptitud(), poblacion.getMejorAbsoluto()));
            //Siguiente generacion*/
            generacionActual++;
            //System.out.println(generacionActual);
        }
                
        for(Observers o: observadores) {
        	o.actualizaVista(generaciones, poblacion.getMejorIndividuo());
        }
        
    }
}
