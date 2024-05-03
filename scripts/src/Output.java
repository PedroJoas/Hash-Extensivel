import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    private String path = "scripts/IO/out.txt";
    private boolean arquivoCriado = false; // Variável para controlar se o arquivo já foi criado
    BufferedWriter writer;

    public Output(){
        try {
            // Verificar se o arquivo existe e, se não existir, criá-lo
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                arquivoCriado = false;

            }
        } catch (IOException e) {
            System.out.println("Error: Failed to create file" + e.getMessage());
        }

    }
    public void writeFile(String globalDepth, String line) {
        try {
            if (!arquivoCriado) {
                // Se o arquivo não foi criado, escreva a profundidade uma vez
                writer = new BufferedWriter(new FileWriter(path));
                writer.write("PG/" + globalDepth);
                writer.newLine();
                arquivoCriado = true; // Marque que o arquivo foi criado
            } else {
                // Se o arquivo já foi criado, abra-o em modo de anexação
                writer = new BufferedWriter(new FileWriter(path, true));
            }
            
            // Escrever outras linhas no arquivo
            writer.write(line);
            writer.newLine();
            
            // Fechar o BufferedWriter após a escrita
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to write to file" + e.getMessage());
        } 
    }

    }


