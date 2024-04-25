import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("scripts/data/compras.csv"));

        String line = reader.readLine();
        
        while(line != null){
            System.out.println(line);
            line =  reader.readLine();
        }

        reader.close();
    }
}
 
