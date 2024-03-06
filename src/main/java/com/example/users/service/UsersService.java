package com.example.users.service;

import com.example.users.entity.Users;
import com.example.users.repositiry.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;


    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    public Optional<Users> getUsers(Long id){
        return usersRepository.findById(id);
    }

    public void saveOrUpdate(Users users){
        usersRepository.save(users);
    }

    public void delete(Long id){
        usersRepository.deleteById(id);
    }
}
