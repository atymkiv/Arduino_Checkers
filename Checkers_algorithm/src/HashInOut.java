import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class HashInOut {
    public static void main(String[] args) {
        HashMap<String, char[][]> allBoards = new HashMap<>();
        char[][] board;
        String hash = args[0];
        Game game;
        try {
            allBoards = readHashMapFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (allBoards.get(hash) != null) {
            board = allBoards.get(hash);
            char player = Board.BLACK;
            game = new Game(board, player);
            Move aiMove = game.minimaxMove();
            board = game.getBoardState();
            String boardsAsBS = transformBoardToString(board);
            String move = String.valueOf(aiMove.currRow) + String.valueOf(aiMove.currCol) + String.valueOf(aiMove.movRow) + String.valueOf(aiMove.movCol);
            System.out.println(move);
            if (allBoards.get(boardsAsBS) == null)
                allBoards.put(boardsAsBS, board);
        } else
            System.out.println("UNKNOWN HASH");
    }


    public static void saveHashMapToJson(HashMap allBoards) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("HashMapForBoards(BytesHash).json");
        objectMapper.writeValue(file, allBoards);
    }


    public static HashMap readHashMapFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("HashMapForBoards(BytesHash).json");
        TypeReference<HashMap<String, char[][]>> typeRef
                = new TypeReference<HashMap<String, char[][]>>() {
        };
        HashMap<String, char[][]> newHash = mapper.readValue(file, typeRef);
        return newHash;
    }

    public static String transformBoardToString(char[][] board) {
        char element;
        boolean newLine = false;
        String boardAsString = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                element = board[i][j];
                switch (element) {
                    case ' ':
                        boardAsString += "  ";
                        break;
                    case 'R':
                        boardAsString += 'R';
                        break;
                    case 'B':
                        boardAsString += 'B';
                        break;
                }
            }
            boardAsString += "\n";
        }
        return boardAsString;

    }
}
