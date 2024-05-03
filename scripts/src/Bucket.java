import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public void increaseLocalDepth(String hashIndex) {
        String filePath = pathBucketFile + "bucket_" + hashIndex + ".txt";
        String tempFilePath = "caminho/do/seu/arquivo_temp.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {

            String line;
            int currentLine = 1;

            // Lê e modifica apenas a primeira linha
            while ((line = reader.readLine()) != null) {
                if (currentLine == 1) {
                    // Modifique a primeira linha aqui
                    line = "PL/"+localDepth+1;
                }
                writer.write(line + System.getProperty("line.separator"));
                currentLine++;
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler/escrever no arquivo: " + e.getMessage());
        }

        // Renomear o arquivo temporário para o nome original
        File file = new File(filePath);
        File tempFile = new File(tempFilePath);
        tempFile.renameTo(file);
    }
    

    

    public void setBucketFile(String hashIndex) {
        this.bucketFile = "bucket_"+bucketFile+".txt";
    }

    public int getLocalDepth(String hashIndex) {
        // Pegar primeira linha do arquivo bucket
        String path = pathBucketFile + "bucket_" + hashIndex + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
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
        String path = pathBucketFile + "bucket_" + hashIndex + ".txt";
        //  A primeira linha do arquivo bucket seria a profundidade local
        /*Exemplo
         * PL/2
         * "1,158,2020"
        */
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
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
    
    public void insert(String tuple, String hashIndex) throws IOException {
        String path = pathBucketFile + "bucket_" + hashIndex + ".txt";
        
        BufferedWriter writer = null;
        try (BufferedReader file = new BufferedReader(new FileReader(path))) {
            if ((file.readLine() == null)) {
                writer = new BufferedWriter(new FileWriter(path));
                writer.write("PL/" + localDepth);
                writer.newLine();
            }
            
            if (!verificationDuplicates(hashIndex, tuple)) {
                if (writer == null) {
                    writer = new BufferedWriter(new FileWriter(path, true));
                }
                writer.write(tuple);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error: failed IO in file: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    public int remove(String hashIndex, String ano){
        String path = pathBucketFile+"bucket_"+hashIndex+".txt";
        String pathTempFile = pathBucketFile+"temp_bucket_"+hashIndex+".txt";

        File file = new File(path);
        File tempFile = new File(pathTempFile);
        ArrayList removeLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            // Remover linhas específicas (substitua com suas condições)
            while ((line = reader.readLine()) != null) {
                if (!(line.contains(ano))) {
                    writer.write(line);
                    writer.newLine();
                }else{
                    removeLines.add(line);
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler/escrever o arquivo: " + e.getMessage());
        }
        tempFile.renameTo(file);

        removeBucket(hashIndex);

        return removeLines.size(); // Retornar o número de linhas apagadas
        // Após a remoção verificar o numero de valores dentro do bucket
        // Caso esteja vazio, apagar arquivo do bucket e dependendo diminuir profundidade global
    }

    
    // Verificar as duplicatas dentro do bucket
    public boolean verificationDuplicates(String hashIndex, String tuple) throws FileNotFoundException{
        String path = pathBucketFile+"bucket_"+hashIndex+".txt";
        BufferedReader reader =  new BufferedReader(new FileReader(path));
        String line;
        try {
            while((line = reader.readLine()) != null){
                if(line.equals(tuple)){
                    return true;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public void redistributionBucket(String bucketDuplicated, String newBucket){
        // redistribuir valores dentro do bucket

    }

    
    private void removeBucket(String hashIndex){

        String path = pathBucketFile+"bucket_"+hashIndex+".txt";
        File file = new File(path);
        if(getNumEntries(hashIndex) == 1){
            file.delete();
        }

    }
}
