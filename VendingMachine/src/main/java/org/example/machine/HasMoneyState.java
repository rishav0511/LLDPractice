package org.example.machine;

public class HasMoneyState implements VendingMachineState {
    private VendingMachine machine;
    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("✗ Finish or cancel the current purchase first.");
    }

    @Override
    public void insertMoney(int amount) {
        machine.addToBalance(amount);
        System.out.println("  Extra money accepted; will be returned as change.");
    }

    @Override
    public void dispense() {
        machine.setState(machine.getDispenseState());
        machine.dispense(); // delegate the actual work to the dispense state
    }

    @Override
    public void cancel() {
        machine.refundBalance();
        machine.reset();
        machine.setState(machine.getIdleState());
    }

    @Override
    public String name() {
        return "HAS_MONEY";
    }
}
