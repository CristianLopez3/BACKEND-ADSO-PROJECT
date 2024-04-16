package edu.menueasy.adso.domain.user;

import java.util.List;

public interface UserService {

	public List<UserDto> getUsers();
	
	public UserDto getUserById(Long id);
	
	public void createUser(UserDto userDto);
	
	public void updateUser(UserDto userDto, Long id);
	
	public void deleteUser(Long id);
	
}