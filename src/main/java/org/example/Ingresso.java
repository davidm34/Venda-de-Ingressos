package org.example;

/**
 * Classe que representa um ingresso para um evento.
 * Contém informações como o nome do evento, data, ID do ingresso e preço.
 *
 * @author David Neves Dias
 */
public class Ingresso {

    /**
     * Nome do evento associado ao ingresso.
     */
    private String name;

    /**
     * Data do evento associado ao ingresso.
     */
    private String data;

    /**
     * ID único do ingresso.
     */
    private String id;

    /**
     * Preço do ingresso.
     */
    private Double preco;

    /**
     * Construtor que inicializa um ingresso com os detalhes fornecidos.
     *
     * @param name  O nome do evento.
     * @param data  A data do evento.
     * @param id    O ID único do ingresso.
     * @param preco O preço do ingresso.
     */
    public Ingresso(String name, String data, String id, Double preco) {
        this.name = name;
        this.data = data;
        this.id = id;
        this.preco = preco;
    }

    /**
     * Obtém o nome do evento associado ao ingresso.
     *
     * @return O nome do evento.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém a data do evento associado ao ingresso.
     *
     * @return A data do evento.
     */
    public String getData() {
        return data;
    }

    /**
     * Obtém o ID único do ingresso.
     *
     * @return O ID do ingresso.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o preço do ingresso.
     *
     * @return O preço do ingresso.
     */
    public Double getPreco() {
        return preco;
    }
}
