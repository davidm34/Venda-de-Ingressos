package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    private TextField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;

    private static final String USERS_FILE = "usuarios.json";

    public void onHelloButtonClick(ActionEvent actionEvent) throws IOException {
        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();
        if (validateLogin(username, password)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }

    private boolean validateLogin(String username, String password) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(username) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;

    }

}
