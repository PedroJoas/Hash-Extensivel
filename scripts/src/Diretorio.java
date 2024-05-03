import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Diretorio {
    private int globalDepth;
    private String dirBuckets = "scripts/buckets";

    private int numMaxBuckets;

    private Bucket bucket = new Bucket();
    private HashExtensible hash;
    private String indexesPath = "scripts/indexes/indexes.txt";

    public Diretorio(){

        hash = new HashExtensible();
        try {
            File fileIndex = new File(indexesPath);
            if (!fileIndex.exists()) { // Verificar se o arquivo existe e, se não existir, criá-lo
                fileIndex.createNewFile();
                System.out.println("Arquivo indexes criado!");
            } 

        } catch (IOException e) {
            System.out.println("Error: Failed create file " + e.getMessage());
        }
    }

    public int getGlobalDepth() {
        return globalDepth;
    }
    
    public void setGlobalDepth(int globalDepth) {
        this.globalDepth = globalDepth;
    }

    private void increaseGlobalDepth(){
        this.globalDepth++;
    } 
    
    private int countBuckets(){
        File directory = new File(dirBuckets);
        File[] files = directory.listFiles();

        int contFiles = 0;

        if(files != null){
            for(File file : files){
                if (file.isFile()) {
                    contFiles++;
                }
            }
        }
        return contFiles;
    }

    public void insert(String hashIndex){
        
    }

    private boolean verificationBucketExists(String hashIndex){
        try
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(indexesPath))) {
                String line;
                while((line = reader.readLine()) != null){
                    if(line.split(",")[0].equals(hashIndex)){
                        return true;
                    }

      }
            }

        } catch (FileNotFoundException e){
            System.out.println("Error: File not found " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error: IOException " + e.getMessage());
        }

        return false;
    }

    public void insertIndex(String hashIndex) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(indexesPath, true))) {
            try{
                if(!verificationBucketExists(hashIndex)){
                    writer.write(hashIndex + ",bucket_" + hashIndex + ".txt");
                    writer.newLine();
                    createBucketFile(hashIndex);
                }
            } catch (IOException e) {
                System.out.println("Error: IOException " + e.getMessage());
            }
        }
    }
     

    
    private void createBucketFile(String hashIndex){
        String path = String.format("%s/bucket_%s.txt", dirBuckets, hashIndex);
    
        try {
            File bucketFile = new File(path);
            if (!bucketFile.exists()) {
                bucketFile.createNewFile();
            }

        } catch (Exception e) {
            System.out.println("Error: Failed to create bucket file");
            e.printStackTrace();
        }
    }
    

    private boolean verificationDirectoryFull(String hashIndex){
        
        if((countBuckets()+1 > Math.pow(2, globalDepth))){
            duplicateDirectory(hashIndex); 
            return true;  
        }
        
        return false;
    }

    private String duplicateDirectory(String hashIndex){
        // Aumentar a profundidade local do bucket e global
        increaseGlobalDepth();
        bucket.increaseLocalDepth(hashIndex);
        // retornar a nova PG, PL
        // Ajeitar o main para receber essa string e concatene com a outra
        return "DUP_DIR:/"+globalDepth+","+bucket.getLocalDepth(hashIndex);
    }

    
    public void insert(String tuple, String ano) throws IOException{
        String hashIndex = hash.HashFunction(ano, globalDepth);
        // converter para o hash index
        
        // Preciso retornar a PG, PL
        if(!verificationBucketExists(hashIndex)){
            insertIndex(hashIndex);
        }

        bucket.insert(tuple, hashIndex);

    }

    public int remove(String ano){
        String hashIndex = hash.HashFunction(ano, globalDepth);

        int removeLines = bucket.remove(hashIndex, ano);
        return removeLines;
    }
    public int search(String ano) throws IOException{
        String hashIndex = hash.HashFunction(ano, globalDepth);
        int tuples = bucket.search(hashIndex, ano);
        return tuples;
    }

    public int getLocalDepth(String ano){
        String hashIndex = hash.HashFunction(ano, globalDepth);

        int localDepth = bucket.getLocalDepth(hashIndex);
        return localDepth;
    }
}
