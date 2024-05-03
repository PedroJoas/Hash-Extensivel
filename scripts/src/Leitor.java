import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Leitor {
    private BufferedReader reader;
    private String path;
    

    public ArrayList<Object> readFileIN(){
        path =  "scripts/IO/in.txt";
        
        ArrayList<Object> array = new ArrayList<>(); // Armazena os anos 

        try { 

            ArrayList<String> lines = new ArrayList<>(); // Armazena os anos 

            reader = new BufferedReader(new FileReader(path));
            
            String depth = reader.readLine().split("/")[1];
            String line;
            
            while ((line = reader.readLine()) != null) {
                String ano = line.strip();
                lines.add(ano);
            }
        
            //System.out.println("Profundidade: " + depth);
            array.add(depth);
            array.add(lines);

            reader.close();
            return array;
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return array;
        } catch (IOException e){
            System.err.println("Error of I/O: " + e.getMessage());
            return array;
        }

        
    }

    public ArrayList<String> readCSV(String ano) {
    path = "scripts/data/compras.csv";
    ArrayList<String> lines = new ArrayList<>();
    try {
        reader = new BufferedReader(new FileReader(path));

        String line;
        while ((line = reader.readLine()) != null) {
            if(line.split(",")[2].equals(ano)){
                lines.add(line);
            }
        }

        reader.close();
        return lines;
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
        return lines;
    } catch (IOException e){
        System.err.println("Error of I/O: " + e.getMessage());
        return lines;
    }
}

}
