import java.util.Scanner;

class Board {
    private int count = 0;
    private String board[] = { " ", " ", " ", " ", " ", " ", " ", " ", " " };

    void printBoard() {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
    }

    boolean updateBoard(int position, String type) {
        if (board[position - 1].equals(" ")) {
            board[position - 1] = type;
            count++;
            return true;
        } else {
            System.out.println("Position already selected. Select another position.");
            return false;
        }
    }

    boolean checkDraw() {
        return count == 9;
    }

    boolean checkWinner(String type) {
        return (board[0].equals(type) && board[1].equals(type) && board[2].equals(type))
                || (board[3].equals(type) && board[4].equals(type) && board[5].equals(type))
                || (board[6].equals(type) && board[7].equals(type) && board[8].equals(type))
                || (board[0].equals(type) && board[3].equals(type) && board[6].equals(type))
                || (board[1].equals(type) && board[4].equals(type) && board[7].equals(type))
                || (board[2].equals(type) && board[5].equals(type) && board[8].equals(type))
                || (board[0].equals(type) && board[4].equals(type) && board[8].equals(type))
                || (board[2].equals(type) && board[4].equals(type) && board[6].equals(type));
    }

    void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = " ";
        }
        count = 0;
    }
}

class Player {
    private String name;
    private String type;

    Player(String type) {
        this.type = type;
        Scanner input = new Scanner(System.in);
        System.out.println("Player selecting " + type + ", enter your name: ");
        name = input.nextLine();
    }

    String getName() {
        return name;
    }

    String getType() {
        return type;
    }
}

class Game {
    private Board board = new Board();
    private Player player1 = new Player("X");
    private Player player2 = new Player("O");
    private Player currentPlayer = player1;

    void play() {
        String message = "enter the position (1 - 9):";
        while (true) {
            System.out.println(currentPlayer.getName() + " " + message);
            Scanner input = new Scanner(System.in);
            int position = input.nextInt();
            if (position < 1 || position > 9) {
                System.out.println("Invalid position. Position must be between 1 and 9.");
                continue;
            }
            if (board.updateBoard(position, currentPlayer.getType())) {
                board.printBoard();
                if (board.checkWinner(currentPlayer.getType())) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    if (!playAgain()) {
                        return;
                    }
                    board.resetBoard();
                } else if (board.checkDraw()) {
                    System.out.println("Game is a draw!");
                    if (!playAgain()) {
                        return;
                    }
                    board.resetBoard();
                } else {
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }
            }
        }
    }

    boolean playAgain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to play again? (yes/no)");
        String choice = input.nextLine();
        return choice.equalsIgnoreCase("yes");
    }
}

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
