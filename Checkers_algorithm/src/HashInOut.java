import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class HashInOut {
    public static void main(String[] args){
        HashMap<String, char[][]> allBoards = new HashMap<>();
        char[][] board;
        String hash = "a2740c59-9ac2-3970-a112-11acbae00a55";
        Game game;
        try {
            allBoards = readHashMapFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (allBoards.get(hash)!=null){
            board = allBoards.get(hash);
            char player = Board.BLACK;
            game = new Game(board,player);
            board = game.rightMove();
            String boardAsString = Arrays.toString(board);
            byte[] boardsAsBytes = boardAsString.getBytes();
            String boardsAsBS = boardsAsBytes.toString();
            System.out.println(boardsAsBS);
            if (allBoards.get(boardsAsBS)==null)
                allBoards.put(boardsAsBS, board);
        }else
            System.out.println("UNKNOWN HASH");
    }


    public static void saveHashMapToJson(HashMap allBoards) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("HashMapForBoards(BytesHash).json");
        objectMapper.writeValue(file,allBoards);
    }


    public static HashMap readHashMapFromJson()throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("HashMapForBoards(BytesHash).json");
        TypeReference<HashMap<String,char[][]>> typeRef
                = new TypeReference<HashMap<String, char[][]>>() {};
        HashMap<String,char[][]> newHash= mapper.readValue(file,typeRef);
        return newHash;
    }

}
