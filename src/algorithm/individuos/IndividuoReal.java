package algorithm.individuos;

import java.util.ArrayList;
import java.util.List;

import algorithm.functions.Function;
import algorithm.population.Poblacion;
import auxiliar.binary.GenRange;
import auxiliar.binary.RealGen;

public class IndividuoReal extends Individuo<Double, GenRange, RealGen> {

    private List<RealGen> genes;

    public IndividuoReal(IndividuoReal individuoBinary) {
        super(individuoBinary);
    }

    public IndividuoReal(Function function, Poblacion poblacion) {
        super(function, poblacion);
    }

    public void init(List<GenRange> ranges) {
        size = ranges.size();
        genes = new ArrayList<RealGen>(size);
        iterable = genes;
        for (int i = 0; i < size; i++) {
            GenRange range = ranges.get(i);
            genes.add(new RealGen(range));
		}
    }

    @Override
    public Iterable<RealGen> copyGenes() {
        List<RealGen> newGenes = new ArrayList<>(size);
        for (RealGen gen: genes) {
            newGenes.add(new RealGen(gen));
        } return newGenes;
    }

    @Override
    public Individuo<Double, GenRange, RealGen> copy() {
        IndividuoReal ind = new IndividuoReal(this);
        ind.genes = (List<RealGen>) ind.iterable;
        return ind;
    }

    @Override
    public void swapGen(int idx, int j, Individuo<Double, GenRange, RealGen> other) {
        RealGen aux = other.get(j);
		other.set(j, get(idx));
		this.set(idx, aux);
    }

    @Override
    public RealGen get(int i) {
        return new RealGen(genes.get(i));
    }

    @Override
    public void set(int i, RealGen other) {
        genes.set(i, other);
    }
}
