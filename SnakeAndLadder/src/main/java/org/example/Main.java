package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create a new SnakeAndLadder game
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter board Size");
        int boardSize = scanner.nextInt();
        System.out.println("Enter number of players");
        int numberOfPlayers = scanner.nextInt();
        System.out.println("Enter number of snakes");
        int numSnakes = scanner.nextInt();
        System.out.println("Enter number of ladders");
        int numLadders = scanner.nextInt();

        Game game = new Game(numLadders, numSnakes, boardSize);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter player name");
            String pName = scanner.next();
            Player player = new Player(pName);
            game.addPlayer(player);
        }
        game.playGame();
    }
}