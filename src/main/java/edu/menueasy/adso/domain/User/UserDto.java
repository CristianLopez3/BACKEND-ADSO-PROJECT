package edu.menueasy.adso.domain.User;

import java.math.BigInteger;

public record UserDto(
        @Not
        String name,
        String lastName,
        String email,
        String identification,
        String password,
        BigInteger cellphone,
        Role role
) {


}
