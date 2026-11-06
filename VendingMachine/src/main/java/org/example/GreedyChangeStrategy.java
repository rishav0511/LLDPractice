package org.example;

import java.util.*;

public class GreedyChangeStrategy implements ChangeStrategy {

    @Override
    public List<Integer> makeChange(int amount, Map<Integer, Integer> floatCoins) {
        List<Integer> denoms = new ArrayList<>(floatCoins.keySet());
        denoms.sort(Collections.reverseOrder());

        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> used = new HashMap<>();
        int remaining = amount;

        for (int d : denoms) {
            int available = floatCoins.getOrDefault(d, 0);
            int take = Math.min(remaining / d, available);
            for (int i = 0; i < take; i++)
                result.add(d);
            if (take > 0)
                used.put(d, take);
            remaining -= take * d;
        }

        if (remaining != 0) {
            throw new CannotMakeChangeException("Cannot make exact change for " + amount);
        }
        // Commit only now that we know it worked.
        used.forEach((d, count) -> floatCoins.merge(d, -count, Integer::sum));
        return result;
    }
}
