package algorithm.population;

import java.util.ArrayList;
import java.util.List;

import algorithm.functions.Function;
import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoFactory;

public class PoblacionTree implements PoblacionInterface {

    @Override
    public List<Individuo> generaPoblacion(String type, int depth, int size, Function function) {
        List<Individuo> poblacion = new ArrayList<>(size);
        int numGrupos = depth - 1;
        int tamGrupo = size / (depth - 1);
        int profundidad = 2;        //Profundidad para el primer grupo
        if(type.equals("RampedAndHalf")) for(int i = 0; i < numGrupos; i++) {
            for(int j = 0; j < tamGrupo; j++) if(j < tamGrupo/2)
                poblacion.add(IndividuoFactory.create("Completo", profundidad, function));
            else poblacion.add(IndividuoFactory.create("Creciente", profundidad, function));
            profundidad++;
        } else for (int i = 0; i < size; i++) {
            poblacion.add(IndividuoFactory.create(type, depth, function));
        } return poblacion;
    }
    
}
