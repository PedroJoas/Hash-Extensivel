
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
        
    }  

    public void insert(String key){
        
    }

    public void remove(String key){
        
    }

    public boolean verificationBucketFull(int key){
        return true;
    }

    public void duplicateBucket(){
        hashmap.setGlobalDepth(hashmap.getGlobalDepth() + 1);
    }

    
}
