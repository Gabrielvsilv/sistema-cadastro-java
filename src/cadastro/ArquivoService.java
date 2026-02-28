package cadastro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArquivoService {

    private static final String CAMINHO = "lista_pessoal.txt";

    public void salvarDados(List<Pessoa> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO))) {
            for (Pessoa p : lista) {
                String linha = p.getNome() + ";" + p.getIdade() + ";" + p.getCpf() + ";" +
                               p.getLogradouro() + ";" + p.getBairro() + ";" + 
                               p.getLocalidade() + ";" + p.getUf();
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("💾 Backup salvo em TXT!");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar TXT: " + e.getMessage());
        }
    }
}