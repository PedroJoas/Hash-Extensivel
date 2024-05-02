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

    private int numMaxBuckets = (int) Math.pow(2, globalDepth);
    private Bucket bucket = new Bucket();
    private HashExtensible hash;
    private String indexesPath = "scripts/data/indexes.txt";

    public Diretorio(){

        try {
            File fileIndex = new File(indexesPath);
            if (!fileIndex.exists()) { // Verificar se o arquivo existe e, se não existir, criá-lo
                fileIndex.createNewFile();
            } 
        } catch (IOException e) {
            System.out.println("Error: IOException " + e.getMessage());
        }
    }

    public int getGlobalDepth() {
        return globalDepth;
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
                    }else{
                        return false;
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

    public void insertIndex(String ano) throws IOException{
        String hashIndex = hash.HashFunction(ano, globalDepth);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(indexesPath, true))) {

                try {
                    writer.write(String.format("%d,bucket_%d.txt", hashIndex, hashIndex));
                    createBucketFile(hashIndex);
                } catch (IOException e) {
                    
                    System.out.println("Error: IOException " + e.getMessage());
                }  
            }
        } 

    
    private void createBucketFile(String ano){
        String hashIndex = hash.HashFunction(ano, globalDepth);
        String path = String.format("/bucket_%d.txt", dirBuckets, hashIndex);

        try {
            File bucketFile = new File(path);
            if (!bucketFile.exists()) {
                bucketFile.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Error: DIR not found");
        }
    }

    private boolean verificationBucketFull(String hashIndex){
        
        if((bucket.getNumEntries(hashIndex) > Math.pow(2, bucket.getLocalDepth(hashIndex)))){
            duplicateDirectory(hashIndex);   
        }
        
        return true;
    }

    private void duplicateDirectory(String hashIndex){
        increaseGlobalDepth();
    }
    public void insert(String tuple, String hashIndex){}
    public void remove(){}
    public void search(){}
}
