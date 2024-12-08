package org.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventController {

    @FXML
    private TableView<Evento> tableEvent;

    private BuyController buyController;

    public void setBuyController(BuyController buyController){
        this.buyController = buyController;
    }

    @FXML
    private TableColumn<Evento, String> nameEvent;

    @FXML
    private TableColumn<Evento, String> categoryEvent;

    @FXML
    private TableColumn<Evento, String> dateEvent;

    @FXML
    private TableColumn<Evento, Void> buyEvent;

    private ObservableList<Evento> eventos;

    @FXML
    public void initialize() {
        nameEvent.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));

        dateEvent.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(formatarData(cellData.getValue().getData())));

        categoryEvent.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));

        eventos = FXCollections.observableArrayList(
                new Evento("Show de Rock", "Show ao Vivo", new Date(1734624000000L), "1"), // 20/12/2024
                new Evento("Feira de Tecnologia", "Exposição", new Date(1734710400000L), "2"), // 21/12/2024
                new Evento("Congresso Médico", "Conferência", new Date(1734796800000L), "3"), // 22/12/2024
                new Evento("Workshop de Programação", "Workshop", new Date(1734969600000L), "5"), // 24/12/2024
                new Evento("Corrida de Rua", "Esporte", new Date(1735056000000L), "6"), // 25/12/2024
                new Evento("Encontro Literário", "Literatura", new Date(1735142400000L), "7"), // 26/12/2024
                new Evento("Show de Stand-Up", "Comédia", new Date(1735228800000L), "8"), // 27/12/2024
                new Evento("Feira Gastronômica", "Gastronomia", new Date(1735315200000L), "9"), // 28/12/2024
                new Evento("Palestra Motivacional", "Palestra", new Date(1735401600000L), "10"), // 29/12/2024
                new Evento("Festa de Ano Novo", "Show ao Vivo", new Date(1735488000000L), "11"), // 30/12/2024
                new Evento("Competição de Culinária", "Gastronomia", new Date(1735574400000L), "12"), // 31/12/2024
                new Evento("Maratona de Filmes", "Cinema", new Date(1735660800000L), "13") // 01/01/2025
        );

        tableEvent.setItems(eventos);
        adicionarColunaDeBotao();
    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    void adicionarColunaDeBotao() {
        buyEvent = new TableColumn<>("COMPRAR");

        Callback<TableColumn<Evento, Void>, TableCell<Evento, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Evento, Void> call(final TableColumn<Evento, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Comprar");

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
                            setGraphic(btn); // Exibe o botão
                        }
                    }
                };
            }
        };

        buyEvent.setCellFactory(cellFactory);
        tableEvent.getColumns().add(buyEvent);
    }



}


