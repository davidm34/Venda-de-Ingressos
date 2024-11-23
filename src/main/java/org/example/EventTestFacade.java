package org.example;

import java.io.IOException;
import java.util.UUID;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Classe responsável pela fachada de gerenciamento de eventos,
 * fornecendo métodos para criar, recuperar e deletar eventos.
 *
 * @author David Neves Dias
 */
public class EventTestFacade {

    public EventTestFacade() {
    }

    /**
     * Remove um assento de um evento específico.
     *
     * @param seat O identificador do assento a ser removido.
     * @param eventId O ID do evento associado ao assento.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void removeSeatByEventId(String seat, String eventId) throws IOException {
        AssentoManager assentoManager = new AssentoManager();
        List<Assento> assentos = assentoManager.lerConteudoArquivoAssento();

        // Filtrar os assentos que não correspondem ao assento e ao evento fornecido
        List<Assento> assentosFiltrados = assentos.stream()
                .filter(assento -> !(assento.getSeat().equals(seat) && assento.getIdEvento().equals(eventId)))
                .collect(Collectors.toList());

        // Salvar a lista atualizada de assentos
        assentoManager.salvarAssentosNoArquivo(assentosFiltrados);
    }

    /**
     * Adiciona um novo assento a um evento específico.
     *
     * @param seat O identificador do assento a ser adicionado.
     * @param eventId O ID do evento ao qual o assento será associado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void addSeatByEventId(String seat, String eventId) throws IOException {
        EventoManager eventoManager = new EventoManager();
        List<Evento> eventos = eventoManager.lerConteudoArquivo();
        for (Evento evento : eventos) {
            if (evento.getId().equals(eventId)) {
                Assento assento = new Assento(eventId, seat);
                AssentoManager assentoManager = new AssentoManager();
                assentoManager.save(assento);  // Adiciona o assento diretamente na lista do evento
                break;
            }
        }
    }

    /**
     * Cria um novo evento e o adiciona ao sistema.
     *
     * @param name O nome do evento.
     * @param description A descrição do evento.
     * @param date A data do evento.
     * @return O ID do novo evento criado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String create(String name, String description, Date date) throws IOException {
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        Evento evento = new Evento(name, description, date, id);
        EventoManager eventoManager = new EventoManager();
        eventoManager.adicionarEventoNoArquivo(evento);
        return id;
    }

    /**
     * Recupera um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return O evento correspondente, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public Evento getById(String id) throws IOException {
        EventoManager eventoManager = new EventoManager();
        List<Evento> eventos = eventoManager.lerConteudoArquivo();
        for (Evento evento : eventos) {
            if (evento.getId().equals(id)) {
                return evento;
            }
        }
        return null;
    }

    /**
     * Recupera o nome de um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return O nome do evento, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getNameByEventId(String id) throws IOException {
        Evento evento = getById(id);
        return (evento != null) ? evento.getNome() : null;
    }

    /**
     * Recupera a lista de assentos de um evento pelo seu ID.
     *
     * @param eventId O ID do evento.
     * @return Uma string com os assentos separados por vírgulas, ou uma string vazia se não houver assentos.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getSeatsByEventId(String eventId) throws IOException {
        AssentoManager assentoManager = new AssentoManager();
        List<Assento> assentos = assentoManager.lerConteudoArquivoAssento();

        // Filtrar assentos do evento específico
        List<String> seats = assentos.stream()
                .filter(assento -> assento.getIdEvento().equals(eventId))
                .map(Assento::getSeat)
                .collect(Collectors.toList());

        // Retornar a lista de assentos como uma string separada por vírgulas ou uma string vazia
        return seats.isEmpty() ? "" : String.join(", ", seats);
    }

    /**
     * Recupera a descrição de um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return A descrição do evento, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getDescriptionByEventId(String id) throws IOException {
        Evento evento = getById(id);
        return (evento != null) ? evento.getDescricao() : null;
    }

    /**
     * Recupera o ano da data de um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return O ano da data do evento, ou 0 se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getYearByEventId(String id) throws IOException {
        Evento evento = getById(id);
        return (evento != null) ? evento.getData().getYear() : 0;
    }

    /**
     * Recupera o mês da data de um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return O mês da data do evento, ou 0 se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getMonthByEventId(String id) throws IOException {
        Evento evento = getById(id);
        return (evento != null) ? evento.getData().getMonth() : 0;
    }

    /**
     * Recupera o dia da data de um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return O dia da data do evento, ou 0 se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getDayByEventId(String id) throws IOException {
        Evento evento = getById(id);
        return (evento != null) ? evento.getData().getDay() : 0;
    }

    /**
     * Recupera a data de um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return A data do evento, ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public Date getDateByEventId(String id) throws IOException {
        Evento evento = getById(id);
        return (evento != null) ? evento.getData() : null;
    }

    /**
     * Verifica se um evento está ativo pelo seu ID.
     *
     * @param id O ID do evento.
     * @return true se o evento estiver ativo, false caso contrário.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public boolean getIsActiveByEventId(String id) throws IOException {
        Evento evento = getById(id);
        return (evento != null) && evento.isAtivo();
    }

    /**
     * Adiciona um evento ao banco de dados mesmo que a data esteja no passado.
     *
     * @param name O nome do evento.
     * @param description A descrição do evento.
     * @param date A data do evento.
     * @return O ID do novo evento criado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String addEventInDataBaseWithPastDate(String name, String description, Date date) throws IOException {
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        Evento evento = new Evento(name, description, date, id);
        EventoManager eventoManager = new EventoManager();
        eventoManager.save(evento);
        return id;
    }

    /**
     * Recupera a quantidade de comentários de um evento pelo seu ID.
     *
     * @param id O ID do evento.
     * @return A quantidade de comentários.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getCommentQuantityByEventId(String id) throws IOException {
        AvaliacaoManager avaliacaoManager = new AvaliacaoManager();
        List<Avaliacao> avaliacoes = avaliacaoManager.lerConteudoArquivo();
        return (int) avaliacoes.stream().filter(avaliacao -> avaliacao.getIdEvento().equals(id)).count();
    }

    /**
     * Deleta todos os eventos do sistema.
     *
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void deleteAllEvents() throws IOException {
        EventoManager eventoManager = new EventoManager();
        eventoManager.limparArquivoJson();
    }
}
