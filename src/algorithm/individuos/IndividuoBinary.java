package algorithm.individuos;

import java.util.List;

import algorithm.functions.Function;
import algorithm.individuos.gen.Gen;
import algorithm.individuos.gen.GenList;
import algorithm.individuos.gen.GenRange;
import algorithm.population.Poblacion;

public class IndividuoBinary extends Individuo<Boolean, GenRange, Gen> {

    private GenList genes;

    public IndividuoBinary(IndividuoBinary individuoBinary) {
        super(individuoBinary);
    }

    public IndividuoBinary(Function function, Poblacion poblacion) {
        super(function, poblacion);
    }

    public void init(double tolerancia, List<GenRange> ranges) {
        genes = new GenList(tolerancia, ranges);
        size = genes.getNumBits();
    }   

    @Override
    public Iterable<Gen> copyGenes() {
        return new GenList(genes);
    }

    @Override
    public Individuo<Boolean, GenRange, Gen> copy() {
        return new IndividuoBinary(this);
    }

    @Override
    public void swapGen(int idx, int j, Individuo<Boolean, GenRange, Gen> other) {
        IndividuoBinary otherBin = (IndividuoBinary) other;
        Boolean aux = otherBin.getBit(j);
		otherBin.setBit(j, getBit(idx));
		otherBin.setBit(idx, aux);
    }

    public int getNumGenes() {
        return genes.getSize();
    }

    public void setGen(int idx, Gen value) {
		genes.set(idx, value);
	}

	public Gen getGen(int idx) {
		return genes.get(idx);
	}

    public void flip(int idx) {
        genes.flip(idx);
	}

    @Override
    public Gen get(int i) {
        return genes.get(i);
    }

    @Override
    public void set(int i, Gen other) {
        genes.set(i, other);
    }

    public Boolean getBit(int i) {
        return genes.getBit(i);
    }

    public void setBit(int i, Boolean other) {
        genes.setBit(i, other);
    }
    
}