import java.util.ArrayList;
import java.util.Random;

public class RandPlayer {

    private Move randMove;

public RandPlayer(){}
    /**
     * Getting random move from all possible moves for a side
     * @return move for RandomPlayer
     */
    public Move getRandMove(ArrayList<Move>allPossibleMoves) {

        Random rn = new Random();
        int range = allPossibleMoves.size();
        if (range < 1) {
            randMove = allPossibleMoves.get(1);
            return randMove;
        }
        this.randMove = allPossibleMoves.get(rn.nextInt(range));
        return randMove;
    }
}
