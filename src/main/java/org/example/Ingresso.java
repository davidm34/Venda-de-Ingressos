package org.example;

import java.util.Date;
import java.util.Objects;

/**
 * Classe que representa um ingresso para um evento, contendo informações como
 * o evento associado, preço, assento, e status do ingresso.
 *
 * @author David Neves Dias
 */
public class Ingresso {

    private String name;

    private String data;

    /**
     * ID único do ingresso.
     */
    private String id;


    /**
     * Preço do ingresso.
     */
    Double preco;


    /**
     * Construtor que inicializa um ingresso com ID do evento, preço, assento, ID e status de ativo.
     *
     * @param id    O ID único do ingresso.
     * @param preco O preço do ingresso.
     */
    public Ingresso(String name, String data, String id, Double preco) {
        this.name = name;
        this.data = data;
        this.id = id;
        this.preco = preco;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public Double getPreco() {
        return preco;
    }


}
