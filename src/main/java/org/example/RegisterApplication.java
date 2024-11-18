package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterApplication extends Application {

    private static Stage stage;

    private static Scene scene;

    private static Scene scene1;

    @Override
    public void start(Stage stage) throws IOException {
        RegisterApplication.stage = stage; // Atribuir o Stage à variável estática.

        stage.setTitle("Página de Cadastro!");

        // Inicializa as cenas e atribui às variáveis de instância.
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("/org/example/main/login.fxml"));
        scene = new Scene(fxmlLoader.load(), 520, 344);

        FXMLLoader fxmlLoader1 = new FXMLLoader(RegisterApplication.class.getResource("/org/example/main/register.fxml"));
        scene1 = new Scene(fxmlLoader1.load(), 520, 344);

        // Configura a cena inicial e exibe o stage.
        stage.setScene(scene1);
        stage.show();
    }

    public static void changeScreen(Boolean validarCadastro) {
        if (validarCadastro) {
            stage.setScene(scene);
        } else {
            stage.setScene(scene1);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
