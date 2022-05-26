package algorithm.functions;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import algorithm.population.Poblacion;


public class Fitness4 extends Function {

	public Fitness4() {
        super(true, "function real");
    }

	@Override
	public double fitnessInstance(Individuo cromosoma, Poblacion poblacion) {
		double total = 0.0;
		IndividuoBinary individuoBinary = (IndividuoBinary) cromosoma;
		for(int i = 0; i < individuoBinary.getNumGenes(); i++) {
			total += Math.sin(individuoBinary.getGen(i).getFenotipo()) * Math.pow(Math.sin(((i + 2)
					* Math.pow(individuoBinary.getGen(i).getFenotipo(), 2))/Math.PI), 20);
		}
		
		return -total;
	}

	@Override
	public double bloatingInstance(Individuo individuo, Poblacion poblacion) {
		// TODO Auto-generated method stub
		return 0;
	}

}
