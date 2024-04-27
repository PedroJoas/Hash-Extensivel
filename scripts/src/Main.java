import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {
        Leitor reader = new Leitor();
        HashExtensible hash = new HashExtensible();
        Output output = new Output();

        ArrayList<Object> result = reader.readFileIN();
        ArrayList<String> anos = (ArrayList<String>) result.get(1);

        String PG = (String) result.get(0);
        ArrayList<String> anosHash =  new ArrayList<>();

        hash.setGlobalDepth(Integer.parseInt(PG));

        String line;
        for(String ano : anos){
            String chaveHash = hash.HashFunction(ano);
            line = ano + "" + chaveHash;
            output.writeFile(PG, line);
        }

      
    }
}
 
