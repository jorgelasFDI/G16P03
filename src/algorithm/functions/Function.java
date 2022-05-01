package algorithm.functions;

import algorithm.individuos.Individuo;

public abstract class Function {

    protected int numGenes;
    protected boolean minimizar;
    protected String name;

    public Function(boolean minimizar, String name) {
        this.minimizar = minimizar;
        this.name = name;
    }

    public static double fitness(Function function, Individuo cromosoma) {
        return function.fitnessInstance(cromosoma);
    } public abstract double fitnessInstance(Individuo cromosoma);

    public String toString() {
        return name;
    }

}
