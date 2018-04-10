import java.util.ArrayList;

public class Game {
    private Move randMove;
    private Move aiMove;
    private RandPlayer randPlayer= new RandPlayer();
    private Board board = new Board('R');
    private ArrayList<Move> allPossibleMoves;
    public char[][]boardState;
    private char winner;

    /**
     * Sending the random move to minimax and getting the answer of the algo
     * @return the move made by AI
     */
    public Move rightMove() {
        while (this.aiMove== null) {
            this.allPossibleMoves = this.board.getAllLegalMovesForSide('R');
            this.randMove = randPlayer.getRandMove(this.allPossibleMoves);
            this.aiMove = board.makeMove(this.randMove);
            this.boardState = board.getBoardState();
            setWinner();
        }

        return this.aiMove;
    }

    public void setAiMove(Move aiMove) {
        this.aiMove = aiMove;
    }

    public void setWinner() {
        this.winner = board.getWinner();
    }
    public char getWinner(){
        return this.winner;
    }

}