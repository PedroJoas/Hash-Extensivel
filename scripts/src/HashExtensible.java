
public class HashExtensible {
    
    public String HashFunction(String ano, int globalDepth) {
        int hashValue = (int) (Integer.parseInt(ano) % Math.pow(2, globalDepth));
        
        // Converter o valor do hash para uma representação binária
        String hashBinario = Integer.toBinaryString(hashValue);
        
        // Verificar se o tamanho da string é 1 e adicionar um zero à esquerda, se necessário
        if (hashBinario.length() == 1) {
            hashBinario = "0" + hashBinario;
        }
        
        return hashBinario;
    }

    
}
