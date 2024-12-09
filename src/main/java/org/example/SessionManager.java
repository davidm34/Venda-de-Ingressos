package org.example;

/**
 * Gerencia a sessão do usuário atual no sistema.
 *
 * @author David Neves Dias
 */
public class SessionManager {

    private static String currentUserId;

    /**
     * Realiza o login do usuário, armazenando seu ID na sessão.
     *
     * @param userId O ID do usuário que será armazenado na sessão.
     * @throws IllegalArgumentException Se o ID do usuário for nulo ou vazio.
     */
    public static synchronized void login(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo ou vazio.");
        }
        currentUserId = userId;
    }

    /**
     * Retorna o ID do usuário atualmente logado.
     *
     * @return O ID do usuário logado ou null se nenhum usuário estiver logado.
     */
    public static synchronized String getCurrentUserId() {
        return currentUserId;
    }

    /**
     * Realiza o logout do usuário, limpando o ID armazenado na sessão.
     */
    public static synchronized void logout() {
        currentUserId = null;
    }
}
