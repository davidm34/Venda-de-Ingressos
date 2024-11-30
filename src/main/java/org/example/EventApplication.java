package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EventApplication extends Application {
    private static Stage stage;

    private static Scene buyScene; // Cena para a tela de compra
    private static Scene eventScene; // Cena para a lista de eventos

    @Override
    public void start(Stage stage) throws IOException {
        EventApplication.stage = stage; // Atribuir o Stage à variável estática.
        stage.setTitle("Página de Cadastro!");

        // Carregar a tela de compra
        FXMLLoader buyLoader = new FXMLLoader(getClass().getResource("/org/example/main/buy.fxml"));
        Parent buyRoot = buyLoader.load();
        BuyController buyController = buyLoader.getController(); // Instância inicializada do BuyController

        // Carregar a tela de lista de eventos
        FXMLLoader eventLoader = new FXMLLoader(getClass().getResource("/org/example/main/listOfEvents.fxml"));
        Parent eventRoot = eventLoader.load();
        EventController eventController = eventLoader.getController(); // Instância inicializada do EventController

        // Passar o BuyController para o EventController
        eventController.setBuyController(buyController);

        // Configurar cenas
        buyScene = new Scene(buyRoot, 520, 344);
        eventScene = new Scene(eventRoot, 833, 405);

        // Configurar a cena inicial e exibir o Stage
        stage.setScene(eventScene);
        stage.show();
    }

    public static void changeScreen(Boolean validarCadastro) {
        if (validarCadastro) {
            stage.setScene(buyScene);
        } else {
            stage.setScene(eventScene);
        }
    }
}
