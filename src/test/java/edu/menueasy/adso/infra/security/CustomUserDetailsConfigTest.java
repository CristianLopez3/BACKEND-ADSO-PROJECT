package edu.menueasy.adso.infra.security;

import edu.menueasy.adso.domain.user.User;
import edu.menueasy.adso.domain.user.UserRepository;
import edu.menueasy.adso.domain.role.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("CustomUserDetailsConfig class test suite - ")
@ExtendWith(MockitoExtension.class)
class CustomUserDetailsConfigTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    UserDetailsServiceImpl service;
//
//    @Test
//    void loadUserByUsername() {
//        String username = "email_test";
//        String password = "email_password";
//        User userEntity = User.builder()
//                .email(username)
//                .password(password)
//                .roles(List.of(new Role("role_test")))
//                .build();
//
//        given(userRepository.findByUsername(anyString())).willReturn(Optional.of(userEntity));
//
//        UserDetails userDetails = service.loadUserByUsername(anyString());
//
//        then(userRepository).should().findByUsername(anyString());
//        assertThat(userDetails).isNotNull();
//        assertThat(userDetails.getUsername()).isEqualTo(username);
//        assertThat(userDetails.getPassword()).isEqualTo(password);
//        assertThat(userDetails.getAuthorities()).isNotNull();
//    }
//
//    @Test
//    void loadUserByUsernameThrowsException() {
//        String email = "email_not_exists";
//        given(userRepository.findByUsername(email)).willThrow(new UsernameNotFoundException("User not found"));
//
//        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(email));
//    }
//
//    @Test
//    void mapRolesToAuthorities() {
//        Role role1 = new Role("TEST_1");
//        Role role2 = new Role("TEST_2");
//        List<Role> roles = Arrays.asList(role1, role2);
//
//        Collection<GrantedAuthority> authorities = service.mapRolesToAuthorities(roles);
//
//        assertNotNull(authorities);
//        assertTrue(authorities.contains(new SimpleGrantedAuthority(role1.getName())));
//        assertTrue(authorities.contains(new SimpleGrantedAuthority(role2.getName())));
//    }

}