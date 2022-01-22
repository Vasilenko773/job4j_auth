package ru.job4j.chat.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exception.UserAllReadyExistException;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.ChatService;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRegController {


    private ChatService chatService;

    private BCryptPasswordEncoder encoder;

   public UserRegController(ChatService chatService, BCryptPasswordEncoder encoder) {
       this.chatService = chatService;
       this.encoder = encoder;
   }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            chatService.saveUser(user);
        } catch (UserAllReadyExistException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return chatService.findAllUsers();
    }
}

/*
curl -i -H "Content-Type:application/json" -X POST -d {"""login""":"""admin""","""password""":"""password"""} "http://localhost:8080/login"
 */

