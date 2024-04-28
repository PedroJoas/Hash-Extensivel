
public class HashExtensible {
    private int GlobalDepth;
    private Bucket bucket = new Bucket();

    public int getGlobalDepth() {
        return GlobalDepth;
    }

    public void setGlobalDepth(int globalDepth) {
        GlobalDepth = globalDepth;
    }
    
    public String HashFunction(String ano) {
        int hashValue = (int) (Integer.parseInt(ano) % Math.pow(2, GlobalDepth));
        
        // Converter o valor do hash para uma representação binária
        String hashBinario = Integer.toBinaryString(hashValue);
        
        // Verificar se o tamanho da string é 1 e adicionar um zero à esquerda, se necessário
        if (hashBinario.length() == 1) {
            hashBinario = "0" + hashBinario;
        }
        
        return hashBinario;
    }

    public void insert(String tuple, String hashIndex){
        //Chamar insert de bucket
        bucket.insert(tuple, hashIndex);
        System.out.println(tuple + " Adicionada");
    }
    

    
}
