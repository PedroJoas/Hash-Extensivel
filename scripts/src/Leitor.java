import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leitor {
    private BufferedReader reader;
    private String path;

    public String readFileIN(){
        path =  "scripts/data/in.txt";

        try {

            reader = new BufferedReader(new FileReader(path));

            //String depth = reader.readLine().split("/")[1];

            String line;

            while ((line = reader.readLine()) != null) {
                //System.out.println(line.split(":")[0]);
                return line.split(":")[0];
            }
        
            //System.out.println("Profundidade: " + depth);
            reader.close();
            return "";
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return "";
        } catch (IOException e){
            System.err.println("Error of I/O: " + e.getMessage());
            return "";
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
