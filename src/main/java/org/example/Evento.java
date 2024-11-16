package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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

    /** O login do usuário que criou o evento. */
    private String login;

    /** O nome do evento. */
    private String nome;

    /** A descrição do evento. */
    private String descricao;

    /** A data do evento. */
    protected Date data;

    /** Comentários e avaliações sobre o evento. */
    Avaliacao comentarios;

    /** Lista de assentos disponíveis para o evento. */
    List<String> assentos = new ArrayList<>();

    /**
     * Constrói um novo evento com nome, descrição e data.
     *
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     */
    public Evento(String nome, String descricao, Date data){
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

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
     * Constrói um novo evento com login, nome, descrição, data, identificador e lista de assentos.
     *
     * @param login O login do usuário que criou o evento.
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     * @param id O identificador único do evento.
     * @param assentos A lista de assentos disponíveis para o evento.
     */
    public Evento(String login, String nome, String descricao, Date data, String id, List<String> assentos){
        this.login = login;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.id = id;
        this.assentos = assentos;
    }

    /**
     * Constrói um novo evento com login, nome, descrição, data e identificador,
     * inicializando a lista de assentos com um assento padrão.
     *
     * @param login O login do usuário que criou o evento.
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     * @param id O identificador único do evento.
     */
    public Evento(String login, String nome, String descricao, Date data, String id){
        this(login, nome, descricao, data, id, List.of("Nenhum assento"));
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
     * Obtém o login do usuário que criou o evento.
     *
     * @return O login do usuário.
     */
    public String getLogin(){ return login; }

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

    /**
     * Obtém a lista de assentos do evento.
     *
     * @return A lista de assentos.
     */
    public List<String> getAssentos(){ return assentos; }

    /**
     * Adiciona um assento à lista de assentos disponíveis do evento.
     *
     * @param assento O assento a ser adicionado.
     */
    public void adicionarAssento(String assento){
        assentos.add(assento);
    }

    /**
     * Obtém a lista de assentos disponíveis.
     *
     * @return A lista de assentos disponíveis.
     */
    public List<String> getAssentosDisponiveis(){
        return assentos;
    }

    /**
     * Remove um assento da lista de assentos disponíveis do evento.
     *
     * @param assento O assento a ser removido.
     */
    public void removerAssento(String assento){
        assentos.removeIf(c -> c.equals(assento));
    }

    /**
     * Verifica se o evento está ativo (ou seja, se a data do evento é futura).
     *
     * @return true se o evento está ativo, false caso contrário.
     */
    public boolean isAtivo(){
        Date data_atual = new Date();
        return data.after(data_atual);
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }
        Evento evento = (Evento) objeto;
        return Objects.equals(nome, evento.nome) && Objects.equals(descricao, evento.descricao) && Objects.equals(data, evento.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, data);
    }
}
