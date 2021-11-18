package challenge;

import java.util.Arrays;
import java.util.List;

/**
 * @author Okala III
 */
public class Solution {

    public static void main(String[] args) {
//        Given an Array1 = [1, 3, 6, 4, 1, 2] returns 5, and Array2 = [5, -1, -3] returns 1.

        Integer[] array = {1, 2, 3, 4, 5};
        Arrays.sort(array);
        List<Integer> numList = Arrays.asList(array);
        int min = array[0];
        int max = array[array.length - 1];

        int x = Integer.MIN_VALUE;

        for (int i = min; i < max; i++) {
            if (numList.contains(i)) {
                continue;
            } else {
                if (i > 0) {
                    x = i;
                    break;
                }
            }
        }
        if (x == Integer.MIN_VALUE) {
            System.out.println("No result");
        } else
            System.out.println(x);
    }
}
