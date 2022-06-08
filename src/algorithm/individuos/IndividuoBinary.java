package algorithm.individuos;

import java.util.List;

import algorithm.functions.Function;
import algorithm.population.Poblacion;
import auxiliar.binary.BinaryList;
import auxiliar.binary.BinaryGen;
import auxiliar.binary.GenRange;

public class IndividuoBinary extends Individuo<Boolean, GenRange, BinaryGen> {

    private BinaryList genes;

    public IndividuoBinary(IndividuoBinary individuoBinary) {
        super(individuoBinary);
        genes = (BinaryList) iterable;
    }

    public IndividuoBinary(Function function, Poblacion poblacion) {
        super(function, poblacion);
    }

    public void init(double tolerancia, List<GenRange> ranges) {
        genes = new BinaryList(tolerancia, ranges);
        iterable = genes;
        size = genes.getNumBits();
    }   

    @Override
    public Iterable<BinaryGen> copyGenes() {
        return new BinaryList(genes);
    }

    @Override
    public Individuo<Boolean, GenRange, BinaryGen> copy() {
        return new IndividuoBinary(this);
    }

    @Override
    public void swapGen(int idx, int j, Individuo<Boolean, GenRange, BinaryGen> other) {
        IndividuoBinary otherBin = (IndividuoBinary) other;
        Boolean aux = otherBin.getBit(j);
		otherBin.setBit(j, getBit(idx));
		otherBin.setBit(idx, aux);
    }

    public int getNumGenes() {
        return genes.getSize();
    }

    @Override
    public BinaryGen get(int i) {
        return genes.get(i);
    }

    @Override
    public void set(int i, BinaryGen other) {
        genes.set(i, other);
    }

    public void flipBit(int idx) {
        genes.flipBit(idx);
	}

    public Boolean getBit(int i) {
        return genes.getBit(i);
    }

    public void setBit(int i, Boolean other) {
        genes.setBit(i, other);
    }
    
}
