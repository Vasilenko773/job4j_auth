package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.exception.UserAllReadyExistException;
import ru.job4j.chat.model.*;
import ru.job4j.chat.service.ChatService;
import ru.job4j.model.Person;


import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/")
    public List<User> findAll() {
        return this.chatService.findAllUsers();
    }

    @PostMapping()
    @ExceptionHandler(value = {UserAllReadyExistException.class, IllegalArgumentException.class})
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        try {
            this.chatService.saveUser(user);
            return new ResponseEntity<User>(HttpStatus.CREATED);
        } catch (UserAllReadyExistException e) {
            return new ResponseEntity<User>(HttpStatus.valueOf(e.toString()));
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public User findUserById(@RequestParam int id) {
        if (chatService.findUserById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Пользователь с таким id не найден.");
        }
        return chatService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (chatService.findUserById(id) != null) {
            this.chatService.deleteUserById(id);
            return ResponseEntity.ok().build();
        }
        return null;
    }

    @PatchMapping("/dto")
    public User example2(@RequestBody UserDTO userDTO) {
        User user = chatService.findUserById(userDTO.getId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с таким id не найден.");
        }
        if (userDTO.getLogin() != null) {
            user.setLogin(userDTO.getLogin());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (chatService.findRoleById(userDTO.getRoleId()) != null) {
            user.setRole(chatService.findRoleById(userDTO.getRoleId()));
        }
        try {
            chatService.saveUser(user);
        } catch (UserAllReadyExistException e) {
            e.printStackTrace();
        }
        return user;
    }
}
