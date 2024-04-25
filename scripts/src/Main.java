import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("scripts/data/compras.csv"));

        String[] line = reader.readLine().split(",");
        Registro tupla;

        while(line != null){
            String pedido = line[0];
            String valor = line[1];
            String ano = line[2];
            tupla = new Registro(pedido, valor, ano);


            System.out.println(tupla.getAno());

            line =  reader.readLine().split(",");
        }

        reader.close();
    }
}
 
