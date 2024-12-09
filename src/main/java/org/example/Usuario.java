package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe que representa um usuário do sistema, contendo informações pessoais,
 * credenciais de login e a lista de ingressos comprados.
 *
 * @author David Neves Dias
 */
public class Usuario {
    private String login;
    private String nome;
    private String senha;
    private String cpf;
    private String email;
    private Boolean isadmin;

    private String id;

    /** Lista de ingressos comprados pelo usuário. */
    List<Ingresso> ingressos = new ArrayList<>();

    /**
     * Construtor para criar um novo usuário com um ID.
     *
     * @param nome    O nome do usuário.
     * @param senha   A senha do usuário.
     * @param cpf     O CPF do usuário.
     * @param email   O email do usuário.
     * @param isadmin Indica se o usuário tem privilégios de administrador.
     * @param id      O ID do usuário.
     */
    public Usuario(String nome, String senha, String cpf, String email, Boolean isadmin, String id) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
        this.isadmin = isadmin;
        this.id = id;
    }

    /**
     * Obtém o login do usuário.
     *
     * @return O login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Obtém o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o CPF do usuário.
     *
     * @return O CPF do usuário.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Obtém o email do usuário.
     *
     * @return O email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Obtém o ID do usuário.
     *
     * @return O ID do usuário.
     */
    public String getId() {
        return id;
    }

    /**
     * Verifica se o usuário é administrador.
     *
     * @return {@code true} se o usuário for administrador, caso contrário {@code false}.
     */
    public Boolean isAdmin() {
        return isadmin;
    }

    /**
     * Verifica se as credenciais de login estão corretas.
     *
     * @param login O login a ser verificado.
     * @param senha A senha a ser verificada.
     * @return {@code true} se as credenciais estiverem corretas, caso contrário {@code false}.
     */
    public boolean login(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha A nova senha do usuário.
     * @return A nova senha definida.
     */
    public String setSenha(String senha) {
        return this.senha = senha;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome O novo nome do usuário.
     * @return O novo nome definido.
     */
    public String setNome(String nome) {
        return this.nome = nome;
    }

    /**
     * Define o CPF do usuário.
     *
     * @param cpf O novo CPF do usuário.
     * @return O novo CPF definido.
     */
    public String setCpf(String cpf) {
        return this.cpf = cpf;
    }

    /**
     * Define o email do usuário.
     *
     * @param email O novo email do usuário.
     * @return O novo email definido.
     */
    public String setEmail(String email) {
        return this.email = email;
    }

    /**
     * Adiciona um ingresso à lista de ingressos do usuário.
     *
     * @param ingresso O ingresso a ser adicionado.
     */
    public void addIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    /**
     * Remove um ingresso da lista de ingressos do usuário.
     *
     * @param ingresso O ingresso a ser removido.
     */
    public void removeIngresso(Ingresso ingresso) {
        ingressos.remove(ingresso);
    }

    /**
     * Obtém a lista de ingressos do usuário.
     *
     * @return A lista de ingressos do usuário.
     */
    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    /**
     * Verifica se este objeto é igual a outro.
     *
     * @param objeto O objeto a ser comparado.
     * @return {@code true} se os objetos forem iguais, caso contrário {@code false}.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) objeto;
        return Objects.equals(login, usuario.login) &&
                Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(email, usuario.email);
    }

    /**
     * Gera o código hash para este objeto.
     *
     * @return O código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, cpf, email);
    }
}
