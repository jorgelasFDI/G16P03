package algorithm.individuos.gen;

public class GenRange {
    private double min;
    private double max;

    public GenRange(double min, double max) {
        this.min = min;
        this.max = max;
    }
    
    public GenRange(GenRange range) {
        min = range.min;
        max = range.max;
    }

    public double getMin() {
        return min;
    }
    
    public double getMax() {
        return max;
    }
    
    public double getDiff() {
        return max - min;
    }
}
