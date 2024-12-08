package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private Label emailId;

    @FXML
    private Label passwordId;

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

    @FXML
    private ComboBox<String> languageComboBox;

    private ResourceBundle bundle;

    private static final String USERS_FILE = "usuarios.json";

    private ControllerScreens controllerScreens;

    public void setControllerScreens(ControllerScreens controllerScreens) {
        this.controllerScreens = controllerScreens;
    }

    @FXML
    public void initialize() {
        // Configuração inicial do ComboBox de idiomas
        languageComboBox.getItems().addAll("Português", "English");
        languageComboBox.setValue("Português");
        updateLanguage("pt", "BR");

        languageComboBox.setOnAction(event -> {
            String selectedLanguage = languageComboBox.getValue();
            if ("Português".equals(selectedLanguage)) {
                updateLanguage("pt", "BR");
            } else if ("English".equals(selectedLanguage)) {
                updateLanguage("en", "US");
            }
        });
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

    private void updateLanguage(String language, String country) {
        Locale locale = new Locale(language, country);
        bundle = ResourceBundle.getBundle("lang", locale);

        // Atualizar os textos da interface
        emailNotFilledIn.setText(bundle.getString("error.emailNotFilledIn"));
        passwordNotFilledIn.setText(bundle.getString("error.passwordNotFilledIn"));

        // Atualizar os textos de especificação de campo
        emailId.setText(bundle.getString("label.emailId"));
        passwordId.setText(bundle.getString("label.passwordId"));

        // Atualizar placeholders dos campos de texto
        enterPasswordField.setPromptText(bundle.getString("placeholder.password"));
        usernameTextField.setPromptText(bundle.getString("placeholder.email"));

        // Atualizar textos de botões
        loginButton.setText(bundle.getString("button.login"));
    }

}
