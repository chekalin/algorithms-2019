package algorithms.course3.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WeightedIndependentSet {

    static int[] calculateWeightedIndependentSet(int[] path) {
        if (path.length == 0) return new int[0];
        if (path.length == 1) return new int[]{0};

        int[] subPathMaxWeights = new int[path.length + 1];
        subPathMaxWeights[0] = 0;
        subPathMaxWeights[1] = path[0];
        for (int i = 2; i <= path.length; i++) {
            subPathMaxWeights[i] = Math.max(subPathMaxWeights[i - 1], subPathMaxWeights[i - 2] + path[i - 1]);
        }
        System.out.println("subPathMaxWeights = " + Arrays.toString(subPathMaxWeights));
        // reconstruct
        List<Integer> wis = new ArrayList<>();
        int i = path.length;
        while (i >= 2) {
            if (subPathMaxWeights[i - 1] >= subPathMaxWeights[i - 2] + path[i - 1]) {
                i--;
            } else {
                wis.add(i - 1);
                i = i - 2;
            }
        }
        if (i == 1) {
            wis.add(0);
        }
        return toArray(wis);
    }

    private static int[] toArray(List<Integer> wis) {
        int[] result = new int[wis.size()];
        for (int i = 0; i < wis.size(); i++) {
            result[i] = wis.get(i);
        }
        return result;
    }
}
