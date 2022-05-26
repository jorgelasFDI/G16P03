package algorithm.population;

import java.util.List;

import algorithm.functions.Function;
import algorithm.individuos.Individuo;

public interface PoblacionInterface {
    public List<Individuo> generaPoblacion(String type, double depth, int size, Function function, Poblacion poblacion);
    
}
