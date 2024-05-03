    import java.util.ArrayList;

    public class Main {
        @SuppressWarnings("unchecked")
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
                
                if(operation.equals("INC")){
                    ArrayList<String> tuples = new ArrayList<>();
                    tuples = reader.readCSV(ano);
                    
                    for(String tuple : tuples){
                        diretorio.insert(tuple, ano);
                        int localDepthBucket = diretorio.getLocalDepth(ano);
                        int globalDepth = diretorio.getGlobalDepth();

                        line = "INC:" + ano + "/"+globalDepth+","+localDepthBucket;
                        output.writeFile(PG, line);
                    }

                } else if (operation.equals("BUS=")){ 
                    int numTuples = diretorio.search(ano);
                    //System.out.println("BUS:"+ano+"/"+numTuples);
                    line  = "BUS=:"+ano+"/"+numTuples;
                    output.writeFile(PG, line);
                }else{
                    //System.out.println("Remover " + ano );
                    int numLinesRemoved = diretorio.remove(ano);
                    int localDepthBucket = diretorio.getLocalDepth(ano);
                    int globalDepth = diretorio.getGlobalDepth();

                    line  = "REM:"+ano+"/"+numLinesRemoved+","+globalDepth+","+localDepthBucket;
                    output.writeFile(PG, line);
                }   
            }

        }
    }
    
