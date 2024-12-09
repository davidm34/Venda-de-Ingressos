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

/**
 * Controlador responsável pela lógica da tela de registro de usuários.
 * Garante validações de campos e interação com o sistema de persistência.
 *
 * @author David Neves Dias
 */
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

    @FXML
    private Button registerBottom;

    private ControllerScreens controllerScreens;

    private ResourceBundle bundle;

    /**
     * Configura o controlador de telas para alternância entre diferentes cenas.
     *
     * @param controllerScreens O controlador de telas.
     */
    public void setControllerScreens(ControllerScreens controllerScreens) {
        this.controllerScreens = controllerScreens;
    }

    /**
     * Inicializa os componentes da interface gráfica e configura o idioma padrão.
     */
    @FXML
    public void initialize() {
        configureLanguageComboBox();
        clearErrorMessages();
        updateLanguage("pt", "BR");
    }

    /**
     * Configura as opções de idioma no ComboBox.
     */
    private void configureLanguageComboBox() {
        languageComboBox.getItems().addAll("Português", "English");
        languageComboBox.setValue("Português");
        languageComboBox.setOnAction(event -> {
            String selectedLanguage = languageComboBox.getValue();
            if ("Português".equals(selectedLanguage)) {
                updateLanguage("pt", "BR");
            } else if ("English".equals(selectedLanguage)) {
                updateLanguage("en", "US");
            }
        });
    }

    /**
     * Atualiza os textos exibidos na interface com base no idioma selecionado.
     *
     * @param language O código do idioma.
     * @param country  O código do país.
     */
    private void updateLanguage(String language, String country) {
        Locale locale = new Locale(language, country);
        bundle = ResourceBundle.getBundle("lang", locale);

        // Atualizar textos da interface
        cpfId.setText(bundle.getString("label.cpfId"));
        emailId.setText(bundle.getString("label.emailId"));
        nameId.setText(bundle.getString("label.nameId"));
        passwordId.setText(bundle.getString("label.passwordId"));
        loginButton.setText(bundle.getString("button.login"));
        registerBottom.setText(bundle.getString("button.register"));

        // Atualizar placeholders
        cpfTextField.setPromptText(bundle.getString("placeholder.cpf"));
        emailTextField.setPromptText(bundle.getString("placeholder.email"));
        nameTextField.setPromptText(bundle.getString("placeholder.name"));
        enterPasswordField.setPromptText(bundle.getString("placeholder.password"));

        // Limpar mensagens de erro
        clearErrorMessages();
    }

    /**
     * Limpa todas as mensagens de erro exibidas na interface.
     */
    private void clearErrorMessages() {
        cpfNotFilledIn.setText("");
        emailNotFilledIn.setText("");
        nameNotFilledIn.setText("");
        passwordNotFilledIn.setText("");
    }

    /**
     * Método chamado ao clicar no botão de registro.
     * Valida os campos e registra o usuário se os dados forem válidos.
     *
     * @param event O evento acionado.
     * @throws IOException Se houver erro na persistência de dados.
     */
    @FXML
    void onHelloButtonClick(ActionEvent event) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        String name = nameTextField.getText();
        String password = enterPasswordField.getText();
        String cpf = cpfTextField.getText();
        String email = emailTextField.getText();

        if (validateFields(name, password, cpf, email)) {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            if (usuarioManager.adicionarUsuarioNoArquivo(name, password, cpf, email, false, id)) {
                controllerScreens.removeScene();
            }
        }
    }

    /**
     * Valida os campos de entrada e exibe mensagens de erro se necessário.
     *
     * @param name     Nome do usuário.
     * @param password Senha do usuário.
     * @param cpf      CPF do usuário.
     * @param email    Email do usuário.
     * @return true se todos os campos forem válidos, false caso contrário.
     */
    public boolean validateFields(String name, String password, String cpf, String email) {
        boolean allFieldsFilled = true;

        if (name == null || name.trim().isEmpty()) {
            nameNotFilledIn.setText(bundle.getString("error.nameNotFilledIn"));
            allFieldsFilled = false;
        } else {
            nameNotFilledIn.setText("");
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

    /**
     * Redireciona o usuário para a página de login.
     *
     * @param event O evento acionado.
     */
    @FXML
    void redirectLoginPage(ActionEvent event) {
        controllerScreens.removeScene();
    }
}
