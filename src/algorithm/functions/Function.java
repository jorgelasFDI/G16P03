package algorithm.functions;

import java.util.List;

import algorithm.individuos.Individuo;
import auxiliar.binary.GenRange;
import algorithm.population.Poblacion;

public abstract class Function {

    protected Integer numVars;
    protected boolean minimizar;
    protected String name;
    protected List<GenRange> ranges;

    public Function(boolean minimizar, String name, Integer numVars, List<GenRange> ranges) {
        this.minimizar = minimizar;
        this.name = name;
        this.numVars = numVars;
        this.ranges = ranges;
    }

    public static double fitness(Function function, Individuo cromosoma) {
        return function.fitnessInstance(cromosoma, null);
    } public abstract double fitnessInstance(Individuo cromosoma, Poblacion poblacion);

    public static double bloating(Function function, Individuo cromosoma) {
        return function.bloatingInstance(cromosoma, null);
    } public abstract double bloatingInstance(Individuo individuo, Poblacion poblacion);

    public String toString() {
        return name;
    }

    public boolean getMinimizar() {
        return minimizar;
    }

    public Integer getNumVars() {
        return numVars;
    }

    public List<GenRange> getRanges() {
        return ranges;
    }

}
