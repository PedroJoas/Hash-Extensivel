import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Leitor {
    private BufferedReader reader;
    private String path;
    

    public ArrayList<Object> readFileIN(){
        path =  "scripts/data/in.txt";

        ArrayList<Object> array = new ArrayList<>(); // Armazena os anos 

        try { 

            ArrayList<String> arrayAnos = new ArrayList<>(); // Armazena os anos 

            reader = new BufferedReader(new FileReader(path));
            
            String depth = reader.readLine().split("/")[1];
            String line;
            
            while ((line = reader.readLine()) != null) {
                String ano = line.split(":")[1];
                arrayAnos.add(ano);
            }
        
            //System.out.println("Profundidade: " + depth);
            array.add(depth);
            array.add(arrayAnos);

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

    public boolean readCSV() {
    path = "scripts/data/compras.csv";
    try {
        reader = new BufferedReader(new FileReader(path));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String pedido = fields[0];
            String valor = fields[1];
            String ano = fields[2];
            Registro tupla = new Registro(pedido, valor, ano);

            System.out.println(tupla.getAno());
        }

        reader.close();
        return true;
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
        return false;
    } catch (IOException e){
        System.err.println("Error of I/O: " + e.getMessage());
        return false;
    }
}

}
