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

}
