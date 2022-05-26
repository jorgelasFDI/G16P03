package algorithm.individuos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algorithm.functions.Function;
import algorithm.individuos.gen.GenRange;
import algorithm.population.Poblacion;
import auxiliar.MyRandom;

public class IndividuoReal extends Individuo<Double, GenRange, Double> {

    private List<Double> genes;

    public IndividuoReal(IndividuoReal individuoBinary) {
        super(individuoBinary);
    }

    public IndividuoReal(Function function, Poblacion poblacion) {
        super(function, poblacion);
    }

    public void init(List<GenRange> ranges) {
        size = ranges.size();
        genes = new ArrayList<Double>(size);
        iterable = genes;
        HashMap<Double, GenRange> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            GenRange range = ranges.get(i);
            genes.add(MyRandom.getInstance().nextDouble()*(range.getMax() - range.getMin()) + range.getMin());
            map.put((double) i, range);
		} setGenesToObjects(map);
    }

    @Override
    public Iterable<Double> copyGenes() {
        return new ArrayList<>(genes);
    }

    @Override
    public Individuo<Double, GenRange, Double> copy() {
        return new IndividuoReal(this);
    }

    @Override
    public void swapGen(int idx, int j, Individuo<Double, GenRange, Double> other) {
        Double aux = other.get(j);
		other.set(j, get(idx));
		other.set(idx, aux);
    }

    @Override
    public Double get(int i) {
        return genes.get(i);
    }

    @Override
    public void set(int i, Double other) {
        genes.set(i, other);
    }
}
