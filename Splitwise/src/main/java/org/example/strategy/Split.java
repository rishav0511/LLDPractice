package org.example.strategy;

import org.example.User;

import java.util.List;
import java.util.Map;

public interface Split {
    boolean validate(double totalAmount, List<User> participants);
    Map<User, Double> computeShares(double totalAmount, List<User> participants);
}
