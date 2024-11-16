package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Classe que atua como controlador do sistema de venda de ingressos,
 * gerenciando usuários, eventos e a compra de ingressos.
 *
 * @author David Neves Dias
 */
public class Controller {

    /** Lista de usuários cadastrados no sistema. */
    private List<Usuario> usuarios;

    /** Lista de eventos disponíveis no sistema. */
    private List<Evento> eventos;

    /**
     * Construtor que inicializa as listas de usuários e eventos.
     */
    public Controller() {
        usuarios = new ArrayList<>();
        eventos = new ArrayList<>();
    }

    /**
     * Cadastra um novo usuário no sistema.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param nome O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param email O email do usuário.
     * @param isAdmin Indica se o usuário é um administrador.
     * @return O usuário cadastrado.
     */
    public Usuario cadastrarUsuario(String login, String senha, String nome, String cpf, String email, boolean isAdmin) {
        Usuario usuario = new Usuario(login, senha, nome, cpf, email, isAdmin);
        usuarios.add(usuario);
        return usuario;
    }

    /**
     * Cadastra um novo evento, se o usuário for um administrador.
     *
     * @param admin O usuário que está cadastrando o evento.
     * @param nome O nome do evento.
     * @param descricao A descrição do evento.
     * @param data A data do evento.
     * @return O evento cadastrado.
     * @throws SecurityException Se o usuário não for um administrador.
     */
    public Evento cadastrarEvento(Usuario admin, String nome, String descricao, Date data) {
        if (!admin.isAdmin()) {
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }

        Evento evento = new Evento(nome, descricao, data);
        eventos.add(evento);
        return evento;
    }

    /**
     * Adiciona um assento a um evento específico.
     *
     * @param nomeEvento O nome do evento ao qual o assento será adicionado.
     * @param assento O identificador do assento a ser adicionado.
     */
    public void adicionarAssentoEvento(String nomeEvento, String assento) {
        Evento evento = buscarEventoPorNome(nomeEvento);
        if (evento != null) {
            evento.adicionarAssento(assento);
        }
    }

    /**
     * Permite que um usuário compre um ingresso para um evento específico.
     *
     * @param usuario O usuário que está comprando o ingresso.
     * @param nomeEvento O nome do evento.
     * @param assento O assento que será reservado.
     * @return O ingresso comprado, ou null se a compra não for bem-sucedida.
     */
    public Ingresso comprarIngresso(Usuario usuario, String nomeEvento, String assento) {
        Evento evento = buscarEventoPorNome(nomeEvento);
        if (evento != null && evento.getAssentosDisponiveis().contains(assento)) {
            evento.removerAssento(assento);
            Ingresso ingresso = new Ingresso(evento, 100.0, assento);
            usuario.addIngresso(ingresso);
            return ingresso;
        }
        return null;
    }

    /**
     * Cancela a compra de um ingresso.
     *
     * @param usuario O usuário que deseja cancelar a compra.
     * @param ingresso O ingresso a ser cancelado.
     * @return true se a compra for cancelada com sucesso, false caso contrário.
     */
    public boolean cancelarCompra(Usuario usuario, Ingresso ingresso) {
        if (usuario.getIngressos().contains(ingresso)) {
            ingresso.cancelar();
            usuario.removeIngresso(ingresso);
            return true;
        }
        return false;
    }

    /**
     * Lista todos os eventos disponíveis no sistema.
     *
     * @return Lista de eventos disponíveis.
     */
    public List<Evento> listarEventosDisponiveis() {
        return eventos;
    }

    /**
     * Lista todos os ingressos comprados por um usuário específico.
     *
     * @param usuario O usuário cujos ingressos comprados serão listados.
     * @return Lista de ingressos comprados pelo usuário.
     */
    public List<Ingresso> listarIngressosComprados(Usuario usuario) {
        return usuario.getIngressos();
    }

    /**
     * Busca um evento pelo seu nome.
     *
     * @param nome O nome do evento a ser buscado.
     * @return O evento correspondente ao nome, ou null se não encontrado.
     */
    private Evento buscarEventoPorNome(String nome) {
        for (Evento evento : eventos) {
            if (evento.getNome().equals(nome)) {
                return evento;
            }
        }
        return null;
    }
}
