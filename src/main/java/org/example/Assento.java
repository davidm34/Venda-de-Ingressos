package org.example; /**
 * Classe que representa um assento em um evento.
 * Contém informações sobre o identificador do evento e o número ou código do assento.
 */

/** @author David Neves Dias **/
public class Assento {

    /** Identificador do evento ao qual o assento pertence. */
    private String idEvento;

    /** Número ou código do assento. */
    private String seat;

    /**
     * Construtor para a classe Assento.
     *
     * @param idEvento Identificador do evento ao qual o assento pertence.
     * @param seat Número ou código do assento.
     */
    public Assento(String idEvento, String seat) {
        this.idEvento = idEvento;
        this.seat = seat;
    }

    /**
     * Obtém o identificador do evento ao qual o assento pertence.
     *
     * @return ID do evento.
     */
    public String getIdEvento() {
        return idEvento;
    }

    /**
     * Obtém o número ou código do assento.
     *
     * @return Número ou código do assento.
     */
    public String getSeat() {
        return seat;
    }
}
