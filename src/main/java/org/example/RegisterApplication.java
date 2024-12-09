package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principal para a aplicação de cadastro e login.
 * Responsável por inicializar e exibir a interface gráfica.
 *
 * @author David Neves Dias
 */
public class RegisterApplication extends Application {

    private static Stage stage;

    private static Scene loginScene;

    private static Scene registerScene;

    /**
     * Método principal de inicialização da aplicação JavaFX.
     *
     * @param stage O palco principal onde as cenas serão exibidas.
     * @throws IOException Se houver erro ao carregar os arquivos FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        RegisterApplication.stage = stage; // Armazena o Stage principal.

        stage.setTitle("Página de Cadastro!");

        // Carrega a cena de login.
        FXMLLoader loginLoader = new FXMLLoader(RegisterApplication.class.getResource("/org/example/main/login.fxml"));
        loginScene = new Scene(loginLoader.load(), 520, 344);

        // Carrega a cena de registro.
        FXMLLoader registerLoader = new FXMLLoader(RegisterApplication.class.getResource("/org/example/main/register.fxml"));
        registerScene = new Scene(registerLoader.load(), 520, 344);

        // Configura a cena inicial como a tela de registro e exibe.
        stage.setScene(registerScene);
        stage.show();
    }

    /**
     * Método principal para iniciar a aplicação.
     *
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        launch();
    }

}
