
public class HashExtensible {
    private int GlobalDepth;

    public int getGlobalDepth() {
        return GlobalDepth;
    }

    public void setGlobalDepth(int globalDepth) {
        GlobalDepth = globalDepth;
    }
    
    public String HashFunction(int ano){
        int hashValue = (int) (ano % Math.pow(2, GlobalDepth));
        return Integer.toBinaryString(hashValue);
    }

    
}
