import java.util.ArrayList;

public class Game {
    private Move randMove;
    private Move aiMove;
    private RandPlayer randPlayer= new RandPlayer();
    private Board board;
    private ArrayList<Move> allPossibleMoves;
    private char[][]boardState;
    private char winner;

    public Game(){}
    public Game(char[][] board, char player){
        this.board = new Board();
        this.board.setPlayer(player);
        this.board.setBoardState(board);
    }

    /**
     * Sending the random move to minimax and getting the answer of the algo
     * @return the board
     */
    public char[][] minimaxBoard() {
        if (board.getPlayer()==Board.RED) {
            this.allPossibleMoves = this.board.getAllLegalMovesForSide(Board.RED);
            if (allPossibleMoves.size()>0)
                this.randMove = randPlayer.getRandMove(this.allPossibleMoves);
            else {winner = Board.BLACK; return null;}
            this.aiMove = board.makeMove(this.randMove);
        }
        else {
            this.aiMove = board.makeMove(this.randMove);
        }
            this.boardState = this.board.getBoardState();
            setWinner();
        return this.boardState;
    }

    public Move minimaxMove(){
        if (board.getPlayer()==Board.RED) {
            this.allPossibleMoves = this.board.getAllLegalMovesForSide(Board.RED);
            if (allPossibleMoves.size()>0)
                this.randMove = randPlayer.getRandMove(this.allPossibleMoves);
            else {winner = Board.BLACK; return null;}
            this.aiMove = board.makeMove(this.randMove);
        }
        else {
            this.aiMove = board.makeMove(this.randMove);
        }
        this.boardState = this.board.getBoardState();
        setWinner();
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

   /* public char[][] getBoardState() {
        board = new Board(Board.RED);
        setBoardState(board.getBoardState());
        return boardState;
    }*/

    public void setBoardState(char[][] boardState) {
        this.boardState = boardState;
    }

    public char[][] getBoardState() {
        return boardState;
    }

    public void restartGame(){
        board = new Board(Board.RED);
    }

    public Move getAiMove() {
        return aiMove;
    }
}