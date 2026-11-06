package org.example.machine;

import org.example.Slot;

public class IdleState implements VendingMachineState {
    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(String code) {
        Slot slot = machine.getSlot(code);
        if (slot == null) {
            System.out.println("✗ No such product: " + code);
            return;
        }
        if (!slot.isAvailable()) {
            System.out.println("✗ Out of stock: " + slot.getProduct().getName());
            return;
        }
        machine.setSelectedSlot(slot);
        System.out.println("→ Selected " + slot.getProduct() + ". Please insert money.");
        machine.setState(machine.getHasSelectionState());
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("✗ Select a product first.");
    }

    @Override
    public void dispense() {
        System.out.println("✗ Select a product first.");
    }

    @Override
    public void cancel() {
        System.out.println("✗ No selection to cancel.");
    }

    @Override
    public String name() {
        return "Idle";
    }
}
