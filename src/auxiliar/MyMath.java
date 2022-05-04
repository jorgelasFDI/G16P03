package auxiliar;

import java.util.List;

public class MyMath {
    
    public static int max(List<Integer> list) {
        int maxValue = Integer.MIN_VALUE;
        for (int value: list) {
            if (value > maxValue)
                maxValue = value;
        } return maxValue;
    }

    public static int min(List<Integer> list) {
        int minValue = Integer.MAX_VALUE;
        for (int value: list) {
            if (value < minValue)
                minValue = value;
        } return minValue;
    }
    
    public static double covariance(List<Integer> profIndividuos, List<Double> fitnessIndividuos, double avgDepth, double avgFitness) {
    	int n = profIndividuos.size();
    	double total = 0.0;
    	
    	for(int i = 0; i < n; i++) {
    		double prof = (double) (profIndividuos.get(i) - avgDepth);
    		double fitness = (double) (fitnessIndividuos.get(i) - avgFitness);
    		total += (prof * fitness);
    	}
    	
    	return total / n;
    }
    
    public static double variance(List<Integer> profIndividuos, double avgDepth) {
    	int n = profIndividuos.size();
    	double total = 0.0;
    	
    	for(int i = 0; i < n; i++) {
    		double prof = (double) (profIndividuos.get(i) - avgDepth);
    		total += (double) Math.pow(prof, 2);
    	}
    	
    	return (total/n);
    }

}
