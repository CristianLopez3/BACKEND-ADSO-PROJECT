package com.example.users.controller;

import com.example.users.entity.Users;
import com.example.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAll(){
        return usersService.getUsers();
    }

    @GetMapping("/{userId}")
    public Optional<Users> getBid(@PathVariable("userId") Long userId){
        return usersService.getUsers(userId);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Users users){
        usersService.saveOrUpdate(users);
    }

    @DeleteMapping("/{userId}")
    public void saveUpdate(@PathVariable("userId") Long userId){
        usersService.delete(userId);
    }


}
