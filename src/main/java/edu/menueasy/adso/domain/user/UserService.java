package edu.menueasy.adso.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

	public Page<UserDto> getAll(Pageable pageable);
	
	public UserDto getUserById(Long id);
	
	public void createUser(UserDto userDto);
	
	public void updateUser(UserDto userDto, Long id);
	
	public void deleteUser(Long id);

	Long countUser();
	
}