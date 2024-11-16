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
     * @param nome O nome do usuário.
     * @param senha A senha do usuário.
     * @param cpf O CPF do usuário.
     * @param email O email do usuário.
     * @param isadmin Indica se o usuário tem privilégios de administrador.
     * @param id O ID do usuário.
     */

    public Usuario(String nome, String senha, String cpf, String email, Boolean isadmin, String id) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
        this.isadmin = isadmin;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getId() {
        return id;
    }

    public Boolean isAdmin() {
        return isadmin;
    }

    /**
     * Verifica se as credenciais de login estão corretas.
     *
     * @param login O login a ser verificado.
     * @param senha A senha a ser verificada.
     * @return true se as credenciais estiverem corretas, false caso contrário.
     */
    public boolean login(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public String setSenha(String senha) {
        return this.senha = senha;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public String setCpf(String cpf) {
        return this.cpf = cpf;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    public void addIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void removeIngresso(Ingresso ingresso) {
        ingressos.remove(ingresso);
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(login, cpf, email);
    }

}
