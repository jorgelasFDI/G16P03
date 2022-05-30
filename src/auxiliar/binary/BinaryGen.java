package auxiliar.binary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import auxiliar.MyRandom;

public class BinaryGen implements Iterable<Boolean> {

    // EVERYTHING ELSE
    protected List<Boolean> alelo;
    protected GenRange range;
    protected int start;
    protected int size;

    // STUFF FOR THE FACTORY
    public BinaryGen(GenRange range, int start, double tolerancia) {
        this.range = range;
        this.start = start;
        int size = (int) (Math.log(1 + (range.getDiff())/tolerancia)/Math.log(2));
        this.size = size;
        alelo = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            alelo.add(MyRandom.getInstance().nextBoolean());
    }

    public BinaryGen(BinaryGen gen) {
        this.range = gen.getRange();
        this.alelo = gen.getAlelo();
        this.start = gen.getStart();
        this.size = gen.getSize();
    }

    private int getStart() {
        return start;
    }

    public GenRange getRange() {
        return new GenRange(range);
    }

    public List<Boolean> getAlelo() {
        List<Boolean> returnval = new ArrayList<>(alelo.size());
        for (Boolean bit: alelo) returnval.add(bit);
        return returnval;
    }

    public Boolean getBit(int idx) {
        if (start <= idx && idx < start + size)
            return alelo.get(idx - start);
        return null;
    }

    public void setBit(int idx, Boolean value) {
        if (start <= idx && idx < start + size)
            alelo.set(idx - start, value);
    }

    public void flipBit(int idx) {
        if (start <= idx && idx < start + size)
            alelo.set(idx - start, !alelo.get(idx - start));
    }
    
    public double getFenotipo() {
        return range.getMin() + Binary.binToInt(alelo) * range.getDiff()/(Math.pow(2, getSize()) - 1);
    }
    
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return alelo.iterator();
    }
}
