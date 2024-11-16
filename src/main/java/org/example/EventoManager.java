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
 * Classe que gerencia as operações relacionadas aos eventos, incluindo leitura,
 * escrita e manipulação da lista de eventos armazenados em um arquivo JSON.
 *
 * @author David Neves Dias
 */
public class EventoManager {
    /** Lista de eventos gerenciados. */
    private List<Evento> listaevento;

    /**
     * Lê o conteúdo do arquivo JSON e retorna a lista de eventos.
     *
     * @return A lista de eventos lida do arquivo.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     */
    public List<Evento> lerConteudoArquivo() throws IOException {
        try {
            FileReader fileReader = new FileReader("eventos.json");
            Type listType = new TypeToken<ArrayList<Evento>>() {}.getType();
            listaevento = new Gson().fromJson(fileReader, listType);
            fileReader.close();
            if (listaevento == null) {
                listaevento = new ArrayList<>();
            }
        } catch (IOException e) {
            listaevento = new ArrayList<>();
            throw e;
        }
        return listaevento;
    }

    /**
     * Adiciona um evento à lista e salva a lista no arquivo JSON.
     *
     * @param evento O evento a ser adicionado.
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public void adicionarEventoNoArquivo(Evento evento) throws IOException {
        lerConteudoArquivo();
        listaevento.add(evento);
        String jsonEvento = new Gson().toJson(listaevento);
        FileWriter fileWriter = new FileWriter("eventos.json");
        fileWriter.write(jsonEvento);
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Limpa o conteúdo do arquivo JSON, substituindo-o por uma lista vazia.
     *
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public void limparArquivoJson() throws IOException {
        try (FileWriter fileWriter = new FileWriter("eventos.json")) {
            fileWriter.write("[]"); // Escreve uma lista vazia no arquivo
        }

        if (listaevento == null) {
            listaevento = new ArrayList<>(); // Inicializa a lista se estiver nula
        }
        listaevento.clear(); // Limpa a lista
    }

    /**
     * Salva um evento na lista, removendo eventos duplicados com o mesmo ID.
     *
     * @param evento O evento a ser salvo.
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public void save(Evento evento) throws IOException {
        // Verifica e inicializa `listaevento` caso esteja nulo
        if (listaevento == null) {
            listaevento = lerConteudoArquivo();
        }

        // Atualiza lista, removendo eventos duplicados antes de adicionar o novo
        listaevento.removeIf(t -> t.getId().equals(evento.getId()));
        listaevento.add(evento);

        // Serializa e salva a lista de eventos no arquivo JSON
        try (FileWriter fileWriter = new FileWriter("eventos.json")) {
            new Gson().toJson(listaevento, fileWriter);
        }
    }
}
