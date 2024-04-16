package edu.menueasy.adso.domain.user;

import edu.menueasy.adso.domain.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDto(
		Long id,
		String name,
		String lastName,
		@NotNull @Email String email,
		String identification,
		@NotNull String password, 
		Long cellphone,
		String role) {
	public UserDto(User user) {
		this(
				user.getId(),
				user.getName(),
				user.getLastName(),
				user.getUsername(),
				user.getIdentification(),
				user.getPassword(),
				user.getCellphone(),
				user.getRole().toString()
		);
	}
}
