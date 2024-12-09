package org.example;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Classe responsável pela fachada de gerenciamento de usuários,
 * fornecendo métodos para criar, atualizar, e recuperar informações sobre usuários.
 *
 * @author David Neves Dias
 */
public class UserTestFacade {

    public UserTestFacade() {}

    /**
     * Cria um novo usuário e o adiciona ao sistema.
     *
     * @param login O login do usuário.
     * @param password A senha do usuário.
     * @param name O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param email O e-mail do usuário.
     * @param isAdmin Se o usuário é um administrador.
     * @return true se o usuário foi criado com sucesso; caso contrário, false.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public boolean create(String login, String password, String name, String cpf, String email, Boolean isAdmin) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();

        // Verifica se o email já está em uso
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                throw new SecurityException("Login, email e/ou cpf já está em uso.");
            }
        }

        // Gera um ID para o novo usuário
        UUID uuid = UUID.randomUUID();
        String id = String.valueOf(uuid);
        return usuarioManager.adicionarUsuarioNoArquivo(name, password, cpf, email, isAdmin, id);
    }

    public boolean validateLogin(String email, String password) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;

    }


    /**
     * Recupera o login de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @return O login do usuário ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getLoginByUserEmail(String email) throws IOException {
        return getUserPropertyByEmail(email, "login");
    }

    /**
     * Atualiza o nome de um usuário pelo seu e-mail.
     *
     * @param newName O novo nome do usuário.
     * @param email O e-mail do usuário.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void setNameByUserEmail(String newName, String email) throws IOException {
        updateUserPropertyByEmail(email, usuario -> usuario.setNome(newName));
    }

    /**
     * Atualiza a senha de um usuário pelo seu e-mail.
     *
     * @param newPassword A nova senha do usuário.
     * @param email O e-mail do usuário.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void setPasswordByUserEmail(String newPassword, String email) throws IOException {
        updateUserPropertyByEmail(email, usuario -> usuario.setSenha(newPassword));
    }

    /**
     * Atualiza o e-mail de um usuário.
     *
     * @param newEmail O novo e-mail do usuário.
     * @param email O e-mail atual do usuário.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void setEmailByUserEmail(String newEmail, String email) throws IOException {
        updateUserPropertyByEmail(email, usuario -> usuario.setEmail(newEmail));
    }

    /**
     * Recupera o nome de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @return O nome do usuário ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getNameByUserEmail(String email) throws IOException {
        return getUserPropertyByEmail(email, "name");
    }

    /**
     * Recupera o e-mail de um usuário pelo seu e-mail (auto-referencial).
     *
     * @param email O e-mail do usuário.
     * @return O e-mail do usuário ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getEmailByUserEmail(String email) throws IOException {
        return getUserPropertyByEmail(email, "email");
    }

    /**
     * Recupera a senha de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @return A senha do usuário ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getPasswordByUserEmail(String email) throws IOException {
        return getUserPropertyByEmail(email, "password");
    }

    /**
     * Recupera o CPF de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @return O CPF do usuário ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getIdByUserEmail(String email) throws IOException {
        return getUserPropertyByEmail(email, "id");
    }

    /**
     * Verifica se um usuário é administrador pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @return true se o usuário é administrador, false caso contrário.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public boolean getIsAdminByUserEmail(String email) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario.isAdmin();
            }
        }
        return false;
    }

    /**
     * Verifica se existe um usuário registrado com o e-mail fornecido.
     *
     * @param email O e-mail do usuário.
     * @return true se o usuário existe, false caso contrário.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public boolean thereIsUserWithEmail(String email) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recupera a quantidade total de usuários cadastrados.
     *
     * @return O número de usuários.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public int getSizeUsers() throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        return usuarios.size();
    }

    /**
     * Deleta todos os usuários registrados no sistema.
     *
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void deleteAllUsers() throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        usuarioManager.limparArquivoJson();
    }

    /**
     * Deleta um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário a ser deletado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public void deleteByUserEmail(String email) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        usuarioManager.deletarUsuarioPorEmail(email, usuarios);
        usuarioManager.salvarUsuarios(usuarios);
    }

    /**
     * Realiza o login de um usuário com base no login e na senha fornecidos.
     *
     * @param login O login do usuário.
     * @param password A senha do usuário.
     * @return true se o login for bem-sucedido; caso contrário, false.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public boolean login(String login, String password) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recupera o ID de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @return O ID do usuário ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getUserIdByEmail(String email) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario.getId();
            }
        }
        return null;
    }

    /**
     * Recupera uma propriedade específica de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @param property O nome da propriedade a ser recuperada (ex: "login", "name").
     * @return O valor da propriedade ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    private String getUserPropertyByEmail(String email, String property) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                switch (property) {
                    case "login":
                        return usuario.getLogin();
                    case "name":
                        return usuario.getNome();
                    case "email":
                        return usuario.getEmail();
                    case "password":
                        return usuario.getSenha();
                    case "id":
                        return usuario.getId();
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    /**
     * Atualiza uma propriedade específica de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @param updateAction A ação a ser realizada para atualizar a propriedade do usuário.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    private void updateUserPropertyByEmail(String email, java.util.function.Consumer<Usuario> updateAction) throws IOException {
        UsuarioManager usuarioManager = new UsuarioManager();
        List<Usuario> usuarios = usuarioManager.lerConteudoArquivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                updateAction.accept(usuario);
                usuarioManager.salvarUsuarios(usuarios); // Salva as alterações no arquivo
                return;
            }
        }
    }
}
