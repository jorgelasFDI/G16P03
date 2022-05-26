package algorithm.individuos;

import java.util.List;

import algorithm.functions.Function;
import algorithm.individuos.gen.GenRange;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;

public class IndividuoFactory {

    public static Individuo create(String type, Double depth, Double tolerancia, List<GenRange> ranges, Function function) {
        switch (type) {
            case "Completo":
                IndividuoTree individuoComplete = new IndividuoTree(function);
                individuoComplete.init(depth, (x, y) -> (x >= y), LogicalNode.map);
                return individuoComplete;
            case "Creciente":
                IndividuoTree individuoCreciente = new IndividuoTree(function);
                individuoCreciente.init(depth, (x, y) -> (0.75 < MyRandom.getInstance().nextDouble() || x >= y), LogicalNode.map);
                return individuoCreciente;
            case "Real":
                IndividuoReal individuoReal = new IndividuoReal(function);
                individuoReal.init(ranges);
                return individuoReal;
            case "Binary":
                IndividuoBinary individuoBinary = new IndividuoBinary(function);
                individuoBinary.init(tolerancia, ranges);
                return individuoBinary;
            default:
                return null;
        }
    }
        
}
