package algorithm.functions;

import java.util.ArrayList;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoBinary;
import auxiliar.binary.GenRange;
import algorithm.population.Poblacion;

public class Fitness1 extends Function {

    public Fitness1() {
        super(false, "function binary 1", 2, new ArrayList<>());
        ranges.add(new GenRange(-3.0, 12.1));
        ranges.add(new GenRange(4.1, 5.8));
    }

    @Override
    public double fitnessInstance(Individuo cromosoma, Poblacion poblacion) {
        // f(x1 , x2) = 21.5 + x1.sen(4π x1)+x2.sen(20π x2)  :
        IndividuoBinary individuoBinary = (IndividuoBinary) cromosoma;
        double gen1 = individuoBinary.getGen(0).getFenotipo();
        double gen2 = individuoBinary.getGen(1).getFenotipo();
        return 21.5 + gen1*Math.sin(4*Math.PI*gen1) + gen2*Math.sin(20*Math.PI*gen2);
    }

    @Override
    public double bloatingInstance(Individuo individuo, Poblacion poblacion) {
        // TODO Auto-generated method stub
        return individuo.getAptitudRevisada();
    }
    
}
