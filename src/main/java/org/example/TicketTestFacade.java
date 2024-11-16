package org.example;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * A facade for managing tickets for events.
 */
public class TicketTestFacade {

    /**
     * Default constructor for TicketTestFacade.
     */
    public TicketTestFacade(){
    }

    /**
     * Creates a new ticket for a specific event.
     *
     * @param eventId The ID of the event for which the ticket is being created.
     * @param price The price of the ticket.
     * @param seat The seat assigned to the ticket.
     * @return The ID of the newly created ticket, or null if creation failed.
     * @throws IOException If an error occurs while reading or writing data.
     */
    public String create(String eventId, Double price, String seat) throws IOException {
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        Ingresso ingresso = new Ingresso(eventId, price, seat, id, true);
        IngressoManager ingressoManager = new IngressoManager();
        if(ingressoManager.adicionarIngressoNoArquivo(ingresso)){
            return id;
        }
        return null;
    }

    /**
     * Retrieves the event ID associated with a ticket by its ID.
     *
     * @param id The ID of the ticket.
     * @return The event ID associated with the ticket, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public String getEventByTicketId(String id) throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        List<Ingresso> ingressos = ingressoManager.lerConteudoArquivo();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id)) {
                return ingresso.getIdEvento();
            }
        }
        return null;
    }

    /**
     * Retrieves the price of a ticket by its ID.
     *
     * @param id The ID of the ticket.
     * @return The price of the ticket, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public Double getPriceByTicketId(String id) throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        List<Ingresso> ingressos = ingressoManager.lerConteudoArquivo();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id)) {
                return ingresso.getPreco();
            }
        }
        return null;
    }

    /**
     * Cancels a ticket by its ID, marking it as inactive.
     *
     * @param ticketId The ID of the ticket to be canceled.
     * @throws IOException If an error occurs while reading or writing data.
     */
    public void cancelByTicketId(String ticketId) throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        List<Ingresso> ingressos = ingressoManager.lerConteudoArquivo();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(ticketId)) {
                ingresso.setIngressoAtivo(false); // Marca o ticket como inativo
                ingressoManager.save(ingresso); // Atualiza o ticket no sistema de persistência
            }
        }
    }

    /**
     * Retrieves the seat assigned to a ticket by its ID.
     *
     * @param id The ID of the ticket.
     * @return The seat assigned to the ticket, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public String getSeatByTicketId(String id) throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        List<Ingresso> ingressos = ingressoManager.lerConteudoArquivo();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id)) {
                return ingresso.getAssento();
            }
        }
        return null;
    }

    /**
     * Reactivates a ticket by its ID, marking it as active.
     *
     * @param id The ID of the ticket to be reactivated.
     * @throws IOException If an error occurs while reading or writing data.
     */
    public void reactiveById(String id) throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        List<Ingresso> ingressos = ingressoManager.lerConteudoArquivo();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id)) {
                ingresso.setIngressoAtivo(true); // Marca o ticket como ativo
                ingressoManager.save(ingresso); // Atualiza o ticket no sistema de persistência
            }
        }
    }

    /**
     * Retrieves a ticket by its ID.
     *
     * @param id The ID of the ticket.
     * @return The ticket associated with the given ID, or null if not found.
     * @throws IOException If an error occurs while reading data.
     */
    public Ingresso getById(String id) throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        List<Ingresso> ingressos = ingressoManager.lerConteudoArquivo();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id)) {
                return ingresso;
            }
        }
        return null;
    }

    /**
     * Checks if a ticket is active by its ID.
     *
     * @param id The ID of the ticket.
     * @return True if the ticket is active, false otherwise.
     * @throws IOException If an error occurs while reading data.
     */
    public boolean getIsActive(String id) throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        List<Ingresso> ingressos = ingressoManager.lerConteudoArquivo();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id)) {
                return ingresso.getIngressoAtivo();
            }
        }
        return false;
    }

    /**
     * Deletes all tickets from the storage.
     *
     * @throws IOException If an error occurs while clearing data.
     */
    public void deleteAllTickets() throws IOException {
        IngressoManager ingressoManager = new IngressoManager();
        ingressoManager.limparArquivoJson();
    }
}
