package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que gerencia a lista de assentos para um evento, permitindo ler, salvar e deletar assentos em um arquivo JSON.
 */

/** @author David Neves Dias **/
public class AssentoManager {

    /** Lista de assentos gerenciados pelo AssentoManager. */
    public List<Assento> listaassentos = new ArrayList<>();

    /**
     * Lê o conteúdo do arquivo JSON de assentos e o carrega na lista de assentos.
     *
     * @return Lista de assentos carregados do arquivo JSON.
     * @throws IOException Se ocorrer um erro de leitura do arquivo.
     */
    public List<Assento> lerConteudoArquivoAssento() throws IOException {
        try {
            FileReader fileReader = new FileReader("assentos.json");
            Type listType = new TypeToken<ArrayList<Assento>>() {}.getType();
            listaassentos = new Gson().fromJson(fileReader, listType);
            fileReader.close();
            if (listaassentos == null) {
                listaassentos = new ArrayList<>();
            }
        } catch (IOException e) {
            listaassentos = new ArrayList<>();
            throw e;
        }
        return listaassentos;
    }

    /**
     * Salva um assento no arquivo JSON, garantindo que não haja duplicatas na lista de assentos.
     *
     * @param assento Assento a ser salvo.
     * @throws IOException Se ocorrer um erro de gravação no arquivo.
     */
    public void save(Assento assento) throws IOException {
        // Verifica e inicializa `listaassentos` caso esteja nulo
        if (listaassentos == null) {
            listaassentos = lerConteudoArquivoAssento();
        }

        // Atualiza lista, removendo assentos duplicados antes de adicionar o novo
        listaassentos.removeIf(t -> t.getSeat().equals(assento.getSeat()));
        listaassentos.add(assento);

        // Serializa e salva a lista de assentos no arquivo JSON
        try (FileWriter fileWriter = new FileWriter("assentos.json")) {
            new Gson().toJson(listaassentos, fileWriter);
        }
    }

    /**
     * Deleta um assento específico da lista de assentos.
     *
     * @param assento Assento a ser deletado.
     */
    public void deletarAssento(Assento assento) {
        for (int i = 0; i < listaassentos.size(); i++) {
            if (Objects.equals(listaassentos.get(i), assento)) {
                listaassentos.remove(assento);
                return;
            }
        }
    }

    /**
     * Salva uma lista de assentos fornecida no arquivo JSON.
     *
     * @param assentos Lista de assentos a serem salvos no arquivo JSON.
     * @throws IOException Se ocorrer um erro de gravação no arquivo.
     */
    public void salvarAssentosNoArquivo(List<Assento> assentos) throws IOException {
        String jsonAssentos = new Gson().toJson(assentos);
        try (FileWriter fileWriter = new FileWriter("assentos.json")) {
            fileWriter.write(jsonAssentos);
            fileWriter.flush();
        }
    }

}