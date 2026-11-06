package org.example;

import java.util.*;

public class BalanceSheet {
    // balances[A_id][B_id] = amount A owes B (positive means A owes B)
    private final Map<String, Map<String, Double>> balances = new HashMap<>();
    private final Map<String, User> userMap = new HashMap<>();

    public void recordDebt(User from, User to, double amount) {
        if (amount <= 0 || from.equals(to)) return;
        userMap.putIfAbsent(from.getId(), from);
        userMap.putIfAbsent(to.getId(), to);

        // Net the balances: if to already owes from, reduce that first
        double reverseDebt = getDirectDebt(to.getId(), from.getId());
        if (reverseDebt >= amount) {
            setDebt(to.getId(), from.getId(), reverseDebt - amount);
        } else {
            setDebt(to.getId(), from.getId(), 0);
            double existing = getDirectDebt(from.getId(), to.getId());
            setDebt(from.getId(), to.getId(), existing + (amount - reverseDebt));
        }
    }

    public Map<String, Double> getBalanceForUser(String userId) {
        Map<String, Double> result = new HashMap<>();
        // What this user owes others (negative for them)
        Map<String, Double> owes = balances.getOrDefault(userId, Collections.emptyMap());
        for (Map.Entry<String, Double> e : owes.entrySet()) {
            if (e.getValue() > 0.01) result.put(e.getKey(), -e.getValue());
        }
        // What others owe this user (positive for them)
        for (Map.Entry<String, Map<String, Double>> entry : balances.entrySet()) {
            if (entry.getKey().equals(userId)) continue;
            double amt = entry.getValue().getOrDefault(userId, 0.0);
            if (amt > 0.01) result.put(entry.getKey(), amt);
        }
        return result;
    }

    public List<Transaction> simplifyDebts() {
        // Step 1: Compute net balance for each user
        Map<String, Double> netBalance = new HashMap<>();
        for (Map.Entry<String, Map<String, Double>> entry : balances.entrySet()) {
            String fromId = entry.getKey();
            for (Map.Entry<String, Double> debt : entry.getValue().entrySet()) {
                String toId = debt.getKey();
                double amt = debt.getValue();
                if (amt < 0.01) continue;
                netBalance.merge(fromId, -amt, Double::sum);  // from owes, so negative
                netBalance.merge(toId, amt, Double::sum);     // to is owed, so positive
            }
        }

        // Step 2: Separate into creditors (positive) and debtors (negative)
        // Max-heap for creditors, min-heap (max of abs) for debtors
        PriorityQueue<double[]> creditors = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        PriorityQueue<double[]> debtors = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));

        List<String> userIds = new ArrayList<>(netBalance.keySet());
        for (String uid : userIds) {
            double bal = netBalance.get(uid);
            if (bal > 0.01) creditors.offer(new double[]{userIds.indexOf(uid), bal});
            else if (bal < -0.01) debtors.offer(new double[]{userIds.indexOf(uid), bal});
        }

        // Step 3: Greedy matching
        List<Transaction> transactions = new ArrayList<>();
        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            double[] creditor = creditors.poll();
            double[] debtor = debtors.poll();

            double settle = Math.min(creditor[1], -debtor[1]);
            User fromUser = userMap.get(userIds.get((int) debtor[0]));
            User toUser = userMap.get(userIds.get((int) creditor[0]));
            transactions.add(new Transaction(fromUser, toUser, settle));

            creditor[1] -= settle;
            debtor[1] += settle;

            if (creditor[1] > 0.01) creditors.offer(creditor);
            if (debtor[1] < -0.01) debtors.offer(debtor);
        }
        return transactions;
    }

    private double getDirectDebt(String fromId, String toId) {
        return balances.getOrDefault(fromId, Collections.emptyMap()).getOrDefault(toId, 0.0);
    }

    private void setDebt(String fromId, String toId, double amount) {
        balances.computeIfAbsent(fromId, k -> new HashMap<>()).put(toId, amount);
    }
}
