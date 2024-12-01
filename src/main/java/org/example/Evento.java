package org.example;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe que representa um evento no sistema de venda de ingressos.
 * Um evento possui um nome, descrição, data, assentos e comentários.
 *
 * @author David Neves Dias
 */
public class Evento {

    /** O identificador único do evento. */
    private String id;

    /** O nome do evento. */
    private String nome;

    /** A descrição do evento. */
    private String descricao;

    /** A data do evento. */
    protected Date data;

    /**
     * Constrói um novo evento com nome, descrição, data e identificador.
     *
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     * @param id O identificador único do evento.
     */
    public Evento(String nome, String descricao, Date data, String id){
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.id = id;
    }

    /**
     * Obtém o identificador do evento.
     *
     * @return O identificador do evento.
     */
    public String getId(){ return id; }

    /**
     * Obtém o nome do evento.
     *
     * @return O nome do evento.
     */
    public String getNome(){
        return nome;
    }

    /**
     * Obtém a descrição do evento.
     *
     * @return A descrição do evento.
     */
    public String getDescricao(){
        return descricao;
    }

    /**
     * Obtém a data do evento.
     *
     * @return A data do evento.
     */
    public Date getData(){
        return data;
    }

    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    public boolean isAtivo(){
        Date data_atual = new Date();
        return data.after(data_atual);
    }

}
