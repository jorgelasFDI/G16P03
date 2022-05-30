package auxiliar.binary;

import java.util.List;

public class Binary {
    
    public static int binToInt(List<Boolean> binary) {
        int sum = 0;
        for (int i = 0; i < binary.size(); i++) 
            sum += binary.get(i) ? Math.pow(2, i) : 0;
        return sum;
    }

    public static boolean toBool(int a) {
		return (a == 1 ? true : false);
	}

}
