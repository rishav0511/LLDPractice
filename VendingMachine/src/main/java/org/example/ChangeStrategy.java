package org.example;

import java.util.List;
import java.util.Map;

public interface ChangeStrategy {

    /**
     * @param amount the change owed, in paise
     * @param floatCoins available denominations (value → count), mutated on success
     * @return the list of denominations to return
     * @throws CannotMakeChangeException if exact change isn't possible
     */
    List<Integer> makeChange(int amount, Map<Integer, Integer> floatCoins);
}
