package br.com.alabvix.ubaseapi.user;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@GroupSequence({SignUpUser.class, UserDataValidationGroup.class, UserEmailValidationGroup.class})
@ConfirmedEmail(groups = UserEmailValidationGroup.class)
public class SignUpUser {

    @NotNull(message = "Name cannot be null", groups = UserDataValidationGroup.class)
    @NotEmpty(message = "Name cannot be empty", groups = UserDataValidationGroup.class)
    @NotBlank(message = "Name cannot be blank", groups = UserDataValidationGroup.class)
    @UsernameAlreadyExists(groups = UserEmailValidationGroup.class)
    public final String username;

    @NotNull(message = "Email cannot be null", groups = UserDataValidationGroup.class)
    @NotEmpty(message = "Email cannot be empty", groups = UserDataValidationGroup.class)
    @Email(message = "Email should be valid", groups = UserDataValidationGroup.class)
    @EmailAlreadyExists(groups = UserEmailValidationGroup.class)
    public final String email;

    @NotNull(message = "Confirm Email cannot be null", groups = UserDataValidationGroup.class)
    @Email(message = "Confirm Email should be valid", groups = UserDataValidationGroup.class)
    public final String confirmEmail;

    @NotNull(message = "Password cannot be null", groups = UserDataValidationGroup.class)
    @NotEmpty(message = "Password cannot be empty", groups = UserDataValidationGroup.class)
    @NotBlank(message = "Password cannot be blank", groups = UserDataValidationGroup.class)
    public final String password;

    @NotNull(message = "Confirm Password cannot be null", groups = UserDataValidationGroup.class)
    @NotEmpty(message = "Confirm Password cannot be empty", groups = UserDataValidationGroup.class)
    @NotBlank(message = "Confirm Password cannot be blank", groups = UserDataValidationGroup.class)
    public final String confirmPassword;

    public SignUpUser(String username, String email, String confirmEmail, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public SignUpUser() {
        this.username = "";
        this.email = "";
        this.confirmEmail = "";
        this.password = "";
        this.confirmPassword = "";
    }
}
