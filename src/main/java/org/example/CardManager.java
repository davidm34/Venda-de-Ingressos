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
 * Classe que gerencia cartões de pagamento, permitindo operações de leitura, adição, limpeza e salvamento de cartões em um arquivo JSON.
 *
 * @author David Neves Dias
 */
public class CardManager {

    /** Lista de cartões gerenciados pelo CardManager. */
    private List<Card> listacard;

    /**
     * Lê o conteúdo do arquivo JSON de cartões e o carrega na lista de cartões.
     *
     * @return Lista de cartões carregados do arquivo JSON.
     * @throws IOException Se ocorrer um erro de leitura do arquivo.
     */
    public List<Card> lerConteudoArquivo() throws IOException {
        try {
            FileReader fileReader = new FileReader("cards.json");
            Type listType = new TypeToken<ArrayList<Card>>() {}.getType();
            listacard = new Gson().fromJson(fileReader, listType);
            fileReader.close();
            if (listacard == null) {
                listacard = new ArrayList<>();
            }
        } catch (IOException e) {
            listacard = new ArrayList<>();
            throw e;
        }
        return listacard;
    }

    /**
     * Adiciona um novo cartão à lista e salva no arquivo JSON.
     *
     * @param card Cartão a ser adicionado.
     * @return true se a operação for bem-sucedida.
     * @throws IOException Se ocorrer um erro ao salvar no arquivo.
     */
    public boolean adicionarCardNoArquivo(Card card) throws IOException {
        lerConteudoArquivo();
        listacard.add(card);
        String jsonCard = new Gson().toJson(listacard);
        FileWriter fileWriter = new FileWriter("cards.json");
        fileWriter.write(jsonCard);
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /**
     * Limpa todos os cartões do arquivo JSON e da lista de cartões.
     *
     * @throws IOException Se ocorrer um erro ao limpar o arquivo.
     */
    public void limparArquivoJson() throws IOException {
        try (FileWriter fileWriter = new FileWriter("cards.json")) {
            fileWriter.write("[]"); // Escreve uma lista vazia no arquivo
        }

        if (listacard == null) {
            listacard = new ArrayList<>(); // Inicializa a lista se estiver nula
        }
        listacard.clear(); // Limpa a lista
    }

    /**
     * Salva uma lista de cartões fornecida no arquivo JSON.
     *
     * @param cards Lista de cartões a serem salvos no arquivo JSON.
     * @throws IOException Se ocorrer um erro ao salvar no arquivo.
     */
    public void salvarCardsNoArquivo(List<Card> cards) throws IOException {
        try (FileWriter fileWriter = new FileWriter("cards.json")) {
            new Gson().toJson(cards, fileWriter);
        }
    }

}
