package My_Project;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');
        Board board = new Board(player1, player2);
        Player currentPlayer = player1;

        while (board.getGameState() == GameState.IN_PROGRESS) {
            board.drawBoard();

            System.out.println(currentPlayer.getName() + ", enter your move (1-9): ");
            int move = scanner.nextInt() - 1;

            if (!board.makeTurn(currentPlayer, move)) {
                System.out.println("Invalid move.");
                continue;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        board.drawBoard();
        if (board.getGameState() == GameState.X_WON) {
            System.out.println("X won!");
        } else if (board.getGameState() == GameState.O_WON) {
            System.out.println("O won!");
        } else {
            System.out.println("The game is a draw!");
        }
    }


}
