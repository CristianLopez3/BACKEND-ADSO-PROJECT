package edu.menueasy.adso.domain.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

	private UserRepository userRepository;

	@Override
	public List<UserDto> getUsers() {
		return userRepository.findAll()
				.stream()
				.map(user -> new UserDto(user))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
		return new UserDto(user);
	}

	@Override
	public void createUser(UserDto userDto) {
		User user = new User();
		user.setName(userDto.name());
		user.setLastName(userDto.lastName());
		user.setEmail(userDto.email());
		user.setPassword(userDto.password());
		user.setCellphone(userDto.cellphone());
		user.setRole(userDto.role());
		user.setIdentification(userDto.identification());
		userRepository.save(user);

	}

	@Override
	public void updateUser(UserDto userDto, Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
		user.setName(userDto.name());
		user.setLastName(userDto.lastName());
		user.setEmail(userDto.email());
		user.setPassword(userDto.password());
		user.setIdentification(userDto.identification());
		user.setCellphone(userDto.cellphone());
		user.setRole(userDto.role());
		user.setId(id);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new RuntimeException("User not found with ID: " + id);
		}
	}

}
