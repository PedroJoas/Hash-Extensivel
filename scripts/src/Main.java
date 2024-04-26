
public class Main {
    public static void main(String[] args) throws Exception {
       Leitor reader = new Leitor();


    while(reader.readFileIN() != null)
       { if(reader.readFileIN() == "REM"){
            System.out.println("Operação de remoção");
        } else if (reader.readFileIN() == "INC"){
            System.out.println("Operação de inserção");
        } else {
            System.out.println("Operação de busca");
        }}
       
    }
}
 
