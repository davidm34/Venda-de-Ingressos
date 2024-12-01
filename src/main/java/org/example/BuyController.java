package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;

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

    private int ticketPrice = 100; // Preço fixo por ingresso

    @FXML
    void buyTicketClick(ActionEvent event) {
        System.out.println("Ingressos comprados: " + numberOfTickets.getValue());
        System.out.println("Preço total: R$ " + totalPrice.getText());
    }

    void start(Evento evento) {
        // Adicionando o evento desejado para o ingresso
        nameEvent.setText(evento.getNome());
        dateEvent.setText(evento.formatDate());

        // Adicionando a quantidade de ingresso máximo por usuário
        numberOfTickets.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));

        // Atualizar o preço de forma dinâmica
        updatePrice();
        numberOfTickets.valueProperty().addListener((observable, oldValue, newValue) -> updatePrice());
    }

    private void updatePrice() {
        int selectedTickets = numberOfTickets.getValue();
        int total = selectedTickets * ticketPrice;
        totalPrice.setText("$ " + total);
    }
}
