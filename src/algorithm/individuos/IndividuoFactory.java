package algorithm.individuos;

import java.util.List;

import algorithm.functions.Function;
import algorithm.individuos.gen.GenRange;
import algorithm.population.Poblacion;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;

public class IndividuoFactory {

    public static Individuo create(String type, Double depth, Double tolerancia, List<GenRange> ranges, Function function, Poblacion poblacion) {
        switch (type) {
            case "Completo":
                IndividuoTree individuoComplete = new IndividuoTree(function, poblacion);
                individuoComplete.init(depth, (x, y) -> (x >= y), LogicalNode.map);
                return individuoComplete;
            case "Creciente":
                IndividuoTree individuoCreciente = new IndividuoTree(function, poblacion);
                individuoCreciente.init(depth, (x, y) -> (0.75 < MyRandom.getInstance().nextDouble() || x >= y), LogicalNode.map);
                return individuoCreciente;
            case "Real":
                IndividuoReal individuoReal = new IndividuoReal(function, poblacion);
                individuoReal.init(ranges);
                return individuoReal;
            case "Binary":
                IndividuoBinary individuoBinary = new IndividuoBinary(function, poblacion);
                individuoBinary.init(tolerancia, ranges);
                return individuoBinary;
            default:
                return null;
        }
    }
        
}
