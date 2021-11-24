package br.com.alabvix.ubaseapi.application;

import javax.validation.constraints.NotNull;

public class Application {

    @NotNull(message = "User id cannot be null")
    public final String userId;

    @NotNull(message = "Application name cannot be null")
    public final String name;

    @NotNull(message = "Auth method cannot be null")
    public final String authMethod;

    public Application(String userId, String name, String authMethod) {
        this.userId = userId;
        this.name = name;
        this.authMethod = authMethod;
    }
}
