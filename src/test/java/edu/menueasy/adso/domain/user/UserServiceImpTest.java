package edu.menueasy.adso.domain.user;

import edu.menueasy.adso.domain.User.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {

  @InjectMocks
  private UserServiceImp userService;

  @Mock
  private UserRepository userRepository;

  @Test
  public void testGetUsers() {
    User user = new User();
    user.setName("Test");
    when(userRepository.findAll()).thenReturn(Arrays.asList(user));
    List<UserDto> users = userService.getUsers();
    assertEquals(1, users.size());
    assertEquals("Test", users.get(0).name());
  }

  @Test
  public void testGetUserById() {
    User user = new User();
    user.setName("Test");
    when(userRepository.findById(1)).thenReturn(Optional.of(user));
    UserDto userDto = userService.getUserById(1);
    assertEquals("Test", userDto.name());
  }

}