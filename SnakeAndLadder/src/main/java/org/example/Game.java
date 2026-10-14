package org.example;

import java.util.*;

public class Game {
    int noOfSnakes;
    int noOfLadders;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Dice dice;
    private Board board;
    private Queue<Player> players;

    public Game(int boardSize, int snakes, int ladders) {
        this.board = new Board(boardSize);
        this.players = new ArrayDeque<>();
        this.noOfLadders = ladders;
        this.noOfSnakes = snakes;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.dice = new Dice(1, 6);
        initBoard();
    }

    void initBoard() {
        Random random = new Random();
        Set<String> slSet = new HashSet<>();
        for (int i = 0; i < noOfSnakes; i++) {
            while (true) {
                int snakeStart = random.nextInt(board.getStart(), board.getSize());
                int snakeEnd = random.nextInt(board.getStart(), board.getSize());
                if (snakeEnd >= snakeStart)
                    continue;
                String startEndPair = String.valueOf(snakeStart) + snakeEnd;
                if (!slSet.contains(startEndPair)) {
                    Snake snake = new Snake(snakeStart, snakeEnd);
                    snakes.add(snake);
                    slSet.add(startEndPair);
                    break;
                }
            }
        }
        for (int i = 0; i < noOfLadders; i++) {
            while (true) {
                int ladderStart = random.nextInt(board.getStart(), board.getSize());
                int ladderEnd = random.nextInt(board.getStart(), board.getSize());
                if (ladderEnd <= ladderStart)
                    continue;
                String startEndPair = String.valueOf(ladderStart) + ladderEnd;
                if (!slSet.contains(startEndPair)) {
                    Ladder ladder = new Ladder(ladderStart, ladderEnd);
                    ladders.add(ladder);
                    slSet.add(startEndPair);
                    break;
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playGame() {
        while(true)
        {
            Player player = players.poll();
            int diceValue = dice.roll();
            System.out.println(player.getName() + " rolled a " + diceValue);
            int newPosition = player.getPosition() + diceValue;
            if(newPosition > board.getEnd()) {
                System.out.println(player.getName() + " cannot move beyond the board. Staying at position " + player.getPosition());
                players.offer(player);
            } else {
                player.setPosition(getNewPosition(newPosition));
                if(player.getPosition()== board.getEnd()) {
                    player.setWon(true);
                    System.out.println("Player " + player.getName() + " Won.");
                } else {
                    System.out.println("Setting " + player.getName() + "'s new position to " + player.getPosition());
                    players.offer(player);
                }
            }
            if (players.size() < 2) {
                break;
            }
        }
    }

    public Integer getNewPosition(int position) {
        for (Snake snake : snakes) {
            if (snake.getHead() == position) {
                System.out.println("Snake Bit");
                return snake.getTail();
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) {
                System.out.println("Climbed ladder");
                return ladder.getEnd();
            }
        }
        return position;
    }
}
