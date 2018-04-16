import java.util.ArrayList;

public class aiMove {
    private char player;
    private  char oppPlayer;
    private Tree decisionTree;
    private Move lastMove;


    /**
     * Creates a new aiMove with the type of its pieces
     * @param player the type of the pieces the ai will move
     */
    public aiMove(char player){
        this.player = player;
        if (player == Board.RED)
            oppPlayer = Board.BLACK;
        else
            oppPlayer = Board.RED;
    }

    /**
     * Picks its move based on all possible moves
     * @param board the current state of the board
     * @return the move picked bu the ai
     */
    public Move getAIMove(Board board){
        decisionTree = makeDescisionTree(board);
        lastMove = pickMove();
        return lastMove;
    }

    /**
     *@return the color of the ai
     */
    public char getPlayer(){
        return player;
    }
    /**
     * Creates a tree with a height of four that has all possible moves
     * for the next three moves of the game
     * @param board the board that the tree will be based on
     * @return a tree with all possible moves
     */
    private Tree makeDescisionTree(Board board) {
        Tree mainTree = new Tree(board,null,score(board));
        ArrayList<Move> moves;
        //Handles multiple jumps
        if (board.isJumped()){
            moves = board.getJumps(lastMove.movRow, lastMove.movCol);
        }else
            moves=board.getAllLegalMovesForSide(player);

    for (Move move:moves){
        //make second row
        Board temp = copyBoard(board);
        temp.movePiece(move);
        temp.handleJump(move);
        Tree firstLayer = new Tree(temp,move,score(temp));
        ArrayList<Move> secondMoves = temp.getAllLegalMovesForSide(oppPlayer);

        for (Move sMove: secondMoves){
            //Make third row
            Board temp2 = copyBoard(temp);
            temp2.movePiece(sMove);
            temp2.handleJump(sMove);
            Tree secondLayer = new Tree(temp2,sMove,score(temp2));
            ArrayList<Move> thirdMoves = temp2.getAllLegalMovesForSide(player);

            for (Move tMove: thirdMoves) {
                //Make third row
                Board temp3 = copyBoard(temp2);
                temp3.movePiece(tMove);
                temp3.handleJump(tMove);

                secondLayer.addChild(new Tree(temp3,tMove,score(temp3)));
            }
            firstLayer.addChild(secondLayer);
        }
        mainTree.addChild(firstLayer);
    }
    return mainTree;
    }

    /**
     * Picks the move based on minimax
     * @return the move that was selected
     */
    //Можна зробити через рекурсію і додати alpha-beta pruning
    private Move pickMove() {
        int max = -13;
        int index = 0;
        for (int i = 0; i < decisionTree.getNumChildren(); i++) {
            Tree child = decisionTree.getChild(i);
            int smin = 13;
            //Find the max leaf
            for (Tree sChild : child.getChildren()){
                int tMax = -13;
                for(Tree tChild : sChild.getChildren()){
                    if (tChild.getScore()>=tMax)
                        tMax=tChild.getScore();
                }
                sChild.setScore(tMax);
                //Find the min on the third level
                if (sChild.getScore()<=smin)
                    smin=sChild.getScore();
            }
            child.setScore(smin);
            //Find the max on the second layer and save the index
            if (child.getScore()>=max){
                max=child.getScore();
                index=i;
            }
        }
        return decisionTree.getChild(index).getMove();
    }



    /**
     * Scores the given board based on a wighted system
     * @param board the board that will be scored
     * @return the score of the given board
     */
    private int score(Board board) {
        if (player ==board.RED)
            return board.getRedWeightedScore()-board.getBlackWeightedScore();
        else
            return board.getBlackWeightedScore()-board.getRedWeightedScore();
    }

    /**
     * Creates a new board with the same information as the given board
     * @param board the board that will be copied
     * @return a copy of the given board
     */
    private Board copyBoard(Board board){
        char[][] player = new char[8][8];
        for (int row= 0; row <8; row++){
            for (int col = 0; col<8;col++){
                player[row][col] = board.getInfoAtPosition(row,col);
            }
        }
        return new Board(player,board.getNumRed(),board.getNumBlack(),board.getNumRedQuin(),board.getNumBlackQuin() );
    }



}

