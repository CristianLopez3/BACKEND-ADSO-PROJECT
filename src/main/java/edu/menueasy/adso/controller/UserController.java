package edu.menueasy.adso.controller;


import edu.menueasy.adso.domain.User.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    User user = new User();


    @GetMapping("/user")
    public ResponseEntity<User>getUser(){
        user.setName("Carlos");



        return ResponseEntity.ok(user);
    }


}