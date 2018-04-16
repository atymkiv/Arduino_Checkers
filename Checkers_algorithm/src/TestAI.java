public class TestAI {

    public static void main(String[] args) {
        Game game = new Game();
        char winner = game.getWinner();

     //starting the game and
        for (int i = 0; i < 10; i++) {
            Move aiMove = game.rightMove();
            int row = aiMove.getRow();
            int col = aiMove.getCol();
            System.out.println("ROW: " + row + " COL: " + col);
            char[][] board = copyBoard(game.boardState);
            printBoard(board);
            System.out.println("ХІД: " +i);
            game.setAiMove(null);
        }
        System.out.println("Red pieces "+ String.valueOf(game.getRedPieces())+" Black pieces: "+game.getBlackPieces());
    }
    static void printBoard(char board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static char[][] copyBoard(char[][] board) {
        char[][] newBoard = new char[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                newBoard[row][col] = board[row][col];
            }
        }
        return newBoard;
    }
}
