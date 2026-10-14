package org.example;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class GameOrchestrator {
    private Deque<Player> players;
    private Board board;

    public GameOrchestrator() {
        initializeGame();
    }

    void initializeGame() {
        players = new LinkedList<>();
        PlayingPieceX pieceX = new PlayingPieceX();
        PlayingPieceO pieceO = new PlayingPieceO();
        players.add(new Player("Player 1", pieceX));
        players.add(new Player("Player 2", pieceO));
        board = new Board(3);
    }

    public void startGame() {
        boolean noWinner = true;
        while(noWinner) {
            if(board.getFreeCells().isEmpty()) {
                noWinner = false;
                System.out.println("Game Over! It's a draw.");
                break;
            }

            Player currentPlayer = players.removeFirst();
            board.printBoard();
            System.out.println(currentPlayer.getName() + "'s turn. Please enter your move (row and column): ");
            Scanner scanner = new Scanner(System.in);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            boolean addPiece = board.addPiece(row, col, currentPlayer.pieceType);
            if(!addPiece) {
                System.out.println("Invalid move, try again.");
                players.addFirst(currentPlayer);
                continue;
            }
            if(checkWinner(row, col, currentPlayer.pieceType)) {
                noWinner = false;
                board.printBoard();
                System.out.println("Game Over! " + currentPlayer.getName() + " wins!");
            } else {
                players.addLast(currentPlayer);
            }
        }
    }

    private boolean checkWinner(int row, int col, PlayingPiece pieceType) {
        PlayingPiece [][] boardState = board.getBoard();
        int size = boardState.length;
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //check rows
        for(int i=0;i<size;i++) {
            if(boardState[row][i] != pieceType || boardState[row][i]==null) {
                rowMatch = false;
            }
            if(boardState[i][col] != pieceType || boardState[i][col]==null) {
                colMatch = false;
            }
            if(boardState[i][i] != pieceType || boardState[i][i]==null) {
                diagonalMatch = false;
            }
            if(boardState[i][size-1-i] != pieceType || boardState[i][size-1-i]==null) {
                antiDiagonalMatch = false;
            }
        }
        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }

}
