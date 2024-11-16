package org.example;
/**
 * Classe que representa uma avaliação feita por um usuário em um evento.
 * Contém informações como identificador da avaliação, identificador do usuário, identificador do evento,
 * nota da avaliação e comentários sobre o evento.
 */
public class Avaliacao {

    /** Identificador do usuário que fez a avaliação. */
    private String idUsuario;

    /** Identificador do evento avaliado. */
    private String idEvento;

    /** Identificador único da avaliação. */
    private String idAvaliacao;

    /** Nota atribuída ao evento pelo usuário. */
    public int avaliacoes;

    /** Comentários adicionais do usuário sobre o evento. */
    public String comentarios;

    /**
     * Construtor para a classe Avaliacao.
     *
     * @param idUsuario Identificador do usuário que fez a avaliação.
     * @param idEvento Identificador do evento avaliado.
     * @param idAvaliacao Identificador único da avaliação.
     * @param avaliacoes Nota atribuída ao evento pelo usuário.
     * @param comentarios Comentários adicionais do usuário sobre o evento.
     */
    public Avaliacao(String idUsuario, String idEvento, String idAvaliacao, int avaliacoes, String comentarios) {
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
        this.idAvaliacao = idAvaliacao;
        this.avaliacoes = avaliacoes;
        this.comentarios = comentarios;
    }

    /**
     * Obtém a nota da avaliação.
     *
     * @return Nota atribuída ao evento.
     */
    public int getAvaliacao() {
        return avaliacoes;
    }

    /**
     * Obtém os comentários do usuário sobre o evento.
     *
     * @return Comentários do usuário.
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Obtém o identificador da avaliação.
     *
     * @return ID da avaliação.
     */
    public String getId() {
        return idAvaliacao;
    }

    /**
     * Obtém o identificador do usuário que fez a avaliação.
     *
     * @return ID do usuário.
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Obtém o identificador do evento avaliado.
     *
     * @return ID do evento.
     */
    public String getIdEvento() {
        return idEvento;
    }
}
