package org.example;

import java.util.Date;

/**
 * Classe que representa um cartão de pagamento utilizado para realizar uma compra de ingresso.
 * Contém informações como ID do cartão, ID do usuário, nome, número do cartão, validade e código de segurança.
 */

/** @author David Neves Dias **/
public class Card {

    /** Identificador único do cartão. */
    private String idCartao;

    /** Identificador do usuário associado ao cartão. */
    private String idUsuario;

    /** Nome do titular do cartão. */
    private String nomedoCartao;

    /** Número do cartão. */
    private String numerodoCartao;

    /** Data de validade do cartão. */
    private Date validade;

    /** Código de segurança do cartão. */
    private int codigoDeSeguranca;

    /**
     * Construtor para a classe Card.
     *
     * @param idCartao Identificador único do cartão.
     * @param idUsuario Identificador do usuário associado ao cartão.
     * @param nomedoCartao Nome do titular do cartão.
     * @param numerodoCartao Número do cartão.
     * @param validade Data de validade do cartão.
     * @param codigoDeSeguranca Código de segurança do cartão.
     */
    public Card(String idCartao, String idUsuario, String nomedoCartao, String numerodoCartao, Date validade, int codigoDeSeguranca) {
        this.idCartao = idCartao;
        this.idUsuario = idUsuario;
        this.nomedoCartao = nomedoCartao;
        this.numerodoCartao = numerodoCartao;
        this.validade = validade;
        this.codigoDeSeguranca = codigoDeSeguranca;
    }

    /**
     * Obtém o identificador do cartão.
     *
     * @return ID do cartão.
     */
    public String getIdCartao() {
        return idCartao;
    }

    /**
     * Obtém o identificador do usuário associado ao cartão.
     *
     * @return ID do usuário.
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Obtém a data de validade do cartão.
     *
     * @return Data de validade do cartão.
     */
    public Date getValidade() {
        return validade;
    }

    /**
     * Obtém o código de segurança do cartão.
     *
     * @return Código de segurança do cartão.
     */
    public int getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }

    /**
     * Obtém o nome do titular do cartão.
     *
     * @return Nome do titular do cartão.
     */
    public String getNomedoCartao() {
        return nomedoCartao;
    }

    /**
     * Obtém o número do cartão.
     *
     * @return Número do cartão.
     */
    public String getNumerodoCartao() {
        return numerodoCartao;
    }
}
