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
     * Recupera o ID de um usuário pelo seu e-mail.
     *
     * @param email O e-mail do usuário.
     * @return O ID do usuário ou null se não encontrado.
     * @throws IOException Se ocorrer um erro ao acessar os arquivos.
     */
    public String getIdByUserEmail(String email) throws IOException {
        return getUserPropertyByEmail(email, "id");
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

}
