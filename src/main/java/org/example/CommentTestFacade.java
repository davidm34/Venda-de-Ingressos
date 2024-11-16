package org.example;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * A facade for managing comments (evaluations) related to events.
 */
public class CommentTestFacade {

    /**
     * Default constructor for CommentTestFacade.
     */
    public CommentTestFacade() {
    }

    /**
     * Creates a new comment for a specific event, if the event has already occurred.
     *
     * @param userID    The ID of the user making the comment.
     * @param eventID   The ID of the event being commented on.
     * @param rating    The rating given to the event.
     * @param content   The content of the comment.
     * @return The ID of the newly created comment.
     * @throws IOException If an error occurs while reading or writing data.
     * @throws SecurityException If the event date is in the future.
     */
    public String create(String userID, String eventID, int rating, String content) throws IOException {
        EventTestFacade eventTestFacade = new EventTestFacade();
        Date data_atual = new Date();
        if(eventTestFacade.getDateByEventId(eventID).after(data_atual)) {
            throw new SecurityException("Comentário só pode ser adicionando após a realização do evento.");
        }
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        Avaliacao avaliacao = new Avaliacao(userID, eventID, id, rating, content);
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        avaliacaoManager.adicionarAvaliacaoNoArquivo(avaliacao);
        return id;
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return The comment associated with the given ID, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public Avaliacao getById(String id) throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getId().equals(id)) {
                return avaliacao;
            }
        }
        return null;
    }

    /**
     * Retrieves the content of a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return The content of the comment, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public String getContentById(String id) throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getId().equals(id)) {
                return avaliacao.getComentarios();
            }
        }
        return null;
    }

    /**
     * Retrieves the rating of a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return The rating of the comment, or -1 if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public int getRatingCommentById(String id) throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getId().equals(id)) {
                return avaliacao.getAvaliacao();
            }
        }
        return -1;
    }

    /**
     * Retrieves the user ID associated with a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return The user ID of the comment, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public String getUserIdById(String id) throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getId().equals(id)) {
                return avaliacao.getIdUsuario();
            }
        }
        return null;
    }

    /**
     * Retrieves the event ID associated with a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return The event ID of the comment, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public String getEventIdById(String id) throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getId().equals(id)) {
                return avaliacao.getIdEvento();
            }
        }
        return null;
    }

    /**
     * Deletes all comments from the storage.
     *
     * @throws IOException If an error occurs while clearing data.
     */
    public void deleteAllComments() throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        avaliacaoManager.limparArquivoJson();
    }

    /**
     * Calculates the average rating for an event based on its comments.
     *
     * @param eventId The ID of the event.
     * @return The average rating of the event, or 0 if no ratings are found.
     * @throws IOException If an error occurs while reading data.
     */
    public double getEventRatingByEventId(String eventId) throws IOException {
        int rating = 0;
        int tam = 0;
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getIdEvento().equals(eventId)) {
                rating += avaliacao.getAvaliacao();
                tam++;
            }
        }
        return (double) rating / tam;
    }

    /**
     * Deletes a specific comment by its ID.
     *
     * @param c1Id The ID of the comment to be deleted.
     * @throws IOException If an error occurs while reading or writing data.
     */
    public void delete(String c1Id) throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();

        // Remover a avaliação da lista com base no ID fornecido
        avaliacoes.removeIf(avaliacao -> avaliacao.getId().equals(c1Id));

        // Salvar a lista atualizada no arquivo JSON
        avaliacaoManager.salvarAvaliacoesNoArquivo(avaliacoes);
    }
}
