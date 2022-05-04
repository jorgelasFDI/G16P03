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

    public static int fitness(Function function, Individuo cromosoma) {
        return function.fitnessInstance(cromosoma);
    } public abstract int fitnessInstance(Individuo cromosoma);

    public String toString() {
        return name;
    }

    public boolean getMinimizar() {
        return minimizar;
    }

}
