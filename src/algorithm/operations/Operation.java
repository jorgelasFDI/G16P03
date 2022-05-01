package algorithm.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algorithm.individuos.Individuo;
import auxiliar.MyRandom;

public abstract class Operation {

    protected Random random;
    protected double prob;
    protected String name;

    protected Operation() {
        this.random = MyRandom.getInstance();
    }

    public static <B> void operate(Operation operation, List<Individuo> poblacion) {
        operation.operationInstance(poblacion);
    } public abstract void operationInstance(List<Individuo> poblacion);
    
    public void setProb(double prob) {
        this.prob = prob;
    }

    protected List<Individuo> copy(List<Individuo> poblacion) {
        List<Individuo> nuevaPoblacion = new ArrayList<>(poblacion.size());
        for (Individuo individuo: poblacion) nuevaPoblacion.add(individuo.copy());
        return nuevaPoblacion;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
