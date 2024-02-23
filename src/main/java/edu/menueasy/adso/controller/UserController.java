package edu.menueasy.adso.controller;


import edu.menueasy.adso.domain.User.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.menueasy.adso.domain.User.UserDto;
import edu.menueasy.adso.domain.User.UserServiceImp;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    public UserServiceImp userService;


    @GetMapping()
    public String get(){
        return "it works";
    }


    @PostMapping()
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok(userDto);
    }


}