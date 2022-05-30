package algorithm.population;

import java.util.ArrayList;
import java.util.List;

import algorithm.functions.Function;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoFactory;
import algorithm.individuos.gen.GenRange;
import algorithm.individuos.vuelo.Vuelo;

public class GeneratePoblacion {
    
    public List<Individuo> generaPoblacion(String type, Integer depth, Integer size, Double tolerancia, List<GenRange> ranges, List<Vuelo> vuelos, Function function, Poblacion poblacion) {
        List<Individuo> poblacionList = new ArrayList<>(size);
        switch (type) {
            case "RampedAndHalf":
                int numGrupos = (int) depth - 1;
                int tamGrupo = size / numGrupos;
                int profundidad = 2;        //Profundidad para el primer grupo
                for(int i = 0; i < numGrupos; i++) {
                    for(int j = 0; j < tamGrupo; j++) {
                        if(j < tamGrupo/2)
                            poblacionList.add(IndividuoFactory.create("Completo", profundidad, null, null, null, function, poblacion));
                        else 
                            poblacionList.add(IndividuoFactory.create("Creciente", profundidad, null, null, null, function, poblacion));
                    } profundidad++;
                }; break;
            default:
                for (int i = 0; i < size; i++) {
                    poblacionList.add(IndividuoFactory.create(type, depth, tolerancia, ranges, vuelos, function, poblacion));
                }; break;
        }; return poblacionList;
    }
    
}
