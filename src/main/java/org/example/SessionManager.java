package org.example;

public class SessionManager {

    private static String currentUserId;

    public static void login(String userId) {
        currentUserId = userId;
    }

    public static String getCurrentUserId() {
        return currentUserId;
    }

    public static void logout() {
        currentUserId = null;
    }
}
