package org.example;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Classe responsável pela fachada de gerenciamento de cartões,
 * fornecendo métodos para criar, recuperar, desabilitar e deletar cartões.
 *
 * @author David Neves Dias
 */
public class CardTestFacade {

    public CardTestFacade() {
    }

    /**
     * Cria um novo cartão para um usuário.
     *
     * @param userEmail O email do usuário que possui o cartão.
     * @param cardNumber O número do cartão a ser criado.
     * @param expiryDate A data de validade do cartão.
     * @param cvv O código de segurança do cartão.
     * @return O ID do cartão criado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     * @throws SecurityException Se já existir um cartão com o mesmo número.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    public String create(String userEmail, String cardNumber, Date expiryDate, int cvv) throws IOException {
        CardManager cardManager = new CardManager();
        List<Card> cards = cardManager.lerConteudoArquivo();

        // Verifica se já existe um cartão com o mesmo número
        for (Card card : cards) {
            if (card.getNumerodoCartao().equals(cardNumber)) {
                throw new SecurityException("Cartão com este número já existe.");
            }
        }

        // Verifica se o usuário existe
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getEmail().equals(userEmail))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Cria um novo cartão com um ID único
        String cardId = UUID.randomUUID().toString();
        Card card = new Card(cardId, usuario.getId(), usuario.getNome(), cardNumber, expiryDate, cvv);
        cards.add(card);

        // Salva a lista atualizada de cartões no arquivo
        cardManager.salvarCardsNoArquivo(cards);

        return cardId;
    }

    /**
     * Recupera o nome do usuário associado a um cartão pelo ID do cartão.
     *
     * @param cardId O ID do cartão.
     * @return O nome do usuário, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getUserNameByCardId(String cardId) throws IOException {
        Card card = getById(cardId);
        return (card != null) ? card.getNomedoCartao() : null;
    }

    /**
     * Recupera o número do cartão pelo ID do cartão.
     *
     * @param cardId O ID do cartão.
     * @return O número do cartão, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getCardNumberByCardId(String cardId) throws IOException {
        Card card = getById(cardId);
        return (card != null) ? card.getNumerodoCartao() : null;
    }

    /**
     * Recupera o ano da data de validade do cartão pelo ID do cartão.
     *
     * @param cardId O ID do cartão.
     * @return O ano da data de validade, ou 0 se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getYearByCardId(String cardId) throws IOException {
        Card card = getById(cardId);
        return (card != null) ? card.getValidade().getYear() : 0;
    }

    /**
     * Recupera o mês da data de validade do cartão pelo ID do cartão.
     *
     * @param cardId O ID do cartão.
     * @return O mês da data de validade, ou 0 se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getMonthByCardId(String cardId) throws IOException {
        Card card = getById(cardId);
        return (card != null) ? card.getValidade().getMonth() : 0;
    }

    /**
     * Recupera o dia da data de validade do cartão pelo ID do cartão.
     *
     * @param cardId O ID do cartão.
     * @return O dia da data de validade, ou 0 se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getDayByCardId(String cardId) throws IOException {
        Card card = getById(cardId);
        return (card != null) ? card.getValidade().getDay() : 0;
    }

    /**
     * Desabilita um cartão pelo ID do cartão.
     *
     * @param cardId O ID do cartão a ser desabilitado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void disable(String cardId) throws IOException {
        delete(cardId);
    }

    /**
     * Verifica se um cartão está ativo pelo ID do cartão.
     *
     * @param cardId O ID do cartão.
     * @return True se o cartão existir, ou false se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public boolean getStatusByCardId(String cardId) throws IOException {
        return getById(cardId) != null;
    }

    /**
     * Deleta um cartão pelo ID do cartão.
     *
     * @param c1Id O ID do cartão a ser deletado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void delete(String c1Id) throws IOException {
        CardManager cardManager = new CardManager();
        List<Card> cards = cardManager.lerConteudoArquivo();

        // Remover o cartão da lista com base no ID fornecido
        cards.removeIf(card -> card.getIdCartao().equals(c1Id));

        // Salvar a lista atualizada no arquivo JSON
        cardManager.salvarCardsNoArquivo(cards);
    }

    /**
     * Recupera um cartão pelo seu ID.
     *
     * @param id O ID do cartão.
     * @return O cartão correspondente, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public Card getById(String id) throws IOException {
        CardManager cardManager = new CardManager();
        List<Card> cards = cardManager.lerConteudoArquivo();
        for (Card card : cards) {
            if (card.getIdCartao().equals(id)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Deleta todos os cartões do banco de dados.
     *
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void deleteAllCards() throws IOException {
        CardManager cardManager = new CardManager();
        cardManager.limparArquivoJson();
    }
}