package org.example.machine;

import org.example.CannotMakeChangeException;
import org.example.Slot;

import java.util.List;

public class DispenseState implements VendingMachineState {
    private VendingMachine machine;
    public DispenseState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("✗ Dispensing, please wait.");
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("✗ Dispensing, please wait.");
    }

    @Override
    public void dispense() {
        Slot slot = machine.getSelectedSlot();
        int price = slot.getProduct().getPrice();
        int changeOwed = machine.getBalance() - price;

        try {
            // 1. Work out change BEFORE touching stock or money.
            List<Integer> change = machine.getChangeStrategy()
                    .makeChange(changeOwed, machine.getCoinFloat());

            // 2. Commit: drop product, bank the money paid.
            slot.dispenseOne();
            machine.bankBalance(); // the inserted coins now belong to the float

            System.out.println("✓ Dispensed: " + slot.getProduct().getName());
            if (!change.isEmpty()) System.out.println("✓ Change returned: " + change);
        } catch (CannotMakeChangeException e) {
            System.out.println("✗ " + e.getMessage() + " — refunding.");
            machine.refundBalance();
        } finally {
            machine.reset();
            machine.setState(machine.getIdleState());
        }
    }

    @Override
    public void cancel() {
        System.out.println("✗ Too late to cancel.");
    }

    @Override
    public String name() {
        return "DISPENSE";
    }
}
