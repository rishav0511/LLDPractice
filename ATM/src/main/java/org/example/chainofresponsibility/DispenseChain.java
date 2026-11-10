package org.example.chainofresponsibility;

public interface DispenseChain {
    void setNextChain(DispenseChain chain);
    void dispense(int amount);
    boolean canDispense(int amount);
}