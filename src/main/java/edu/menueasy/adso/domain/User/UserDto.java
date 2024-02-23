package edu.menueasy.adso.domain.User;

import java.math.BigInteger;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotNull String name,
        @NotNull String lastName,
        @NotNull @Email String email,
        @NotNull String identification,
        @NotNull String password,
        @NotNull BigInteger cellphone,
        @NotNull Role role
) {

    public UserDto(User user) {
        this(
            user.getName(), 
            user.getLastName(), 
            user.getEmail(),
            user.getIdentification(), 
            user.getPassword(), 
            user.getCellphone(), 
            user.getRole());
    }
}
