package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

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

    private int ticketPrice = 100; // Preço fixo por ingresso

    private ResourceBundle bundle;

    @FXML
    public void initialize() {
        // Configura o Spinner com valor inicial para evitar null
        numberOfTickets.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));

        // Configuração inicial do ComboBox de idiomas
        configureLanguageComboBox();

        // Inicializa o idioma padrão (Português)
        atualizarIdioma(new Locale("pt", "BR"));

        // Atualiza o preço dinamicamente conforme o número de ingressos
        numberOfTickets.valueProperty().addListener((observable, oldValue, newValue) -> updatePrice());
    }

    @FXML
    void buyTicketClick(ActionEvent event) {
        int selectedTickets = numberOfTickets.getValue() == null ? 1 : numberOfTickets.getValue();

        // Obtem o ID do usuário autenticado
        String userId = SessionManager.getCurrentUserId();
        if (userId != null) {
            try {
                // Adiciona os ingressos ao arquivo JSON
                adicionarIngressosAoArquivo(userId, selectedTickets);

                // Exibe a notificação
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

    public void start(Evento evento) {
        // Configura os textos do evento exibido
        nameEvent.setText(evento.getNome());
        dateEvent.setText(evento.formatDate());
        updatePrice(); // Garante que o preço inicial esteja correto
    }

    private void updatePrice() {
        int selectedTickets = numberOfTickets.getValue() == null ? 1 : numberOfTickets.getValue();
        int total = selectedTickets * ticketPrice;
        totalPrice.setText("$ " + total);
    }

    private void configureLanguageComboBox() {
        // Adiciona os idiomas ao ComboBox
        languageComboBox.getItems().addAll("Português", "Inglês");
        languageComboBox.setValue("Português");

        // Listener para detectar mudanças no ComboBox e alterar o idioma
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

    private void atualizarIdioma(Locale locale) {
        // Atualiza o ResourceBundle para o idioma selecionado
        bundle = ResourceBundle.getBundle("lang", locale);

        // Atualiza os textos exibidos na interface
        loginButton.setText(bundle.getString("botao.comprar"));
        textPriceTicket.setText(bundle.getString("label.preco_unitario"));
        textQuantity.setText(bundle.getString("label.quantidade"));
        textTotal.setText(bundle.getString("label.total"));

        // Atualiza o preço total
        int selectedTickets = numberOfTickets.getValue() == null ? 1 : numberOfTickets.getValue();
        totalPrice.setText("$" + (selectedTickets * ticketPrice));
    }

    private void adicionarIngressosAoArquivo(String userId, int quantidadeIngressos) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for(Usuario usuario: usuarios){
            if(usuario.getId().equals(userId)){
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
