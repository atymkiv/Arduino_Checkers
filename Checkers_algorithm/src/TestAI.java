import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestAI {

    public static void main(String[] args) throws IOException {

        final int GAMES = 2000, STEPS = 14;

        Map<String, char[][]> allBoards = new HashMap<>();
        int min_new = 200;
        int collisions = 0;
        int count_of_used = 0;
       int count_of_new = min_new;
        float right = 0;
        Game game = new Game();
        char[][] board;
        char player;
        allBoards = readHashMapFromJson();
        //starting the game and
        while (count_of_new > min_new-1) {
            count_of_new =0;
            count_of_used = 0;
            right = 0;
            for (int i = 0; i < GAMES; i++) {
                game.restartGame();
                board = Board.placePieces();
                for (int j = 0; j < STEPS; j++) {
                    if (j % 2 != 0 && j > 0)
                        player = Board.BLACK;
                    else
                        player = Board.RED;

                    game = new Game(board, player);
                    board = game.rightMove();
                    char[][] boardtoHash = copyBoardToHash(board);

                    if (board == null) {
                        System.out.println("AI WINS");
                        break;
                    }
                    String boardAsString = transformBoardToString(boardtoHash);
                    if (allBoards.get(boardAsString) == null) {
                        allBoards.put(boardAsString, boardtoHash);
                        count_of_new++;
                    } else {
                        String boardAsStringFromHash = transformBoardToString(allBoards.get(boardAsString));
                        if (!boardAsString.equals(boardAsStringFromHash)) {
                            printBoard(board);
                            printBoard(allBoards.get(boardAsString));
                            collisions++;

                        } else {

                            right++;
                        }
                        count_of_used++;
                    }
                }
            }
            count_of_new++;
            System.out.println("Нових бордів: " + count_of_new);
            System.out.println("Юзаних: " + count_of_used);
            float true_perc = right / count_of_used * 100;
            float collisions_perc = collisions/count_of_used*100;
            System.out.println("Колізій: " + collisions_perc + "%");
            System.out.println("Правильних: " + true_perc + "%");

        }
            try {
                saveHashMapToJson(allBoards);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    /*public static String sha1(String input) {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Logger.getLogger(TestAI.class.getName()).log(Level.SEVERE, null, e);
        }
        return sha1;
    }*/


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


    public static void saveHashMapToJson(Map allBoards) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("HashMapForBoards(StringHash).json");
        objectMapper.writeValue(file, allBoards);
    }


    public static HashMap readHashMapFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("HashMapForBoards(StringHash).json");
        TypeReference<Map<String, char[][]>> typeRef
                = new TypeReference<Map<String, char[][]>>() {
        };
        HashMap<String, char[][]> newHash = mapper.readValue(file, typeRef);
        return newHash;
    }
    public static String transformBoardToString(char[][] board){
        char element;
        boolean newLine = false;
        String boardAsString = "";
        for (int i = 0; i <8; i++) {
            for (int j = 0; j <8; j++) {
                element = board[i][j];
                switch (element){
                    case ' ':
                        boardAsString += "  ";
                        break;
                    case 'R':
                        boardAsString+='R';
                        break;
                    case 'B':
                        boardAsString+='B';
                        break;
                }
            }
            boardAsString+="\n";
        }
        return boardAsString;
    }
    public static char[][] copyBoardToHash(char[][] board){
        char[][] player = new char[8][8];
        for (int row= 0; row <8; row++){
            for (int col = 0; col<8;col++){
                player[row][col] = board[row][col];
            }
        }
        return player;
    }

}

