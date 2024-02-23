package edu.menueasy.adso.domain.User;

import java.util.List;

public interface UserService {

	public List<UserDto> getUsers();
	
	public UserDto getUserById();
	
	public void createUser(UserDto userDto);
	
	public void updateUser(UserDto userDto, Integer id);
	
	public void deleteUser(Integer id);
	
}