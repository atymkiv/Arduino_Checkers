import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {
    private Board node;
    private Move move;
    private int score;
    private ArrayList<Tree>children;

    /**
     * Creates a new tree with node as the head, a move, a score, and children
     * @param node the head of the tree
     * @param move the move associated with the tree
     * @param score the score of the tree
     * @param children the children of the node
     */
    public Tree(Board node, Move move, int score, Tree ...children){
        this.node = node;
        this.children = new ArrayList<>(Arrays.asList(children));
        this.score = score;
        this.move = move;
    }

    /**
     * Adds a child to the tree
     * @param child the tree that will be added  to the children
     */
    public void addChild(Tree child) {
        children.add(child);
    }

    /**
     *
     * @return the numver of children the tree as
     */
    public int getNumChildren() {
        return  children.size();
    }

    /**
     * The child of the tree at given index
     * @param index the chosen index
     * @return the child at the index
     */
    public Tree getChild(int index) {
        return children.get(index);
    }

    /**
     *
     * @return the tree's children
     */
    public List<Tree> getChildren() {
        return children;
    }

    /**
     *
     * @return the score of the tree
     */
    public int getScore() {
        return score;
    }

    /**
     * Changes the tree's score
     * @param newVal the new score of the tree
     */
    public void setScore(int newVal) {
        score = newVal;
    }

    /**
     *
     * @return the move of the tree
     */
    public Move getMove() {
        return move;
    }
}
