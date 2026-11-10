package org.example;

import org.example.chainofresponsibility.DispenseChain;
import org.example.chainofresponsibility.NoteDispenser100;
import org.example.chainofresponsibility.NoteDispenser200;
import org.example.chainofresponsibility.NoteDispenser500;
import org.example.entities.BankService;
import org.example.entities.Card;
import org.example.entities.CashDispenser;
import org.example.state.AtmState;
import org.example.state.IdleState;

import java.util.concurrent.atomic.AtomicLong;

public class ATMSystem {
    private static ATMSystem INSTANCE;
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    private static final AtomicLong transactionCounter = new AtomicLong(0);
    private AtmState currentState;
    private Card currentCard;

    private ATMSystem() {
        this.currentState = new IdleState();
        this.bankService = new BankService();

        // Setup the dispenser chain
        DispenseChain c1 = new NoteDispenser100(10); // 10 x $100 notes
        DispenseChain c2 = new NoteDispenser200(20); // 20 x $200 notes
        DispenseChain c3 = new NoteDispenser500(30); // 30 x $500 notes
        c3.setNextChain(c2);
        c2.setNextChain(c1);
        this.cashDispenser = new CashDispenser(c3);
    }

    public static ATMSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ATMSystem();
        }
        return INSTANCE;
    }

    public void changeState(AtmState newState) { this.currentState = newState; }
    public void setCurrentCard(Card card) { this.currentCard = card; }

    public void insertCard(String cardNumber) {
        currentState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int... args) { currentState.selectOperation(this, op, args); }

    public Card getCard(String cardNumber) {
        return bankService.getCard(cardNumber);
    }

    public boolean authenticate(String pin) {
        return bankService.authenticate(currentCard, pin);
    }

    public void checkBalance() {
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your current account balance is: $%.2f%n", balance);
    }

    public void withdrawCash(int amount) {
        if (!cashDispenser.canDispenseCash(amount)) {
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        bankService.withdrawMoney(currentCard, amount);

        try {
            cashDispenser.dispenseCash(amount);
        } catch (Exception e) {
            bankService.depositMoney(currentCard, amount); // Deposit back if dispensing fails
        }
    }

    public void depositCash(int amount) {
        bankService.depositMoney(currentCard, amount);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public BankService getBankService() {
        return bankService;
    }

}
