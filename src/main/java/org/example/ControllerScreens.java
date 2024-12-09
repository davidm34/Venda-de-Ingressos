package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Stack;
import java.io.IOException;

public class ControllerScreens extends Application {

    private static Stage stage;

    private static Scene sceneRegister;

    private static Scene sceneLogin;

    private static Scene sceneEvents;

    private static Scene sceneBuy;

    private static Stack<Scene> scenes = new Stack<>();

    @Override
    public void start(Stage stage) throws IOException {
        ControllerScreens.stage = stage; // Atribuir o Stage à variável estática.

        stage.setTitle("Ticket Sales!");

        // Carregar a Tela de Registro
        FXMLLoader fxmlLoader1 = new FXMLLoader(RegisterApplication.class.getResource("/org/example/main/register.fxml"));
        sceneRegister = new Scene(fxmlLoader1.load(), 1024, 566);
        RegisterController registerController = fxmlLoader1.getController();
        registerController.setControllerScreens(this); // Passa a instância atual


        // Carregar a Tela de Login
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("/org/example/main/login.fxml"));
        sceneLogin = new Scene(fxmlLoader.load(), 1024, 566);
        LoginController loginController = fxmlLoader.getController();

        // Carregar a Tela de Compra
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
        sceneBuy = new Scene(buyRoot, 1024, 566);
        sceneEvents = new Scene(eventRoot, 1024, 566);

        scenes.push(sceneBuy);
        scenes.push(sceneEvents);
        scenes.push(sceneLogin);
        scenes.push(sceneRegister);

        // Configura a cena inicial e exibe o stage.
        stage.setScene(scenes.peek());
        stage.show();
    }

    public static void removeScene() {
        if (!scenes.isEmpty()) {
            scenes.pop();
            stage.setScene(scenes.peek());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
