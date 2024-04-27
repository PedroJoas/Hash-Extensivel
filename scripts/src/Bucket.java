
public class Bucket {
    private int LocalDepth;
    private int numEntries;
    private String filename;
    static final private String DIRNAME = "";
    private HashExtensible hashmap;
    
    
    public Bucket(HashExtensible hashmap) {
        this.hashmap = new HashExtensible();
    }

    public void setLocalDepth(int localDepth) {
        LocalDepth = localDepth;
    }

    public void setNumEntries(int numEntries) {
        this.numEntries = numEntries;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public int getLocalDepth() {
        return LocalDepth;
    }
    public int getNumEntries() {
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

    public void insert(String key){
        // Verificar dentro do indexes.txt se o índice já existe
        // Caso 1: não exista devo criar o arquivo bucket com o novo indice
        // Caso 2.1: exista e só inserir
        // Caso 2.2: exista, mas é necessario criar outro bucket pois o outro já esta cheio
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

    
}
