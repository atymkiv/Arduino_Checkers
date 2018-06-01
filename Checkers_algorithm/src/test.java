import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class test {
    public static void main (String[]args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("HashMapForBoards(StringHash).json");
        TypeReference<HashMap<String,char[][]>> typeRef
                = new TypeReference<HashMap<String, char[][]>>() {};
        HashMap<String,char[][]> newHash= mapper.readValue(file,typeRef);
        int count = 1;
       for (HashMap.Entry<String,char[][]> entry : newHash.entrySet())
        {

        }

        System.out.println(count);
    }
    static void printBoard(char board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
