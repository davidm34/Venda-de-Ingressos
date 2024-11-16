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
 * Classe que gerencia operações de compras, permitindo leitura, adição, limpeza e atualização de compras em um arquivo JSON.
 *
 * @author David Neves Dias
 */
public class CompraManager {

    /** Lista de compras gerenciadas pelo CompraManager. */
    private List<Compra> listacompra;

    /**
     * Lê o conteúdo do arquivo JSON de compras e o carrega na lista de compras.
     *
     * @return Lista de compras carregadas do arquivo JSON.
     * @throws IOException Se ocorrer um erro de leitura do arquivo.
     */
    public List<Compra> lerConteudoArquivo() throws IOException {
        try {
            FileReader fileReader = new FileReader("compras.json");
            Type listType = new TypeToken<ArrayList<Compra>>() {}.getType();
            listacompra = new Gson().fromJson(fileReader, listType);
            fileReader.close();
            if (listacompra == null) {
                listacompra = new ArrayList<>();
            }
        } catch (IOException e) {
            listacompra = new ArrayList<>();
            throw e;
        }
        return listacompra;
    }

    /**
     * Adiciona uma nova compra à lista e salva no arquivo JSON.
     *
     * @param compra Compra a ser adicionada.
     * @return true se a operação for bem-sucedida.
     * @throws IOException Se ocorrer um erro ao salvar no arquivo.
     */
    public boolean adicionarCompraNoArquivo(Compra compra) throws IOException {
        lerConteudoArquivo();
        listacompra.add(compra);
        String jsonCompra = new Gson().toJson(listacompra);
        FileWriter fileWriter = new FileWriter("compras.json");
        fileWriter.write(jsonCompra);
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /**
     * Limpa todas as compras do arquivo JSON e da lista de compras.
     *
     * @throws IOException Se ocorrer um erro ao limpar o arquivo.
     */
    public void limparArquivoJson() throws IOException {
        try (FileWriter fileWriter = new FileWriter("compras.json")) {
            fileWriter.write("[]"); // Escreve uma lista vazia no arquivo
        }

        if (listacompra == null) {
            listacompra = new ArrayList<>(); // Inicializa a lista se estiver nula
        }
        listacompra.clear(); // Limpa a lista
    }

    /**
     * Salva ou atualiza uma compra no arquivo JSON.
     * Caso o identificador da compra já exista, ele é atualizado com as novas informações.
     *
     * @param compra Compra a ser salva ou atualizada.
     * @throws IOException Se ocorrer um erro ao salvar no arquivo.
     */
    public void save(Compra compra) throws IOException {
        if (listacompra == null) {
            listacompra = lerConteudoArquivo();
        }

        listacompra.removeIf(t -> t.getIdCompra().equals(compra.getIdCompra()));
        listacompra.add(compra);

        try (FileWriter fileWriter = new FileWriter("compras.json")) {
            new Gson().toJson(listacompra, fileWriter);
        }
    }

}
