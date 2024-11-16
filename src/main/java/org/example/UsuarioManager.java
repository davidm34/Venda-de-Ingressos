package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela gestão de usuários, incluindo operações
 * de leitura e escrita em arquivos JSON.
 *
 * @author David Neves Dias
 */
public class UsuarioManager {
    private List<Usuario> listausuario;

    /**
     * Lê o conteúdo do arquivo JSON e retorna a lista de usuários.
     *
     * @return Lista de usuários lida do arquivo.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     */
    public List<Usuario> lerConteudoArquivo() throws IOException {
        try {
            FileReader fileReader = new FileReader("usuarios.json");
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            listausuario = new Gson().fromJson(fileReader, listType);

            // Fecha o arquivo após a leitura
            fileReader.close();

            // Verifica se a lista está vazia ou nula
            if (listausuario == null) {
                listausuario = new ArrayList<>();
            }

        } catch (IOException e) {
            // Se houver uma exceção, inicializa uma lista vazia
            listausuario = new ArrayList<>();
            throw e;  // Propaga a exceção para que o chamador saiba do erro
        }

        // Retorna a lista de usuários
        return listausuario;
    }

    /**
     * Adiciona um novo usuário ao arquivo JSON.
     *
     * @param login O login do usuário.
     * @param password A senha do usuário.
     * @param name O nome do usuário.
     * @param cpf O CPF do usuário.
     * @param email O email do usuário.
     * @param isAdmin Indica se o usuário é um administrador.
     * @param id O ID do usuário.
     * @return true se o usuário foi adicionado com sucesso.
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public boolean adicionarUsuarioNoArquivo(String login, String password, String name, String cpf, String email, Boolean isAdmin, String id) throws IOException {
        Usuario usuario = new Usuario(login, password, name, cpf, email, isAdmin, id);
        lerConteudoArquivo();
        listausuario.add(usuario);
        String jsonUsuarios = new Gson().toJson(listausuario);
        FileWriter fileWriter = new FileWriter("usuarios.json");
        fileWriter.write(jsonUsuarios);
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

    /**
     * Remove um usuário da lista pelo seu email.
     *
     * @param email O email do usuário a ser removido.
     * @param usuarios A lista de usuários.
     */
    public void deletarUsuarioPorEmail(String email, List<Usuario> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getEmail().equals(email)) {
                usuarios.remove(i);
                return;
            }
        }
    }

    /**
     * Limpa o conteúdo do arquivo JSON, deixando uma lista vazia.
     *
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public void limparArquivoJson() throws IOException {
        try (FileWriter fileWriter = new FileWriter("usuarios.json")) {
            fileWriter.write("[]"); // Escreve uma lista vazia no arquivo
        }

        if (listausuario == null) {
            listausuario = new ArrayList<>(); // Inicializa a lista se estiver nula
        }
        listausuario.clear(); // Limpa a lista
    }

    /**
     * Salva a lista de usuários no arquivo JSON.
     *
     * @param usuarios A lista de usuários a ser salva.
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public void salvarUsuarios(List<Usuario> usuarios) throws IOException {
        try (FileWriter fileWriter = new FileWriter("usuarios.json")) {
            String jsonUsuarios = new Gson().toJson(usuarios);
            fileWriter.write(jsonUsuarios);
        }
    }
}
