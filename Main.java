import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        char currentPlayer = 'X';

        while (true) {
            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println("|");
            }
            System.out.println("---------");

            if (currentPlayer == 'X') {
                System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
                int move = scanner.nextInt() - 1;

                if (move < 0 || move >= 9 || board[move / 3][move % 3] != ' ') {
                    System.out.println("Invalid move.");
                    continue;
                }
                board[move / 3][move % 3] = currentPlayer;
            } else {
                System.out.println("Player " + currentPlayer + " is thinking...");
                int bestScore = Integer.MIN_VALUE;
                int bestMove = -1;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == ' ') {
                            board[i][j] = currentPlayer;
                            int score = minimax(board, false, currentPlayer);
                            board[i][j] = ' ';
                            if (score > bestScore) {
                                bestScore = score;
                                bestMove = i * 3 + j;
                            }
                        }
                    }
                }

                board[bestMove / 3][bestMove % 3] = currentPlayer;
            }

            if (checkWinner(board)) {
                System.out.println("Player " + currentPlayer + " won!");
                break;
            }

            if (isDraw(board)) {
                System.out.println("The game is a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static int minimax(char[][] board, boolean isMaximizing, char currentPlayer) {
        if (checkWinner(board)) {
            return isMaximizing ? -1 : 1;
        }
        if (isDraw(board)) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        char nextPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = nextPlayer;
                    int score = minimax(board, !isMaximizing, nextPlayer);
                    board[i][j] = ' ';
                    bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }
    private static boolean checkWinner(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return true;
        }
        return false;
    }
    private static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
