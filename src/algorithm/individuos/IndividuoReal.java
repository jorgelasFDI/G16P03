package algorithm.individuos;

import algorithm.functions.Function;

public class IndividuoReal extends Individuo<Double, Object, Double> {

    public IndividuoReal(IndividuoReal individuo) {
		super(individuo);
	}

	public IndividuoReal(Function function) {
		super(function);
	}

    @Override
    public Iterable<Double> copyGenes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Individuo<Double, Object, Double> copy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void swapGen(int idx, int j, Individuo<Double, Object, Double> other) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Double get(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void set(int i, Double other) {
        // TODO Auto-generated method stub
        
    }
    
}
