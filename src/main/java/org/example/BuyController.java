package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class BuyController {



    @FXML
    private Text dateEvent;

    @FXML
    private Button loginButton;

    @FXML
    private Text nameEvent;

    @FXML
    void buyTicketClick(ActionEvent event) {

    }

    void addEventToTicket(Evento evento){
        nameEvent.setText(evento.getNome());
        dateEvent.setText(evento.getData().toString());
    }



}
