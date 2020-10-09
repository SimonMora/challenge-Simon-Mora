package com.mavha.airbnb.controller;

import com.mavha.airbnb.model.User;
import com.mavha.airbnb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/users")
public class UsersController {
    
    @Autowired
    UserRepository userRepository;
    
    @PostMapping(path = "/create")
    public void addNewUser(@RequestHeader Map<String,Object> headers,
                           @RequestBody User body ) {

        try{
            User user = userRepository.save(body);
        }catch (Exception e){

        }
    }

    @GetMapping(path = "/list")
    public List<User> listUsers() {

        return userRepository.findAll();

    }

    @GetMapping(path = "/list/{id}")
    public User listUser(@PathVariable("id") String id) {

        return userRepository.findById(UUID.fromString(id)).get();

    }
    
}
