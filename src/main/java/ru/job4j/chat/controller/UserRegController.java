package ru.job4j.chat.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exception.UserAllReadyExistException;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.ChatService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


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
    public void signUp(@Valid @RequestBody User user) {
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

/*
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0MzcwOTEzM30.SxgJaX9wQH5SbB97zAfIbzYLRc4WDw5MyhuZsXRk8iK6oIdBwxkx5kAQTwwRtmW8LDyRh5btQDSde5tx3gDX6w
 */

