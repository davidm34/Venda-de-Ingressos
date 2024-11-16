package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia as avaliações feitas pelos usuários para eventos.
 * Inclui métodos para ler, adicionar, limpar e salvar avaliações em um arquivo JSON.
 *
 * @author David Neves Dias
 */
public class AvaliacaoManager {

    /** Lista de avaliações gerenciadas pelo AvaliacaoManager. */
    private List<Avaliacao> listaavaliacao;

    /**
     * Lê o conteúdo do arquivo JSON de avaliações e o carrega na lista de avaliações.
     *
     * @return Lista de avaliações carregadas do arquivo JSON.
     * @throws IOException Se ocorrer um erro de leitura do arquivo.
     */
    public List<Avaliacao> lerConteudoArquivo() throws IOException {
        try {
            FileReader fileReader = new FileReader("avaliacoes.json");
            Type listType = new TypeToken<ArrayList<Avaliacao>>() {}.getType();
            listaavaliacao = new Gson().fromJson(fileReader, listType);
            fileReader.close();
            if (listaavaliacao == null) {
                listaavaliacao = new ArrayList<>();
            }
        } catch (IOException e) {
            listaavaliacao = new ArrayList<>();
            throw e;
        }
        return listaavaliacao;
    }

    /**
     * Adiciona uma nova avaliação à lista e a salva no arquivo JSON.
     *
     * @param avaliacao Avaliação a ser adicionada.
     * @return true se a operação for bem-sucedida.
     * @throws IOException Se ocorrer um erro ao salvar no arquivo.
     */
    public boolean adicionarAvaliacaoNoArquivo(Avaliacao avaliacao) throws IOException {
        lerConteudoArquivo();
        listaavaliacao.add(avaliacao);
        String jsonAvaliacao = new Gson().toJson(listaavaliacao);
        FileWriter fileWriter = new FileWriter("avaliacoes.json");
        fileWriter.write(jsonAvaliacao);
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /**
     * Limpa todas as avaliações do arquivo JSON e da lista de avaliações.
     *
     * @throws IOException Se ocorrer um erro ao limpar o arquivo.
     */
    public void limparArquivoJson() throws IOException {
        try (FileWriter fileWriter = new FileWriter("avaliacoes.json")) {
            fileWriter.write("[]"); // Escreve uma lista vazia no arquivo
        }

        if (listaavaliacao == null) {
            listaavaliacao = new ArrayList<>(); // Inicializa a lista se estiver nula
        }
        listaavaliacao.clear(); // Limpa a lista
    }

    /**
     * Salva uma lista de avaliações fornecida no arquivo JSON.
     *
     * @param avaliacoes Lista de avaliações a serem salvas no arquivo JSON.
     * @throws IOException Se ocorrer um erro ao salvar no arquivo.
     */
    public void salvarAvaliacoesNoArquivo(List<Avaliacao> avaliacoes) throws IOException {
        try (FileWriter fileWriter = new FileWriter("avaliacoes.json")) {
            new Gson().toJson(avaliacoes, fileWriter);
        }
    }

}
