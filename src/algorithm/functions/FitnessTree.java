package algorithm.functions;

import java.util.List;

import algorithm.individuos.Individuo;
import algorithm.individuos.IndividuoTree;
import algorithm.population.Poblacion;
import auxiliar.MyRandom;
import auxiliar.binary.Binary;
import auxiliar.tree.LogicalNode;
import auxiliar.tree.Tree;

public class FitnessTree extends Function {
	
    public FitnessTree() {
        super(false, "function tree", null, null);
    }

    public Boolean execFunction(Tree<String> root, List<Integer> combinacion, IndividuoTree cromosoma) {

		if (root == null)
			return null;

        LogicalNode obj = cromosoma.getObject(root.data);

		if (obj.type == "LEAF")
			return Binary.toBool(combinacion.get(obj.index));
		
        //Si el nodo es una funcion se mira en sus sub-arboles recursivamente
		Boolean x = execFunction(root.getChild(0), combinacion, cromosoma);
		Boolean y = execFunction(root.getChild(1), combinacion, cromosoma);
		Boolean z = execFunction(root.getChild(2), combinacion, cromosoma);

		return obj.ternary(x, y, z);
	}

    @Override
    public double fitnessInstance(Individuo cromosoma, Poblacion poblacion) {

		int total = 0;
        IndividuoTree individuo = (IndividuoTree) cromosoma;	
		for(int i = 0; i < LogicalNode.combinaciones.size(); i++) {
			if (execFunction(individuo.get(0), LogicalNode.combinaciones.get(i), individuo) == Binary.toBool(LogicalNode.solution.get(i)))
				total++;
		}
		// double value = ((double) total) / (double) LogicalNode.combinaciones.size();
		return total;
    }
    
	@Override
    public double bloatingInstance(Individuo cromosoma, Poblacion poblacion) {
		IndividuoTree individuo = (IndividuoTree) cromosoma;
		double maxDepth = individuo.getMaxDepth();
		double depth = individuo.get(0).depth();

		// CALCULATE AVERAGE DEPTH
		int totalNodes = 0;
		for (Individuo i: poblacion) {
			IndividuoTree ind = (IndividuoTree) i;
			totalNodes += ind.get(0).depth();
		} individuo.setMaxDepth((double) totalNodes / (double) poblacion.size());

		double prob = prob(individuo, poblacion, depth, maxDepth);
    	if(depth > maxDepth && MyRandom.getInstance().nextDouble() > prob) {
    		return 0;
    	} return individuo.getAptitudRevisada();
    }

	private double prob(IndividuoTree individuoTree, Poblacion pob, double depth, double maxDepth) {
		double depthDiff = 1 - maxDepth/depth;
		double scaledFitness = individuoTree.getAptitud()/pob.getMediaAptitud() - 1;
		
		return Math.max(func(scaledFitness - depthDiff), 0);
	}

	private double func(double var) {
		return 1.0 - 1.0/(Math.max(var, 0) + 1);
	}
}
