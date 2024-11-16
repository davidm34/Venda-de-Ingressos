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

    /** ID do evento associado ao ingresso. */
    String idEvento;

    /** ID único do ingresso. */
    String id;

    /** Evento associado ao ingresso. */
    Evento evento;

    /** Preço do ingresso. */
    Double preco;

    /** Assento reservado. */
    String assento;

    /** Indica se o ingresso está ativo. */
    Boolean ingressoAtivo;

    /** Indica se o ingresso está disponível para uso. */
    Boolean ativo = true;

    /**
     * Construtor que inicializa um ingresso com o evento, preço e assento.
     *
     * @param evento O evento associado ao ingresso.
     * @param preco  O preço do ingresso.
     * @param assento O assento reservado.
     */
    public Ingresso(Evento evento, Double preco, String assento) {
        this.evento = evento;
        this.preco = preco;
        this.assento = assento;
    }

    /**
     * Construtor que inicializa um ingresso com ID do evento, preço, assento, ID e status de ativo.
     *
     * @param idEvento      O ID do evento associado.
     * @param preco         O preço do ingresso.
     * @param assento       O assento reservado.
     * @param id            O ID único do ingresso.
     * @param ingressoAtivo O status de ativo do ingresso.
     */
    public Ingresso(String idEvento, Double preco, String assento, String id, Boolean ingressoAtivo) {
        this.idEvento = idEvento;
        this.preco = preco;
        this.assento = assento;
        this.id = id;
        this.ingressoAtivo = true;
    }

    public Evento getEvento() {
        return evento;
    }

    public Double getPreco() {
        return preco;
    }

    public String getAssento() {
        return assento;
    }

    public String getId() {
        return id;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Boolean getIngressoAtivo() {
        return ingressoAtivo;
    }

    Date data_atual = new Date();

    public void setIngressoAtivo(Boolean ingressoAtivo) {
        this.ingressoAtivo = ingressoAtivo;
    }

    /**
     * Cancela o ingresso se a data do evento for futura.
     *
     * @return true se o ingresso foi cancelado, false caso contrário.
     */
    public Boolean cancelar() {
        if (evento.data.after(data_atual)) {
            ativo = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reativa o ingresso.
     *
     * @return true sempre, pois reativar é sempre bem-sucedido.
     */
    public Boolean reativar() {
        ativo = true;
        return true;
    }

    /**
     * Cancela o ingresso, tornando-o inativo.
     */
    public void cancelarIngresso() {
        ativo = false;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }
        Ingresso ingresso = (Ingresso) objeto;
        return Double.compare(ingresso.preco, preco) == 0 &&
                Objects.equals(evento, ingresso.evento) &&
                Objects.equals(assento, ingresso.assento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evento, preco, assento);
    }
}
