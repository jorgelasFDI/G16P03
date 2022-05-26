package algorithm.individuos;

import algorithm.functions.Function;
import auxiliar.MyRandom;
import auxiliar.tree.LogicalNode;

public class IndividuoFactory {

    public static Individuo create(String type, double depth, Function function) {
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
                return individuoReal;
            case "Binary":
                IndividuoBinary individuoBinary = new IndividuoBinary(function);
                return individuoBinary;
            default:
                return null;
        }
    }
        
}
