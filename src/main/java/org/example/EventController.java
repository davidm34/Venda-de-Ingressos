package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controlador responsável pela tela de listagem de eventos.
 * Gerencia a exibição dos eventos disponíveis e permite que o usuário selecione um evento para compra de ingressos.
 *
 * @author David Neves Dias
 */
public class EventController {

    @FXML
    private TableView<Evento> tableEvent; // Tabela que exibe os eventos disponíveis.

    @FXML
    private TableColumn<Evento, String> nameEvent; // Coluna que exibe o nome do evento.

    @FXML
    private TableColumn<Evento, String> categoryEvent; // Coluna que exibe a categoria do evento.

    @FXML
    private TableColumn<Evento, String> dateEvent; // Coluna que exibe a data do evento.

    @FXML
    private TableColumn<Evento, Void> buyEvent; // Coluna com botão para comprar ingressos.

    @FXML
    private ComboBox<String> languageComboBox; // ComboBox para seleção do idioma.

    private BuyController buyController; // Controlador responsável pela tela de compra.

    private ObservableList<Evento> eventos; // Lista de eventos exibida na tabela.

    private ResourceBundle bundle; // Bundle de recursos para gerenciamento de textos internacionalizados.

    /**
     * Define o controlador responsável pela tela de compra.
     *
     * @param buyController Instância do controlador de compra.
     */
    public void setBuyController(BuyController buyController) {
        this.buyController = buyController;
    }

    /**
     * Inicializa os componentes da interface.
     * Configura a tabela, o idioma padrão e os textos da interface.
     */
    @FXML
    public void initialize() {
        // Inicializa o idioma padrão (Português)
        Locale locale = new Locale("pt", "BR");
        bundle = ResourceBundle.getBundle("lang", locale);
        languageComboBox.setValue("Português");

        // Preenche o ComboBox com as opções de idioma
        ObservableList<String> languages = FXCollections.observableArrayList("Português", "Inglês");
        languageComboBox.setItems(languages);

        // Adiciona listener para detectar mudanças de idioma
        languageComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("Inglês")) {
                    atualizarIdioma(new Locale("en", "US"));
                } else {
                    atualizarIdioma(new Locale("pt", "BR"));
                }
            }
        });

        // Atualiza os textos de interface com base na linguagem
        atualizarTextos();

        // Configura as células da tabela
        nameEvent.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));

        dateEvent.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(formatarData(cellData.getValue().getData())));

        categoryEvent.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));

        // Preenche a lista de eventos
        atualizarEventos();
        tableEvent.setItems(eventos);

        // Adiciona a coluna de botão
        adicionarColunaDeBotao();
    }

    /**
     * Formata uma data para o padrão "dd/MM/yyyy".
     *
     * @param data A data a ser formatada.
     * @return String representando a data formatada.
     */
    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    /**
     * Adiciona a coluna de botão para compra de ingressos na tabela de eventos.
     */
    public void adicionarColunaDeBotao() {
        // Remover coluna antiga, se existir
        tableEvent.getColumns().remove(buyEvent);

        // Cria nova coluna com o texto atualizado
        buyEvent = new TableColumn<>(bundle.getString("coluna.comprar")); // Traduz o título da coluna de comprar

        Callback<TableColumn<Evento, Void>, TableCell<Evento, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Evento, Void> call(final TableColumn<Evento, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button(bundle.getString("botao.comprar")); // Traduz o texto do botão

                    {
                        btn.setOnAction(event -> {
                            Evento evento = getTableView().getItems().get(getIndex());

                            // Use a instância correta do controlador
                            if (buyController != null) {
                                buyController.start(evento);
                                ControllerScreens.removeScene();
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null); // Não exibe nada para células vazias
                        } else {
                            btn.setText(bundle.getString("botao.comprar")); // Atualiza o texto do botão
                            setGraphic(btn); // Exibe o botão
                        }
                    }
                };
            }
        };

        buyEvent.setCellFactory(cellFactory);
        tableEvent.getColumns().add(buyEvent); // Adiciona a nova coluna ao TableView
    }

    /**
     * Atualiza o idioma da interface.
     *
     * @param newLocale O novo Locale a ser utilizado.
     */
    private void atualizarIdioma(Locale newLocale) {
        // Atualiza o ResourceBundle para o novo idioma
        bundle = ResourceBundle.getBundle("lang", newLocale);

        // Atualiza os textos de interface
        atualizarTextos();

        // Atualiza os eventos
        atualizarEventos();

        // Recria a coluna de botão com o novo idioma
        adicionarColunaDeBotao();
    }

    /**
     * Atualiza os textos exibidos na interface com base no idioma atual.
     */
    private void atualizarTextos() {
        nameEvent.setText(bundle.getString("label.nome"));
        categoryEvent.setText(bundle.getString("label.categoria"));
        dateEvent.setText(bundle.getString("label.data"));
    }

    /**
     * Atualiza a lista de eventos com base no idioma atual.
     */
    private void atualizarEventos() {
        eventos = FXCollections.observableArrayList(
                new Evento(bundle.getString("evento.show_rock"), bundle.getString("evento.show_ao_vivo"), new Date(1734624000000L), "1"),
                new Evento(bundle.getString("evento.feira_tecnologia"), bundle.getString("evento.exposicao"), new Date(1734710400000L), "2"),
                new Evento(bundle.getString("evento.congresso_medico"), bundle.getString("evento.conferencia"), new Date(1734796800000L), "3"),
                new Evento(bundle.getString("evento.workshop_programacao"), bundle.getString("evento.workshop"), new Date(1734969600000L), "5"),
                new Evento(bundle.getString("evento.corrida_rua"), bundle.getString("evento.esporte"), new Date(1735056000000L), "6"),
                new Evento(bundle.getString("evento.encontro_literario"), bundle.getString("evento.literatura"), new Date(1735142400000L), "7"),
                new Evento(bundle.getString("evento.show_stand_up"), bundle.getString("evento.comedia"), new Date(1735228800000L), "8"),
                new Evento(bundle.getString("evento.feira_gastronomica"), bundle.getString("evento.gastronomia"), new Date(1735315200000L), "9"),
                new Evento(bundle.getString("evento.palestra_motivacional"), bundle.getString("evento.palestra"), new Date(1735401600000L), "10"),
                new Evento(bundle.getString("evento.festa_ano_novo"), bundle.getString("evento.show_ao_vivo"), new Date(1735488000000L), "11"),
                new Evento(bundle.getString("evento.competicao_culinaria"), bundle.getString("evento.gastronomia"), new Date(1735574400000L), "12"),
                new Evento(bundle.getString("evento.maratona_filmes"), bundle.getString("evento.cinema"), new Date(1735660800000L), "13")
        );
        tableEvent.setItems(eventos);
    }
}
