package org.example;

public class Player {
    String name;
    PlayingPiece pieceType;

    public Player(String name, PlayingPiece pieceType) {
        this.name = name;
        this.pieceType = pieceType;
    }

    public String getName() {
        return name;
    }
}
