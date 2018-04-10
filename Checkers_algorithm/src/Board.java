import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Board  {
    private char[][] boardState = new char[8][8];
    private char player = 'R';
    private ArrayList<Move>moves;
    private boolean jumped = false;
    private int redCount = 12;//number of red pieces on the board
    private int blackCount = 12;
    private int blackQuins = 0;
    private int redQuins = 0;
    private char winner = '\u0000';
    private boolean initial = true;
    private aiMove ai;
    private boolean tie = false;


    public Board(char player){
        this.player = player;
        if (this.player == 'B')
            ai = new aiMove('R');
        else if (this.player=='R')
            ai=new aiMove('B');
        this.boardState = placePieces();
    }
    /**
     * Creates a new board with an ai player;
     */
    public Board(){
        this.ai = new aiMove('B');
    }

    /**
     * Creates a new board with pieces in the
     * same locations boarColor and with numRed
     * red pieces and numBlack black pieces
     * @param boardColor the location of the pieces
     * @param numRed the number of red pieces
     * @param numBlack the number of black pieces
     */
    public Board(char[][]boardColor, int numRed, int numBlack){
        this();
        this.boardState = boardColor;
        this.redCount = numRed;
        this.blackCount = numBlack;
    }

    /**
     * Creates a new board with pieces in the
     * same locations as boardColor and with numRed
     * red pieces, numBlack black pieces, numRedQuin
     * red quin, and numBlackQuin black quins
     * @param boardState the location of the pieces
     * @param numRed the number of red pieces
     * @param numBlack the number of black pieces
     * @param numBlackQuin number of black quin
     * @param numRedQuin number of red quin
     */
    public Board(char[][] boardState, int numRed,int numBlack,int numBlackQuin, int numRedQuin){
        this(boardState,numRed,numBlack);
        this.redQuins = numRedQuin;
        this.blackQuins = numBlackQuin;
    }


    /**
     *
     * @return the weighted score of the board
     */
    public int getRedWeightedScore() {
        return redCount-redQuins +(3*redQuins);
    }

    /**
     *
     * @return the weighted score of the board
     */
    public int getBlackWeightedScore() {
        return  blackCount-blackQuins+(3*blackQuins);
    }

    /**
     *
     * @return whether or not the last move was a jump
     */
    public boolean isJumped() {
        return jumped;
    }

    /**
     * Get all legal jumps from the given location
     * @param row the row of the piece
     * @param col the col of the piece
     * @return an array of all legal jumps
     */
    public ArrayList<Move> getJumps(int row, int col) {
        ArrayList<Move> jumps = new ArrayList<>();
        char chosenPiece = getInfoAtPosition(row,col);

        //get red 'R' jumps
        if (player == 'R'){
            if (chosenPiece == 'R') {
                if (chosenPiece == 'R' || chosenPiece == 'Q') {
                    if(getInfoAtPosition(row+1,col+1)=='B'||
                            getInfoAtPosition(row+1,col+1)=='K'){
                        if (getInfoAtPosition(row+2,col+2)==' '){
                            jumps.add(new Move(row,col,row+2,col+2));
                        }
                        if (getInfoAtPosition(row+1,col-1)=='B'||
                                getInfoAtPosition(row+1,col-1)=='K'){
                            if (getInfoAtPosition(row+2,col-2)==' '){
                                jumps.add(new Move(row,col,row-2,col-2));
                            }
                        }
                    }
                }
                //if it is a quine - jumps along the diagonal
                if (chosenPiece == 'Q'){
                    if (getInfoAtPosition(row+1,col+1)=='R'||
                            getInfoAtPosition(row+1,col+1)== 'K'){
                        if (getInfoAtPosition(row+2,col+2)==' '){
                            jumps.add(new Move(row, col,row+2,col+2 ));
                        }
                    }
                    if (getInfoAtPosition(row+1,col-1)=='R'||
                            getInfoAtPosition(row+1,col-1)== 'K') {
                        if (getInfoAtPosition(row + 2, col - 2) == ' ') {
                            jumps.add(new Move(row, col, row + 2, col - 2));
                        }
                    }
                }
            }
        }
        return jumps;
    }

    /**
     * Retruns the type of piece at the given position
     * @param row a row
     * @param col a column
     * @return a color
     */
    public char getInfoAtPosition(int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7)
            return '\u0000';
        return boardState[row][col];
    }

    /**
     * Get all possible moves for the given side
     * @param side the side whose moves will be found
     * @return all legal moves for the side
     */
    public ArrayList<Move> getAllLegalMovesForSide(char side) {
        ArrayList<Move> moves= new ArrayList<>();
        int count = 0;

        //loop through board and get moves at each location
        for (int row = 0;row<8;row++){
            for (int col=0; col<8; col++){
                char currPosition = getInfoAtPosition(row,col);
                if (currPosition==side){
                    moves.addAll(getLegalMovesForSideAtPosition(side,row,col));
                    count++;
                }

                //Get quin moves
                if (side=='Q') {
                    moves.addAll(getLegalMovesForSideAtPosition(side, row, col));
                    count++;
                } else if (side=='K'){
                    moves.addAll(getLegalMovesForSideAtPosition(side,row,col));
                    count++;
                }

                //Stop if all pieces of the color have been found
                if (count==12){
                    return moves;
                }
            }
        }
        return moves;
    }

    /**
     * Get all legal moves for given checker at the given location
     * @param side the side whose moves will be found
     * @param row a row
     * @param col a column
     * @return moves all legal moves at position row,col for checker
     */
    private ArrayList<Move> getLegalMovesForSideAtPosition(char side, int row, int col) {
        char chosenPiece = getInfoAtPosition(row, col);
        ArrayList<Move>moves= new ArrayList<>();

        //Get red moves
        if (side=='R'||side=='Q'){
            if (chosenPiece=='R'||chosenPiece=='Q'){
                if (getInfoAtPosition(row+1,col+1)==' ')
                    moves.add(new Move(row,col,row+1,col+1));
                if (getInfoAtPosition(row+1,col-1)==' ')
                    moves.add(new Move(row,col,row+1,col-1));
            }
            if (chosenPiece=='Q'){ //ЗМІНИТИ ДЛЯ ХОДІВ ПО ДІАГОНАЛІ
                if (getInfoAtPosition(row-1,col+1)==' ')
                    moves.add(new Move(row,col,row-1,col-1));
                if (getInfoAtPosition(row-1, col-1)==' ')
                    moves.add(new Move(row,col, row-1, col-1));
            }
        }//get Black moves
        else if (side=='B'||side=='K'){
            if (chosenPiece=='B'||chosenPiece=='K'){
                if (getInfoAtPosition(row-1,col+1)==' ')
                    moves.add(new Move(row,col,row-1, col+1));
                if (getInfoAtPosition(row-1, col-1)==' ')
                    moves.add(new Move(row,col,row-1,col-1));
            }
            if (chosenPiece=='K'){//ЗМІНИТИ ДЛЯ ХОДІВ ПО ДІАГОНАЛІ
                if (getInfoAtPosition(row+1,col+1)==' ')
                    moves.add(new Move(row,col,row+1,col+1));
                if (getInfoAtPosition(row+1,col-1)==' ')
                    moves.add(new Move(row,col,row+1,col-1));
            }
        }

        //Add jumps
        ArrayList<Move> jumps = getJumps(row,col);
        moves.addAll(jumps);
        return moves;
    }

    /**
     * Moves a piece
     * @param move the move that will made
     * @return whether of not a quine was made
     */
    public boolean movePiece(Move move) {
        //change location of piece
        char temp = boardState[move.currRow][move.currCol];
        boardState[move.currRow][move.currCol]=' ';

        //handles quine
        if (player == 'R'&&move.currRow == 7){
            boardState[move.movRow][move.movCol]='Q';
            redQuins++;
            return true;
        }else if (player=='B'&&move.movRow==0){
            boardState[move.movRow][move.movCol]='K';
            blackQuins++;
            return true;
        }else {
            boardState[move.movRow][move.movCol]=temp;
            return false;
        }
    }

    /**
     * Deletes the piece that was jumped over
     * @param move the move that was made
     */
    public void handleJump(Move move) {
        Pair<Integer, Integer> spaceSkipped = move.getSpaceInBetween();

        //Verifies that jump was made
        if (spaceSkipped.getKey()!=move.currRow && spaceSkipped.getKey()!=move.movRow &&
                spaceSkipped.getValue() != move.movCol && spaceSkipped.getValue() != move.currCol) {
            if (boardState[spaceSkipped.getKey()][spaceSkipped.getValue()] == 'Q')
                redQuins--;
            if (boardState[spaceSkipped.getKey()][spaceSkipped.getValue()] == 'K')
                blackQuins--;
            boardState[spaceSkipped.getKey()][spaceSkipped.getValue()] = ' ';
            jumped = true;
            if (player == 'R')
                blackCount--;
            else
                redCount--;
        }else
            jumped=false;
        }
    /**
     *
     * @return the number of red pieces on the board
     */
    public int getNumRed() {
    return redCount;
    }

    /**
     *
     * @return the number of black pieces on the board
     */
    public int getNumBlack() {
    return blackCount;
    }

    /**
     *
     * @return number of red quins
     */
    public int getNumRedQuin() {
    return redQuins;
    }

    /**
     *
     * @return number of black quines
     */
    public int getNumBlackQuin() {
    return blackQuins;
    }

    /**
     * Places twelve pieces of each side in their
     * initial positions
     */
    public char[][] placePieces(){
        char[][]board = new char[8][8];
        for (int row= 0;row<8;row++){
            for(int col = 0; col<8; col++){
                if (row % 2 != col % 2) {
                    if (row<3)
                        board[row][col]= 'R';
                    else if (row>4)
                        board[row][col] = 'B';
                    else
                        board[row][col] = ' ';
                }else
                    board[row][col] = ' ';
            }
        }
        return board;
    }

    public char[][] getBoardState() {
        return boardState;
    }

    /**
     * setting the boardState
     * @param boardState
     */
    public void setBoardState(char[][]boardState){
        this.boardState = boardState;
    }

    /**
     *Handles moving the piece and updating the board
     */
    public Move makeMove(Move movePl){
        boolean crowned;
        Move aiMove = null;
        if (player != ai.getPlayer()) {
                    crowned = movePiece(movePl);
                    handleJump(movePl);
                    updateBoard(movePl, crowned);
                return null;
        }
        if (player == ai.getPlayer()) {
            aiMove = ai.getAIMove(this);
            crowned = movePiece(aiMove);
            handleJump(aiMove);
            updateBoard(aiMove, crowned);
        }
        return aiMove;
    }

    /**
     *
     * @param move
     * @param crowned
     */
    public void updateBoard(Move move, boolean crowned) {
        // Checks for winner
        if (blackCount == 0) {
            winner = 'R';

            return;
        }
        if (redCount == 0) {
            winner = 'B';
            return;
        }

        // Handle multiple jumps
        if (!crowned && jumped) {
            if (getJumps(move.movRow, move.movCol).isEmpty()) {
                jumped = false;
                if (player =='R')
                    player = 'B';
                else
                    player = 'R';
            }
        } else {
            // Changes player
            jumped = false;
            if (player == 'R') {
                player = 'B';
            } else
                player = 'R';
        }
    }

    public char getWinner() {
        return winner;
    }
}
