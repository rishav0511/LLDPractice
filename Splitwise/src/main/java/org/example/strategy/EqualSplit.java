package org.example.strategy;

import org.example.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit implements Split {
    @Override
    public boolean validate(double totalAmount, List<User> participants) {
        return participants != null && !participants.isEmpty() && totalAmount > 0;
    }

    @Override
    public Map<User, Double> computeShares(double totalAmount, List<User> participants) {
        Map<User, Double> shares = new HashMap<>();
        double each = totalAmount / participants.size();
        for (User u : participants) {
            shares.put(u, each);
        }
        return shares;
    }
}
