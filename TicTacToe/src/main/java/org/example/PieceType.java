package org.example;

public enum PieceType {
    X('X'), O('O');

    private final char symbol;

    PieceType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
