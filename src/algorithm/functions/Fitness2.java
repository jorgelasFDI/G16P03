package algorithm.functions;

import java.util.ArrayList;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import auxiliar.binary.Gen;
import auxiliar.binary.GenRange;
import algorithm.population.Poblacion;

public class Fitness2 extends Function {

	public Fitness2() {
        super(true, "function binary 2", 2, new ArrayList<>());
        ranges.add(new GenRange(-10.0, 10.0));
        ranges.add(new GenRange(-10.0, 10.0));
    }

	@Override
	public double fitnessInstance(Individuo cromosoma, Poblacion poblacion) {
		// TODO Auto-generated method stub
		double gen1 = ((Gen) cromosoma.get(0)).getFenotipo();
        double gen2 = ((Gen) cromosoma.get(1)).getFenotipo();
        
        double total1 = 0.0;
        double total2 = 0.0;
        
        for(int i = 1; i <= 5; i++) 
        	total1 += i * Math.cos((i + 1) * gen1 + i);
       
        for(int i = 1; i <= 5; i++) 
        	total2 += i * Math.cos((i + 1) * gen2 + i);
        
		return total1*total2;
	}

    @Override
    public double bloatingInstance(Individuo individuo, Poblacion poblacion) {
        // TODO Auto-generated method stub
        return individuo.getAptitudRevisada();
    }

}
