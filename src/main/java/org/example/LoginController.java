package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    private TextField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Text validateLogin;

    @FXML
    private Text emailNotFilledIn;

    @FXML
    private Text passwordNotFilledIn;

    private static final String USERS_FILE = "usuarios.json";

    private ControllerScreens controllerScreens;

    public void setControllerScreens(ControllerScreens controllerScreens) {
        this.controllerScreens = controllerScreens;
    }

    public void onHelloButtonClick(ActionEvent actionEvent) throws IOException {
        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();
        boolean check = checkIfThereIsNoEmptyField(username, password);
        if (check) {
            if (validateLogin(username, password)) {
                controllerScreens.removeScene();
            } else {
                validateLogin.setText("Usuário ou senha inválidos.");
            }
        }
    }

    private boolean validateLogin(String email, String password) throws IOException {

        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;

    }

    private boolean checkIfThereIsNoEmptyField(String email, String password){
        boolean allFieldsFilled = true;

        if (email == null || email.trim().isEmpty()) {
            emailNotFilledIn.setText("Campo não Preenchido");
            allFieldsFilled = false;
        } else {
            emailNotFilledIn.setText(""); // Limpa o texto de erro
        }

        if (password == null || password.trim().isEmpty()) {
            passwordNotFilledIn.setText("Campo não Preenchido");
            allFieldsFilled = false;
        } else {
            passwordNotFilledIn.setText(""); // Limpa o texto de erro
        }

        return allFieldsFilled;
    }

}
