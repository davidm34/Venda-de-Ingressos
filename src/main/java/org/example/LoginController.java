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

    @FXML
    private Label textPriceTicket;

    @FXML
    private Label textQuantity;

    @FXML
    private Label textTotal;

    @FXML
    private Text totalPrice;

    private ResourceBundle bundle;

    private ControllerScreens controllerScreens;

    public void setControllerScreens(ControllerScreens controllerScreens) {
        this.controllerScreens = controllerScreens;
    }

    @FXML
    public void initialize() {
        // Configurar opções de idiomas no ComboBox
        languageComboBox.getItems().addAll("Português", "English");
        languageComboBox.setValue("Português"); // Idioma padrão

        // Atualizar idioma para o padrão inicial
        updateLanguage("pt", "BR");

        // Configurar listener para mudanças no ComboBox de idioma
        languageComboBox.setOnAction(event -> {
            String selectedLanguage = languageComboBox.getValue();
            if ("Português".equals(selectedLanguage)) {
                updateLanguage("pt", "BR");
            } else if ("English".equals(selectedLanguage)) {
                updateLanguage("en", "US");
            }
        });

        // Inicializar textos de erro como vazios
        emailNotFilledIn.setText("");
        passwordNotFilledIn.setText("");
    }

    @FXML
    public void onHelloButtonClick(ActionEvent actionEvent) throws IOException {
        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();

        // Validar campos preenchidos
        boolean check = checkIfThereIsNoEmptyField(username, password);
        if (check) {
            // Validar credenciais
            if (validateLogin(username, password)) {
                UserTestFacade userTestFacade = new UserTestFacade();
                String id = userTestFacade.getIdByUserEmail(username);
                SessionManager.login(id); // Armazena o ID do usuário na sessão
                controllerScreens.removeScene();
            } else {
                validateLogin.setText(bundle.getString("error.invalidCredentials"));
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

    private boolean checkIfThereIsNoEmptyField(String email, String password) {
        boolean allFieldsFilled = true;

        if (email == null || email.trim().isEmpty()) {
            emailNotFilledIn.setText(bundle.getString("error.emailNotFilledIn"));
            allFieldsFilled = false;
        } else {
            emailNotFilledIn.setText(""); // Limpar mensagem de erro
        }

        if (password == null || password.trim().isEmpty()) {
            passwordNotFilledIn.setText(bundle.getString("error.passwordNotFilledIn"));
            allFieldsFilled = false;
        } else {
            passwordNotFilledIn.setText(""); // Limpar mensagem de erro
        }

        return allFieldsFilled;
    }

    private void updateLanguage(String language, String country) {
        Locale locale = new Locale(language, country);
        bundle = ResourceBundle.getBundle("lang", locale);

        // Atualizar textos exibidos na interface
        emailId.setText(bundle.getString("label.emailId"));
        passwordId.setText(bundle.getString("label.passwordId"));
        enterPasswordField.setPromptText(bundle.getString("placeholder.password"));
        usernameTextField.setPromptText(bundle.getString("placeholder.email"));
        loginButton.setText(bundle.getString("button.login"));

        // Atualizar mensagens de validação
        validateLogin.setText("");
        emailNotFilledIn.setText("");
        passwordNotFilledIn.setText("");
    }
}
