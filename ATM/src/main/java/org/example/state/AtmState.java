package org.example.state;

import org.example.ATMSystem;
import org.example.OperationType;

public interface AtmState {
    void insertCard(ATMSystem atmSystem, String cardNumber);
    void enterPin(ATMSystem atmSystem, String pin);
    void selectOperation(ATMSystem atmSystem, OperationType op, int... args);
    void ejectCard(ATMSystem atmSystem);
}
