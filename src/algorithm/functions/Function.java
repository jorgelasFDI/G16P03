package algorithm.functions;

import algorithm.individuos.Individuo;
import algorithm.population.Poblacion;

public abstract class Function {

    protected int numGenes;
    protected boolean minimizar;
    protected String name;

    public Function(boolean minimizar, String name) {
        this.minimizar = minimizar;
        this.name = name;
    }

    public static double fitness(Function function, Individuo cromosoma) {
        return function.fitnessInstance(cromosoma, null);
    } public abstract double fitnessInstance(Individuo cromosoma, Poblacion poblacion);

    public String toString() {
        return name;
    }

    public boolean getMinimizar() {
        return minimizar;
    }

    public static double bloating(Function function, Individuo cromosoma) {
        return function.bloatingInstance(cromosoma, null);
    } public abstract double bloatingInstance(Individuo individuo, Poblacion poblacion);

}
