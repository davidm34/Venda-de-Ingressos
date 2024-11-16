package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.UUID;

public class RegisterController {

    @FXML
    private TextField cpfTextField;

    @FXML
    private TextField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    void onHelloButtonClick(ActionEvent event) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        String name = nameTextField.getText();
        String password  = enterPasswordField.getText();
        String cpf = cpfTextField.getText();
        String email = emailTextField.getText();
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        boolean register = usuarioManager.adicionarUsuarioNoArquivo(name, password, cpf, email, false, id);
        if (register) {
            System.out.println("Usuario Registrado");
        } else {
            System.out.println("Usuario não Registrado");
        }
    }

}
