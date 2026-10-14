package org.example;

import java.util.ArrayList;
import java.util.List;

public class Board {
    PlayingPiece [][] board;
    int size;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece pieceType) {
        if(row<0 || row>=size || col<0 || col>=size || board[row][col] != null) {
            return false;
        }
        board[row][col] = pieceType;
        return true;
    }

    public List<Integer> getFreeCells () {
        List<Integer> freeCells = new ArrayList<>();
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(board[i][j] == null) {
                    freeCells.add(i*size + j);
                }
            }
        }
        return freeCells;
    }

    void printBoard() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                if(board[i][j]!=null)
                    System.out.print(board[i][j] == null ? "." : board[i][j].getPieceType().getSymbol());
                else
                    System.out.print('_');
            }
            System.out.println();
        }
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }
}

