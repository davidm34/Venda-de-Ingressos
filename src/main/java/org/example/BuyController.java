package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.*;

/**
 * Controlador responsável pela interface de compra de ingressos.
 * Gerencia eventos, traduções e interação com o modelo de dados.
 *
 * @author David Neves Dias
 */
public class BuyController {

    @FXML
    private Text dateEvent;

    @FXML
    private Button loginButton;

    @FXML
    private Text nameEvent;

    @FXML
    private Spinner<Integer> numberOfTickets;

    @FXML
    private Text totalPrice;

    @FXML
    private Label textPriceTicket;

    @FXML
    private Label textQuantity;

    @FXML
    private Label textTotal;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Text purchaseMade;

    private final int ticketPrice = 100; // Preço fixo por ingresso

    private ResourceBundle bundle;

    /**
     * Método inicializador chamado automaticamente pelo JavaFX.
     * Configura os componentes iniciais como Spinner, ComboBox e idioma padrão.
     */
    @FXML
    public void initialize() {
        numberOfTickets.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        configureLanguageComboBox();
        atualizarIdioma(new Locale("pt", "BR"));
        numberOfTickets.valueProperty().addListener((observable, oldValue, newValue) -> updatePrice());
    }

    /**
     * Ação disparada ao clicar no botão de compra de ingressos.
     * Verifica se o usuário está autenticado, adiciona os ingressos ao arquivo JSON e exibe uma notificação.
     *
     * @param event Evento do clique no botão.
     */
    @FXML
    void buyTicketClick(ActionEvent event) {
        int selectedTickets = numberOfTickets.getValue() == null ? 1 : numberOfTickets.getValue();
        String userId = SessionManager.getCurrentUserId();
        if (userId != null) {
            try {
                adicionarIngressosAoArquivo(userId, selectedTickets);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(bundle.getString("alerta.titulo"));
                alert.setHeaderText(null);
                alert.setContentText(bundle.getString("alerta.mensagem") + " " + selectedTickets + " " + bundle.getString("alerta.ingressos"));
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuário não está autenticado!");
        }
    }

    /**
     * Configura os detalhes do evento exibido na interface.
     *
     * @param evento Objeto representando os detalhes do evento.
     */
    public void start(Evento evento) {
        nameEvent.setText(evento.getNome());
        dateEvent.setText(evento.formatDate());
        updatePrice();
    }

    /**
     * Atualiza o preço total dinamicamente com base no número de ingressos selecionados.
     */
    private void updatePrice() {
        int selectedTickets = numberOfTickets.getValue() == null ? 1 : numberOfTickets.getValue();
        int total = selectedTickets * ticketPrice;
        totalPrice.setText("$ " + total);
    }

    /**
     * Configura o ComboBox de idiomas, adicionando opções e listeners para detecção de mudanças.
     */
    private void configureLanguageComboBox() {
        languageComboBox.getItems().addAll("Português", "Inglês");
        languageComboBox.setValue("Português");
        languageComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("Inglês")) {
                    atualizarIdioma(new Locale("en", "US"));
                } else {
                    atualizarIdioma(new Locale("pt", "BR"));
                }
            }
        });
    }

    /**
     * Atualiza os textos da interface de acordo com o idioma selecionado.
     *
     * @param locale Locale representando o idioma a ser aplicado.
     */
    private void atualizarIdioma(Locale locale) {
        bundle = ResourceBundle.getBundle("lang", locale);
        loginButton.setText(bundle.getString("botao.comprar"));
        textPriceTicket.setText(bundle.getString("label.preco_unitario"));
        textQuantity.setText(bundle.getString("label.quantidade"));
        textTotal.setText(bundle.getString("label.total"));
        int selectedTickets = numberOfTickets.getValue() == null ? 1 : numberOfTickets.getValue();
        totalPrice.setText("$" + (selectedTickets * ticketPrice));
    }

    /**
     * Adiciona os ingressos comprados ao arquivo JSON associado ao usuário autenticado.
     *
     * @param userId ID do usuário autenticado.
     * @param quantidadeIngressos Número de ingressos comprados.
     * @throws IOException Se houver erro ao acessar ou salvar o arquivo.
     */
    private void adicionarIngressosAoArquivo(String userId, int quantidadeIngressos) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(userId)) {
                usuarios.remove(usuario);
                for (int i = 0; i < quantidadeIngressos; i++) {
                    UUID uuid = UUID.randomUUID();
                    String id = String.valueOf(uuid);
                    Ingresso ingresso = new Ingresso(nameEvent.getText(), dateEvent.getText(), id, 100.00);
                    usuario.addIngresso(ingresso);
                }
                usuarios.add(usuario);
                usuarioManager.salvarUsuarios(usuarios);
            }
        }
    }
}
