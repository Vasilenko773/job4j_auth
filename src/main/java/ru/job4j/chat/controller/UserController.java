package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.exception.UserAllReadyExistException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.ChatService;
import ru.job4j.model.Person;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public ResponseEntity<User> create(@RequestBody Map<String, String> body) {
        var login = body.get("login");
        var password = body.get("password");
        if (login == null || password == null) {
            throw new NullPointerException("Логин или пароль не указаны");
        }
        User user = User.of(login, password);
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
}
