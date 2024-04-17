package edu.menueasy.adso.domain.user;

import java.util.List;
import java.util.stream.Collectors;

import edu.menueasy.adso.domain.role.Role;
import edu.menueasy.adso.infra.exceptions.user.UserNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder encoder;

	@Autowired
	public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.encoder = passwordEncoder;
	}

	@Override
	public List<UserDto> getUsers() {
		return userRepository.findAll()
				.stream()
				.map(UserDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		return new UserDto(user);
	}

	@Override
	public void createUser(@NotNull UserDto userDto) {
		User user = User.builder()
				.name(userDto.name())
				.lastname(userDto.lastname())
				.username(userDto.email())
				.password(encoder.encode(userDto.password()))
				.cellphone(userDto.cellphone())
				.identification(userDto.identification())
				.role(Role.ADMIN)
				.build();
		userRepository.save(user);
	}

	@Override
	public void updateUser(UserDto userDto, @NotNull Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		user.setName(userDto.name());
		user.setLastname(userDto.lastname());
		user.setUsername(userDto.email());
		user.setIdentification(userDto.identification());
		user.setCellphone(userDto.cellphone());
		user.setRole(Role.ADMIN);
		user.setId(id);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User not found with ID: " + id);
		}
		userRepository.deleteById(id);
	}

	@Override
	public Long countUser(){
		return userRepository.count();
	}
}