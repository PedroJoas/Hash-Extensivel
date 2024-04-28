import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bucket {
    private int LocalDepth;
    private int numEntries;
    private String filename;
    static final private String DIRNAME = "scripts/buckets";
    private HashExtensible hashmap;
    private String indexesPath = "scripts/data/indexes.txt";
    
    
    public Bucket() {
        this.hashmap = new HashExtensible();

        // Verificar dentro do indexes.txt se o índice já existe
        try {
            File fileIndex = new File(indexesPath);
            if (!fileIndex.exists()) { // Verificar se o arquivo existe e, se não existir, criá-lo
                fileIndex.createNewFile();
            } 
        } catch (IOException e) {
            System.out.println("Error: IOException " + e.getMessage());
        }
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

    // tuple = linhas do compras.csv
    public void insert(String tuple, String hashIndex){
        //String path = "scripts/data/indexes.txt";
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(indexesPath, true));

            String line;

            if (verificationBucketExists(hashIndex)){
                // Verificar tamanho do bucket
                // Caso 2.1: exista e só inserir
                // Caso 2.2: exista, mas esteja cheio
                // Se caso 2.2 seja feito é necessário aumentar a profundidade global
                
                
            } else {

                // Caso 1: não exista devo criar o arquivo bucket com o novo indice
                createBucketFile(hashIndex);
                writer.write(String.format("%d,bucket_%d.txt", hashIndex, hashIndex)); // Adicionando a informacao no indexes.txt
                insert(tuple, hashIndex);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to create file" + e.getMessage());
        }
        
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

    private void duplicateBucket(){
        hashmap.setGlobalDepth(hashmap.getGlobalDepth() + 1);
    }

    private boolean verificationBucketExists(String hashIndex){
        try
        {

            BufferedReader reader = new BufferedReader(new FileReader(indexesPath));

            String line;

            while((line = reader.readLine()) != null){
                if(line.split(",")[0].equals(hashIndex)){
                    return true;
                }else{
                    return false;
                }
        }
        } catch (FileNotFoundException e){
            System.out.println("Error: File not found " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error: IOException " + e.getMessage());
        }

        return false;
    }

    private void createBucketFile(String hashIndex){
        String pathBucket = String.format("%d/bucket_%d.txt", DIRNAME, hashIndex);
        try {
            File bucketFile = new File(pathBucket);
            if (!bucketFile.exists()) {
                bucketFile.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Error: DIR not found");
        }
    }
    
}
