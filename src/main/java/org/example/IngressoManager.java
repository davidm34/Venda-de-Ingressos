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
 * Classe responsável pela gestão dos ingressos, incluindo leitura,
 * escrita e manipulação de dados em um arquivo JSON.
 *
 * @author David Neves Dias
 */
public class IngressoManager {
    /** Lista de ingressos gerenciada por esta classe. */
    private List<Ingresso> listaingresso;

    /**
     * Lê o conteúdo do arquivo de ingressos e o converte em uma lista de objetos Ingresso.
     *
     * @return Lista de ingressos lidos do arquivo.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     */
    public List<Ingresso> lerConteudoArquivo() throws IOException {
        try {
            FileReader fileReader = new FileReader("ingressos.json");
            Type listType = new TypeToken<ArrayList<Ingresso>>() {}.getType();
            listaingresso = new Gson().fromJson(fileReader, listType);
            fileReader.close();
            if (listaingresso == null) {
                listaingresso = new ArrayList<>();
            }
        } catch (IOException e) {
            listaingresso = new ArrayList<>();
            throw e;
        }
        return listaingresso;
    }

    /**
     * Adiciona um ingresso à lista e salva a lista atualizada no arquivo JSON.
     *
     * @param ingressos O ingresso a ser adicionado.
     * @return true se a adição for bem-sucedida.
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public boolean adicionarIngressoNoArquivo(Ingresso ingressos) throws IOException {
        lerConteudoArquivo();
        listaingresso.add(ingressos);
        String jsonIngresso = new Gson().toJson(listaingresso);
        FileWriter fileWriter = new FileWriter("ingressos.json");
        fileWriter.write(jsonIngresso);
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /**
     * Limpa o conteúdo do arquivo JSON, escrevendo uma lista vazia.
     *
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public void limparArquivoJson() throws IOException {
        try (FileWriter fileWriter = new FileWriter("ingressos.json")) {
            fileWriter.write("[]"); // Escreve uma lista vazia no arquivo
        }

        if (listaingresso == null) {
            listaingresso = new ArrayList<>(); // Inicializa a lista se estiver nula
        }
        listaingresso.clear(); // Limpa a lista
    }

    /**
     * Salva um ingresso, removendo qualquer ingresso existente com o mesmo ID
     * antes de adicionar o novo.
     *
     * @param ticket O ingresso a ser salvo.
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public void save(Ingresso ticket) throws IOException {
        // Atualiza a lista de tickets, removendo qualquer ticket com o mesmo ID antes de adicionar o novo
        listaingresso.removeIf(t -> t.getId().equals(ticket.getId()));
        listaingresso.add(ticket);

        // Serializa e salva a lista de tickets atualizada no arquivo JSON
        try (FileWriter fileWriter = new FileWriter("ingressos.json")) {
            new Gson().toJson(listaingresso, fileWriter);
        }
    }
}
