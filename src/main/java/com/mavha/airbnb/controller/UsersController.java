package com.mavha.airbnb.controller;

import com.mavha.airbnb.model.User;
import com.mavha.airbnb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/users")
public class UsersController {

    Logger logger = LoggerFactory.getLogger(UsersController.class);
    
    @Autowired
    UserRepository userRepository;
    
    @PostMapping(path = "/create")
    public void addNewUser(@RequestHeader Map<String,Object> headers,
                           @RequestBody User body ) {

        logger.info("Request to addNewUser controller with body: {}", body);
        logger.info("Headers: {}", headers);
        try{
            User user = userRepository.save(body);
            logger.info("User saved as: {}", user);
        }catch (Exception e){
            logger.error("Exception in addNewUser controller : {}", e.getStackTrace());
        }
    }

    @GetMapping(path = "/list")
    public List<User> listUsers(@RequestHeader Map<String,Object> headers) {

        logger.info("Request to addNewUser controller...");
        logger.info("Headers: {}", headers);
        try {
            return userRepository.findAll();
        } catch (Exception e){
            logger.error("Exception in listUsers controller : {}", e.getStackTrace());
            return null;
        }
    }

    @GetMapping(path = "/list/{id}")
    public User consultUser(@RequestHeader Map<String, Object> headers,
                         @PathVariable("id") String id) {

        logger.info("Request to consultUser controller with id: {}", id);
        logger.info("Headers: {}", headers);
        try {
            User user = userRepository.findById(UUID.fromString(id)).get();
            logger.info("Consult result: {}",user);
            return user;
        } catch (Exception e){
            logger.error("Error consultUser with error : {}", e.getStackTrace());
            return null;
        }
    }
    
}
