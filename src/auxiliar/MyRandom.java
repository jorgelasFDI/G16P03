package auxiliar;

import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

public class MyRandom {

    private static Random random;

    public static Random getInstance() {
        if (random == null) random = new Random();
        return random;
    }

    public static Pair<Integer, Integer> getRandomNoRepeat(List<Integer> selected, int ammount, int lowerbound, int upperbound) {
        int last = Integer.MIN_VALUE;
        int first = Integer.MAX_VALUE;
        while (selected.size() < ammount) {
            int num = random.nextInt((upperbound + 1) - lowerbound) + lowerbound;
            if (selected.contains(num)) continue;
            selected.add(num);
            last = Math.max(last, num);
            first = Math.min(first, num);
        } return new Pair<>(first, last);
    }

    public static String getSizeVuelo() {
    	int n = random.nextInt(3);
    	
    	if(n == 0)
    		return "W";
    	else if ( n == 1)
    		return "G";
    	else 
    		return "P";
    }
    
}
