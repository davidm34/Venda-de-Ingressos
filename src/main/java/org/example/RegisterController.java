package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.UUID;

public class RegisterController {

    @FXML
    private Text cpfNotFilledIn;

    @FXML
    private TextField cpfTextField;

    @FXML
    private Text emailNotFilledIn;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Text nameNotFilledIn;

    @FXML
    private TextField nameTextField;

    @FXML
    private Text passwordNotFilledIn;

    private ControllerScreens controllerScreens;

    public void setControllerScreens(ControllerScreens controllerScreens) {
        this.controllerScreens = controllerScreens;
    }


    @FXML
    void onHelloButtonClick(ActionEvent event) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        String name = nameTextField.getText();
        String password  = enterPasswordField.getText();
        String cpf = cpfTextField.getText();
        String email = emailTextField.getText();
        boolean check = checkIfThereIsNoEmptyField(name, password, cpf, email);
        if (check){
            UUID uuid = UUID.randomUUID();
            String id = String.valueOf(uuid);
            boolean register = usuarioManager.adicionarUsuarioNoArquivo(name, password, cpf, email, false, id);
            ControllerScreens.removeScene();
        }
    }

    @FXML
    void redirectLoginPage(ActionEvent event) {
        ControllerScreens.removeScene();
    }

    public boolean checkIfThereIsNoEmptyField(String name, String password, String cpf, String email) {
        boolean allFieldsFilled = true;

        if (name == null || name.trim().isEmpty()) {
            nameNotFilledIn.setText("Campo não Preenchido");
            allFieldsFilled = false;
        } else {
            nameNotFilledIn.setText(""); // Limpa o texto de erro
        }

        if (password == null || password.trim().isEmpty()) {
            passwordNotFilledIn.setText("Campo não Preenchido");
            allFieldsFilled = false;
        } else {
            passwordNotFilledIn.setText("");
        }

        if (cpf == null || cpf.trim().isEmpty()) {
            cpfNotFilledIn.setText("Campo não Preenchido");
            allFieldsFilled = false;
        } else {
            cpfNotFilledIn.setText("");
        }

        if (email == null || email.trim().isEmpty()) {
            emailNotFilledIn.setText("Campo não Preenchido");
            allFieldsFilled = false;
        } else {
            emailNotFilledIn.setText("");
        }

        return allFieldsFilled;
    }


}
