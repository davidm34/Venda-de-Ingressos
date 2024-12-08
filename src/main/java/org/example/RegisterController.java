package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

public class RegisterController {

    @FXML
    private Label cpfId;

    @FXML
    private Label emailId;

    @FXML
    private Label nameId;

    @FXML
    private Label passwordId;

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

    @FXML
    private ComboBox<String> languageComboBox;

    private ControllerScreens controllerScreens;

    private ResourceBundle bundle;

    @FXML
    private Button registerBottom;

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

    private void updateLanguage(String language, String country) {
        Locale locale = new Locale(language, country);
        bundle = ResourceBundle.getBundle("lang", locale);

        // Atualizar os textos da interface
        cpfNotFilledIn.setText(bundle.getString("error.cpfNotFilledIn"));
        emailNotFilledIn.setText(bundle.getString("error.emailNotFilledIn"));
        nameNotFilledIn.setText(bundle.getString("error.nameNotFilledIn"));
        passwordNotFilledIn.setText(bundle.getString("error.passwordNotFilledIn"));

        // Atualizar os textos de especificação de campo
        nameId.setText(bundle.getString("label.nameId"));
        emailId.setText(bundle.getString("label.emailId"));
        cpfId.setText(bundle.getString("label.cpfId"));
        passwordId.setText(bundle.getString("label.passwordId"));

        // Atualizar placeholders dos campos de texto
        nameTextField.setPromptText(bundle.getString("placeholder.name"));
        enterPasswordField.setPromptText(bundle.getString("placeholder.password"));
        cpfTextField.setPromptText(bundle.getString("placeholder.cpf"));
        emailTextField.setPromptText(bundle.getString("placeholder.email"));

        // Atualizar textos de botões
        loginButton.setText(bundle.getString("button.login"));
        registerBottom.setText(bundle.getString("button.register"));
    }

    @FXML
    void onHelloButtonClick(ActionEvent event) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        String name = nameTextField.getText();
        String password = enterPasswordField.getText();
        String cpf = cpfTextField.getText();
        String email = emailTextField.getText();
        boolean check = checkIfThereIsNoEmptyField(name, password, cpf, email);
        if (check) {
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
            nameNotFilledIn.setText(bundle.getString("error.nameNotFilledIn"));
            allFieldsFilled = false;
        } else {
            nameNotFilledIn.setText(""); // Limpa o texto de erro
        }

        if (password == null || password.trim().isEmpty()) {
            passwordNotFilledIn.setText(bundle.getString("error.passwordNotFilledIn"));
            allFieldsFilled = false;
        } else {
            passwordNotFilledIn.setText("");
        }

        if (cpf == null || cpf.trim().isEmpty()) {
            cpfNotFilledIn.setText(bundle.getString("error.cpfNotFilledIn"));
            allFieldsFilled = false;
        } else {
            cpfNotFilledIn.setText("");
        }

        if (email == null || email.trim().isEmpty()) {
            emailNotFilledIn.setText(bundle.getString("error.emailNotFilledIn"));
            allFieldsFilled = false;
        } else {
            emailNotFilledIn.setText("");
        }

        return allFieldsFilled;
    }
}
