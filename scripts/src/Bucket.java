import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bucket {
    
    private int localDepth;
    private int numEntries;
    private String bucketFile;
    static final private String DIRNAME = "scripts/buckets";
    private HashExtensible hashmap;
    private String pathBucketFile = DIRNAME + "/";
    BufferedWriter writer;
    
    public Bucket() {
        this.hashmap = new HashExtensible();
        this.localDepth = 2;
    }

    public int increaseLocalDepth() {
        int newLocalDepth = localDepth + 1;
        
        return newLocalDepth;
    }

    

    public void setBucketFile(String hashIndex) {
        this.bucketFile = "bucket_"+bucketFile+".txt";
    }

    public int getLocalDepth(String hashIndex) {
        // Pegar primeira linha do arquivo bucket
        this.bucketFile = "bucket_" + hashIndex + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(pathBucketFile))) {
            this.localDepth = Integer.parseInt(reader.readLine().split("/")[1]);
        } catch (IOException e) {
            System.out.println("Error: IOException " + e.getMessage());
        }

        return localDepth;
    }

    public void setNumEntries(int numEntries){
        this.numEntries = numEntries;
    }
    public int getNumEntries(String hashIndex) {
        this.bucketFile = String.format("bucket_%d.txt", hashIndex);
        //  A primeira linha do arquivo bucket seria a profundidade local
        /*Exemplo
         * PL/2
         * "1,158,2020"
        */
        try{
            BufferedReader reader = new BufferedReader(new FileReader(pathBucketFile));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            this.numEntries = lines;
            reader.close();
            
        }catch(FileNotFoundException e){
            System.out.println("Error: File not found  "+ e.getMessage());
        }catch(IOException e){
            System.out.println("Error: IO Exception " + e.getMessage());
    }
        
        return numEntries;
    }
   
    public String getBucketFile() {
        return bucketFile;
    }
    public static String getDirname() {
        return DIRNAME;
    }
    
    public int search(String hashIndex, String anoCSV) throws IOException{
        // Usar a função hash para saber o indice, após isso
            String path = pathBucketFile+"/bucket_"+hashIndex+".txt";

        
            BufferedReader reader = new BufferedReader(new FileReader(path));
            
            String line = reader.readLine(); // Primeira linha tem a profundidade global
            ArrayList<String> tuples = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String ano = line.split(",")[2];
                if(ano.equals(anoCSV)){
                    tuples.add(line);
                }
            }

            return tuples.size();
        
    }  

    // tuple = linhas do compras.csv
    
    public void insert(String tuple, String hashIndex) throws IOException{
        
        String path = pathBucketFile+"bucket_"+hashIndex+".txt";

        try (BufferedReader file = new BufferedReader(new FileReader(path))) {
            if ((file.readLine() == null)) {

                writer = new BufferedWriter(new FileWriter(path));
                writer.write("PL/" + localDepth);
                writer.newLine();
            }
        }
            writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(tuple);
            writer.newLine();

        // Caso 2.2: exista, mas esteja cheio
        // Se caso 2.2 seja feito é necessário aumentar a profundidade global 
    }

    public void remove(String hashIndex, String ano){
        // Após a remoção verificar o numero de valores dentro do bucket
        // Caso esteja vazio, apagar arquivo do bucket e dependendo diminuir profundidade global
    }

    
    // Verificar as duplicatas dentro do bucket
    public void verificationDuplicates(String bucketFile, String tuple){
        // Verificar se os valores adicionandos já estão no bucket

    }
    public void redistributionBucket(String bucketDuplicated, String newBucket){
        // redistribuir valores dentro do bucket

    }

    private void identifyBucket(String hashIndex){
        String bucketFilepath = String.format("",hashIndex, null);
    }
}
