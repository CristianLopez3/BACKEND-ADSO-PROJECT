package edu.menueasy.adso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.menueasy.adso.domain.User.UserDto;
import edu.menueasy.adso.domain.User.UserServiceImp;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {

    @Autowired
    public UserServiceImp userService;

    @GetMapping("/test")
    public String get() {
        return "it works";
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Integer id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User delete with success!");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> updateUser(
        @RequestBody UserDto userDto, 
        @PathVariable(name = "id") Integer id
    ){
        userService.updateUser(userDto, id);
        return ResponseEntity.ok(userDto);
    }

}
