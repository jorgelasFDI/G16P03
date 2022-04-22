package algorithm.individuos;

public class GenRange<T> {
    private T min;
    private T max;

    public GenRange(T min, T max) {
        this.min = min;
        this.max = max;
    }
    
    public GenRange(GenRange<T> range) {
        min = range.min;
        max = range.max;
    }

    public T getMin() {
        return min;
    }
    
    public T getMax() {
        return max;
    }
}
