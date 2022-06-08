package auxiliar.binary;

import auxiliar.MyRandom;

public class RealGen extends Gen<Double> {

    // EVERYTHING ELSE
    protected double alelo;
    protected GenRange range;

    // STUFF FOR THE FACTORY
    public RealGen(double value, GenRange range) {
        alelo = value;
        this.range = range;
    }

    public RealGen(GenRange range) {
        alelo = MyRandom.getInstance().nextDouble()*(range.getMax() - range.getMin()) + range.getMin();
        this.range = range;
    }

    public RealGen(RealGen gen) {
        this.alelo = gen.getFenotipo();
        this.range = gen.range;
    }

    @Override
    public GenRange getRange() {
        return new GenRange(range);
    }
    
    @Override
    public double getFenotipo() {
        return alelo;
    }
    
}
