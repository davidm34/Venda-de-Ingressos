package org.example;

/**
 * Classe que representa uma compra de ingresso para um evento.
 * Contém informações sobre o email do comprador, identificadores da compra, evento, cartão e o assento reservado.
 *
 * @author David Neves Dias
 */
public class Compra {

    /** Email do comprador. */
    private String email;

    /** Identificador único da compra. */
    private String idCompra;

    /** Identificador do evento associado à compra. */
    private String idEvento;

    /** Identificador do cartão utilizado na compra. */
    private String idCard;

    /** Assento reservado para a compra. */
    private String seat;

    /**
     * Construtor para a classe Compra.
     *
     * @param email Email do comprador.
     * @param idCompra Identificador único da compra.
     * @param idEvento Identificador do evento associado à compra.
     * @param idCard Identificador do cartão utilizado na compra.
     * @param seat Assento reservado para a compra.
     */
    public Compra(String email, String idCompra, String idEvento, String idCard, String seat) {
        this.email = email;
        this.idCompra = idCompra;
        this.idEvento = idEvento;
        this.idCard = idCard;
        this.seat = seat;
    }

    /**
     * Obtém o email do comprador.
     *
     * @return Email do comprador.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtém o identificador do cartão utilizado na compra.
     *
     * @return Identificador do cartão.
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * Obtém o identificador da compra.
     *
     * @return Identificador da compra.
     */
    public String getIdCompra() {
        return idCompra;
    }

    /**
     * Obtém o identificador do evento associado à compra.
     *
     * @return Identificador do evento.
     */
    public String getIdEvento() {
        return idEvento;
    }

    /**
     * Obtém o assento reservado para a compra.
     *
     * @return Assento reservado.
     */
    public String getSeat() {
        return seat;
    }
}
