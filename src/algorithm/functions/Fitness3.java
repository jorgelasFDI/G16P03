package algorithm.functions;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;

public class Fitness3 extends Function {

	public Fitness3() {
        super(false, "function binary 3");
    }

	@Override
	public double fitnessInstance(Individuo cromosoma) {
		IndividuoBinary individuoBinary = (IndividuoBinary) cromosoma;
		double gen1 = individuoBinary.getGen(0).getFenotipo();
        double gen2 = individuoBinary.getGen(1).getFenotipo();
		
		return -(gen2 + 47)*Math.sin(Math.sqrt(Math.abs(gen2 + gen1/2 + 47)))
				- gen1*Math.sin(Math.sqrt(Math.abs(gen1 - (gen2 + 47))));
	}

	@Override
	public double bloatingInstance(Individuo individuo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
