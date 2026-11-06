package org.example.machine;

import org.example.ChangeStrategy;
import org.example.GreedyChangeStrategy;
import org.example.Product;
import org.example.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    // States (created once, reused)
    private final VendingMachineState idle = new IdleState(this);
    private final VendingMachineState hasSelection = new HasSelectionState(this);
    private final VendingMachineState hasMoney = new HasMoneyState(this);
    private final VendingMachineState dispenseState = new DispenseState(this);
    private VendingMachineState current = idle;

    //Data
    private final Map<String, Slot> slots = new HashMap<>();
    private final Map<Integer, Integer> coinFloat = new HashMap<>(); // denomination → count
    private ChangeStrategy changeStrategy = new GreedyChangeStrategy();

    // In-flight transaction
    private Slot selectedSlot;
    private int balance;      // money inserted this transaction (paise)
    private final List<Integer> insertedCoins = new ArrayList<>();

    public void selectProduct(String code) {
        current.selectProduct(code);
    }

    public void dispense() {
        current.dispense();
    }

    public void cancel() {
        current.cancel();
    }

    public void insertMoney(int denomination) {
        coinFloatCandidate(denomination); // remember it so we can refund exact coins
        current.insertMoney(denomination);
    }

    // ─── State accessors (used by states) ────────────────────
    public VendingMachineState getIdleState() {
        return idle;
    }
    public VendingMachineState getHasSelectionState() {
        return hasSelection;
    }
    public VendingMachineState getHasMoneyState() {
        return hasMoney;
    }
    public VendingMachineState getDispenseState() {
        return dispenseState;
    }
    public void setState(VendingMachineState s) {
        this.current = s;
    }
    public String currentStateName() {
        return current.name();
    }

    // ─── Transaction data (used by states) ───────────────────
    public Slot getSlot(String code) { return slots.get(code); }
    public Slot getSelectedSlot() { return selectedSlot; }
    public void setSelectedSlot(Slot s) { this.selectedSlot = s; }
    public int getBalance() { return balance; }
    public void addToBalance(int amount) { balance += amount; }
    public ChangeStrategy getChangeStrategy() { return changeStrategy; }
    public void setChangeStrategy(ChangeStrategy s) { this.changeStrategy = s; }
    public Map<Integer, Integer> getCoinFloat() { return coinFloat; }

    private void coinFloatCandidate(int denomination) {
        insertedCoins.add(denomination);
    }

    /** The coins inserted this transaction become part of the float (sale committed). */
    public void bankBalance() {
        for (int c : insertedCoins)
            coinFloat.merge(c, 1, Integer::sum);
    }

    /** Give the inserted coins back to the user (cancel / cannot-make-change). */
    public void refundBalance() {
        if (!insertedCoins.isEmpty())
            System.out.println("✓ Refunded: " + insertedCoins);
    }

    public void reset() {
        selectedSlot = null;
        balance = 0;
        insertedCoins.clear();
    }

    // ─── Operator / setup ────────────────────────────────────
    public void addSlot(String code, Product product, int qty) {
        slots.put(code, new Slot(product, qty));
    }
    public void addCoins(int denomination, int count) {
        coinFloat.merge(denomination, count, Integer::sum);
    }

}
