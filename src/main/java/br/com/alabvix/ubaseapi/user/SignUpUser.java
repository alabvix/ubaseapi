package br.com.alabvix.ubaseapi.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@ValidEmailConfirmedEmail(groups = {Default.class})
@EmailAlreadyExists(groups = {Default.class})
public class SignUpUser {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    public final String name;

    @Email(message = "Email should be valid")
    public final String email;

    @Email(message = "Confirm Email should be valid")
    public final String confirmEmail;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password cannot be blank")
    public final String password;

    @NotNull(message = "Confirm Password cannot be null")
    public final String confirmPassword;

    public SignUpUser(String name, String email, String confirmEmail, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
