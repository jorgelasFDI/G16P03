package auxiliar.binary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryList implements Iterable<BinaryGen> {

    private List<BinaryGen> genes;
    private int size;
    private int numBits;

    public BinaryList(double tolerancia, List<GenRange> ranges) {
        genes = new ArrayList<>();
        numBits = 0;
        for (GenRange range: ranges) {
            BinaryGen gen = new BinaryGen(range, numBits, tolerancia);
            genes.add(gen);
            numBits += gen.getSize();
        } size = ranges.size();
    }

    public BinaryList(BinaryList genes2) {
        size = genes2.getSize();
        numBits = genes2.getNumBits();
        genes = new ArrayList<>(genes2.getSize());
        for (int i = 0; i < genes2.getSize(); i++) {
            genes.add(new BinaryGen(genes2.get(i)));
        }
    }

    public int getNumBits() {
        return size;
    }

    public int getSize() {
        return size;
    }

    public void add(BinaryGen gen) {
        genes.add(gen);
    }

    public void set(int i, BinaryGen gen) {
        genes.set(i, gen);
    }

    public BinaryGen get(int i) {
        return genes.get(i);
    }

    @Override
    public Iterator<BinaryGen> iterator() {
        return genes.iterator();
    }

    public Iterator<Boolean> bitsIterator() {
        return new Iterator<Boolean>() {
            private Iterator<BinaryGen> genIter = genes.iterator();
            private Iterator<Boolean> iter;

            @Override
            public boolean hasNext() {
                return iter.hasNext() || genIter.hasNext();
            }

            @Override
            public Boolean next() {
                if (iter == null || !iter.hasNext()) iter = genIter.next().iterator();
                return iter.next();
            }
            
        };
    }

    public Boolean getBit(int i) {
        for (BinaryGen gen: genes) {
			Boolean val = gen.getBit(i);
			if (val != null) return val;
		} return null;
    }

    public void setBit(int i, Boolean other) {
        for (BinaryGen gen: genes) gen.setBit(i, other);
    }

    public void flip(int idx) {
        for (BinaryGen gen: genes) gen.flipBit(idx);
    }
    
}
