package algorithm.functiones;

import algorithm.individuos.Individuo;

public abstract class Fitness {

    protected int numGenes;
    protected boolean minimizar;
    protected String name;

    public Fitness(boolean minimizar, String name) {
        this.minimizar = minimizar;
        this.name = name;
    }

    public static double fitness(Fitness function, Individuo cromosoma) {
        return function.fitnessInstance(cromosoma);
    } public abstract double fitnessInstance(Individuo cromosoma);

    public int getNumGenes() {
        return numGenes;
    }

    public void setNumGenes(int i) {
        numGenes = i;
    }

    public String toString() {
        return name;
    }

}
