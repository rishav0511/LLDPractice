package org.example.machine;

public class HasSelectionState implements VendingMachineState {
    private final VendingMachine machine;
    public HasSelectionState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(String code) {
        // Allow re-selecting while no money is in; delegate to idle logic.
        machine.setState(machine.getIdleState());
        machine.selectProduct(code);
    }

    @Override
    public void insertMoney(int amount) {
        machine.addToBalance(amount);
        int price = machine.getSelectedSlot().getProduct().getPrice();
        System.out.println("  Balance: ₹" + machine.getBalance() / 100.0 + " / ₹" + price / 100.0);
        if (machine.getBalance() >= price) {
            System.out.println("→ Enough money in. Press dispense.");
            machine.setState(machine.getHasMoneyState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("✗ Insert more money first.");
    }

    @Override
    public void cancel() {
        machine.refundBalance();
        machine.reset();
        machine.setState(machine.getIdleState());
    }

    @Override
    public String name() {
        return "HAS_SELECTION";
    }
}
