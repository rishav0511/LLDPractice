package org.example.machine;

/**
 * One method per user action. Every concrete state must decide how to handle each — including the ones it should reject.
 * 💡 An interface is a list of method signatures with no bodies — a contract.
 * Because all four states implement the same contract, the machine can hold any of them in one current field
 * and call the same methods without knowing which it has.
 */

public interface VendingMachineState {
    void selectProduct(String code);
    void insertMoney(int amount);
    void dispense();
    void cancel();
    String name();
}
