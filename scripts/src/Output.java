import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    private String path;
    BufferedWriter writer;

    public void writeFile(String globalDepth, String operation, String key){
        path = "scripts/data/out.txt";

        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write("PG/"+globalDepth);
            // Fazer com que operation ao final receba "" para que a verificao esteja correta
            while(operation != ""){
                // lembrar de puxar o localDepth
                writer.newLine();
                writer.write(operation+":"+key+"/"+globalDepth);
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e){

        }

    }

}
