package edu.menueasy.adso.controller;

import java.util.List;

import org.apache.coyote.Response;
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
import edu.menueasy.adso.domain.user.UserDto;
import edu.menueasy.adso.domain.user.UserServiceImp;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {


    public UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String get() {
        return "it works";
    }


    @PostMapping
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
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User delete with success!");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> updateUser(
        @RequestBody UserDto userDto, 
        @PathVariable(name = "id") Long id
    ){
        userService.updateUser(userDto, id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUser(){

        long countUser = userService.countUser();
        return ResponseEntity.ok(userService.countUser());
    }

}
