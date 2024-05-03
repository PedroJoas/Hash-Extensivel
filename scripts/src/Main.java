import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {
        Leitor reader = new Leitor();
        Output output = new Output();
        Diretorio diretorio = new Diretorio();
        ArrayList<Object> result = reader.readFileIN();

        ArrayList<String> commands = (ArrayList<String>) result.get(1);

        String PG = (String) result.get(0);
        diretorio.setGlobalDepth(Integer.parseInt(PG));

        String line;
        String ano;
        String operation;

        for(String command : commands){

            operation = command.split(":")[0];
            ano = command.split(":")[1];
            //chaveHash = hash.HashFunction(ano, diretorio);
            System.out.println(ano);
            if(operation.equals("INC")){
                ArrayList<String> tuples = new ArrayList<>();
                tuples = reader.readCSV(ano);
                
                for(String tuple : tuples){
                    diretorio.insert(tuple, ano);
                    // Adicionar output
                    line = "INC:" + ano + "/";
                    output.writeFile(PG, line);
                }

            } else if (operation.equals("BUS")){ 
                int numTuples = diretorio.search(ano);
                System.out.println("BUS:"+ano+"/"+numTuples);
            }else{
                System.out.println("Remover " + ano );
            }

            
            
        }

      
    }
}
 
