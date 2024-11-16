package org.example;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Classe responsável pela fachada de gerenciamento de compras,
 * fornecendo métodos para criar e recuperar informações sobre compras.
 *
 * @author David Neves Dias
 */
public class PurchaseTestFacade {

    public PurchaseTestFacade() {
    }

    /**
     * Cria uma nova compra e a adiciona ao sistema.
     *
     * @param email O email do usuário que está realizando a compra.
     * @param eventId O ID do evento para o qual a compra está sendo feita.
     * @param cardId O ID do cartão usado na compra.
     * @param seat O assento escolhido para a compra.
     * @return O ID da compra criada.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String create(String email, String eventId, String cardId, String seat) throws IOException {
        // Gera um ID para a compra
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        // Cria e adiciona a compra ao arquivo
        Compra compra = new Compra(email, id, eventId, cardId, seat);
        CompraManager compraManager = new CompraManager();
        compraManager.adicionarCompraNoArquivo(compra);
        return id;
    }

    /**
     * Recupera uma compra pelo seu ID.
     *
     * @param id O ID da compra.
     * @return A compra correspondente, ou null se não encontrada.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public Compra getById(String id) throws IOException {
        CompraManager compraManager = new CompraManager();
        List<Compra> compras = compraManager.lerConteudoArquivo();
        for (Compra compra : compras) {
            if (compra.getIdCompra().equals(id)) {
                return compra;
            }
        }
        return null;
    }

    /**
     * Recupera o ID do evento associado a uma compra pelo seu ID.
     *
     * @param id O ID da compra.
     * @return O ID do evento, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getEventByPurchaseId(String id) throws IOException {
        Compra compra = getById(id);
        return (compra != null) ? compra.getIdEvento() : null;
    }

    /**
     * Recupera o email do usuário que realizou uma compra pelo seu ID.
     *
     * @param id O ID da compra.
     * @return O email do usuário, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getUserLoginByPurchaseId(String id) throws IOException {
        Compra compra = getById(id);
        return (compra != null) ? compra.getEmail() : null;
    }

    /**
     * Recupera o ID do ticket de uma compra pelo seu ID.
     *
     * @param id O ID da compra.
     * @return O ID do ticket, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getTicketByPurchaseId(String id) throws IOException {
        Compra compra = getById(id);
        return (compra != null) ? compra.getIdCompra() : null;
    }

    /**
     * Conta quantas vezes um email aparece entre as compras registradas.
     *
     * @param id O ID da compra.
     * @return A quantidade de ocorrências do email.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getUserMailBoxByPurchaseId(String id) throws IOException {
        CompraManager compraManager = new CompraManager();
        List<Compra> compras = compraManager.lerConteudoArquivo();
        return (int) compras.stream().filter(compra -> compra.getIdCompra().equals(id)).count();
    }

    /**
     * Recupera o cartão usado na compra pelo ID da compra.
     *
     * @param id O ID da compra.
     * @return O cartão correspondente, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public Card getCardByPurchaseID(String id) throws IOException {
        Compra compra = getById(id);
        if (compra != null) {
            CardTestFacade cardTestFacade = new CardTestFacade();
            return cardTestFacade.getById(compra.getIdCard());
        }
        return null;
    }

    /**
     * Deleta todas as compras registradas no sistema.
     *
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void deleteAllPurchases() throws IOException {
        CompraManager compraManager = new CompraManager();
        compraManager.limparArquivoJson();
    }
}
