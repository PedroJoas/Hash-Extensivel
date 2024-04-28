import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bucket {
    private int LocalDepth;
    private int numEntries;
    private String filename;
    static final private String DIRNAME = "scripts/buckets";
    private HashExtensible hashmap;
    
    
    public Bucket() {
        this.hashmap = new HashExtensible();
    }

    public void setLocalDepth(int localDepth) {
        this.LocalDepth = localDepth;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public int getLocalDepth() {
        return LocalDepth;
    }
    public int getNumEntries(String filename) {
        return numEntries;
    }
    public String getFilename() {
        return filename;
    }
    public static String getDirname() {
        return DIRNAME;
    }
    
    public void search(String key){
        // Usar a função hash para saber o indice, após isso
    }  

    public void insert(String tuple, String hashIndex){
        String path = "scripts/data/indexes.txt";
        // Verificar dentro do indexes.txt se o índice já existe
        try {
            // Verificar se o arquivo existe e, se não existir, criá-lo
            File fileIndex = new File(path);
            if (!fileIndex.exists()) {
                fileIndex.createNewFile();
            }  // Quando eu criar para onde eu vou?
            BufferedReader reader = new BufferedReader(new FileReader(path));
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            String line;
            while((line = reader.readLine()) !=  null){
                if(line.equals(hashIndex)){
                    if(verificationBucketExists(hashIndex)){
                        
                    }
                    break;
                    }
                
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to create file" + e.getMessage());
        }
        // Caso 1: não exista devo criar o arquivo bucket com o novo indice
        // Caso 2.1: exista e só inserir
        // Caso 2.2: exista, mas esteja cheio
        // Se caso 2.2 seja feito é necessário aumentar a profundidade global 
    }

    public void remove(String key){
        // Após a remoção verificar o numero de valores dentro do bucket
        // Caso esteja vazio, apagar arquivo do bucket e dependendo diminuir profundidade global
    }

    public boolean verificationBucketFull(int key){
        return true;
    }

    public void duplicateBucket(){
        hashmap.setGlobalDepth(hashmap.getGlobalDepth() + 1);
    }

    public boolean verificationBucketExists(String hashIndex){
        return true;
    }
    
}
