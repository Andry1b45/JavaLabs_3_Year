package controller;

import java.util.UUID;

public class UserAuthData {
    private static UserAuthData userAuthData = new UserAuthData();
    private UUID id;
    private String username = "guest";
    private String role = "NONE";

    private UserAuthData() {
    }

    private UserAuthData(UUID id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public static void setAuthData(UUID id, String username, String role) {
        userAuthData = new UserAuthData(id, username, role);
    }

    public static UserAuthData getAuthData() {
        return userAuthData;
    }
}