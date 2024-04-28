import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {
        Leitor reader = new Leitor();
        HashExtensible hash = new HashExtensible();
        Output output = new Output();

        ArrayList<Object> result = reader.readFileIN();

        ArrayList<String> commands = (ArrayList<String>) result.get(1);

        String PG = (String) result.get(0);

        hash.setGlobalDepth(Integer.parseInt(PG));

        String line;
        String ano;
        String operation;
        String chaveHash;
        
        for(String command : commands){

            operation = command.split(":")[0];
            ano = command.split(":")[1];
            chaveHash = hash.HashFunction(ano);
            if(operation.equals("INC")){
                ArrayList<String> tuples = new ArrayList<>();
                tuples = reader.readCSV(ano);
                for(String tuple : tuples){
                    hash.insert(tuple, chaveHash);
                }
            } else if (operation.equals("BUS")){ 
                System.out.println("Buscar " + ano + " no bucket_" + chaveHash);
            }else{
                System.out.println("Remover " + ano + " no bucket_" + chaveHash);
            }

            
            line = command + ":" + chaveHash;
            output.writeFile(PG, line);
        }

      
    }
}
 
