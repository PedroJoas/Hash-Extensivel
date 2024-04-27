import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    private String path = "scripts/data/out.txt";;
    BufferedWriter writer;

    public Output(){
        File file = new File(path);

    }
    public void writeFile(String globalDepth, String line){
        
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write("PG/"+globalDepth);
            // Fazer com que operation ao final receba "" para que a verificao esteja correta
            
            // lembrar de puxar o localDepth
            writer.newLine();
            writer.write(line);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found" + e.getMessage());
        } catch (IOException e){
            System.out.println("Error: I/O exception" + e.getMessage());
        }

    }

}
