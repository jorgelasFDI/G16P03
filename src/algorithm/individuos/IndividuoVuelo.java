package algorithm.individuos;

import java.util.ArrayList;
import java.util.List;

import algorithm.functions.Function;
import algorithm.population.Poblacion;
import auxiliar.MyRandom;
import auxiliar.vuelo.Vuelo;

public class IndividuoVuelo extends Individuo<Integer, Vuelo, Integer> {

    public IndividuoVuelo(IndividuoVuelo individuoBinary) {
        super(individuoBinary);
    }

    public IndividuoVuelo(Function function, Poblacion poblacion) {
        super(function, poblacion);
    }

    public void init(List<Vuelo> objects) {
        this.size = objects.size();		
		for(int i = 0; i < size; i++) genesToObjects.put(i + 1, new Vuelo(objects.get(i)));
        iterable = new ArrayList<Integer>(size);
		MyRandom.getRandomNoRepeat((List<Integer>)iterable, size, 1, size); // genes vacio -> 0 - 11
    }

    @Override
    public Iterable<Integer> copyGenes() {
        return new ArrayList<>((List<Integer>) iterable);
    }

    @Override
    public Individuo<Integer, Vuelo, Integer> copy() {
        return new IndividuoVuelo(this);
    }

    @Override
    public void swapGen(int idx, int j, Individuo<Integer, Vuelo, Integer> other) {
        int aux = other.get(j);
		other.set(j, get(idx));
		set(idx, aux);
    }

    @Override
    public Integer get(int i) {
        return ((List<Integer>) iterable).get(i);
    }

    @Override
    public void set(int i, Integer other) {
        ((List<Integer>) iterable).set(i, other);
    }
    
}
