package algorithm.individuos;

import java.util.List;

import algorithm.functions.Function;
import algorithm.population.Poblacion;
import auxiliar.MyRandom;
import auxiliar.binary.GenRange;
import auxiliar.tree.LogicalNode;
import auxiliar.vuelo.Vuelo;

public class IndividuoFactory {

    public static Individuo create(String type, Integer depth, Double tolerancia, List<GenRange> ranges, List<Vuelo> vuelos, Function function, Poblacion poblacion) {
        switch (type) {
            case "Practica3-Completo":
                IndividuoTree individuoComplete = new IndividuoTree(function, poblacion);
                individuoComplete.init(depth, (x, y) -> (x >= y), LogicalNode.map);
                return individuoComplete;
            case "Practica3-Creciente":
                IndividuoTree individuoCreciente = new IndividuoTree(function, poblacion);
                individuoCreciente.init(depth, (x, y) -> (0.75 < MyRandom.getInstance().nextDouble() || x >= y), LogicalNode.map);
                return individuoCreciente;
            case "Practica1-Real":
                IndividuoReal individuoReal = new IndividuoReal(function, poblacion);
                individuoReal.init(ranges);
                return individuoReal;
            case "Practica1-Binary":
                IndividuoBinary individuoBinary = new IndividuoBinary(function, poblacion);
                individuoBinary.init(tolerancia, ranges);
                return individuoBinary;
            case "Practica2-Vuelo":
                IndividuoVuelo individuoVuelo = new IndividuoVuelo(function, poblacion);
                individuoVuelo.init(vuelos);
                return individuoVuelo;
            default:
                return null;
        }
    }
        
}
