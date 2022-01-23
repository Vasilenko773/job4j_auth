package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.ChatService;


@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private ChatService chatService;


    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message, @RequestParam int userId) {
        if (message.getText() == null) {
            throw new NullPointerException("Сообщение не содержит текста");
        }
        return new ResponseEntity<Message>(
                this.chatService.saveMessage(message, userId),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Message> createMessage(@RequestParam int id) {
        return new ResponseEntity<Message>(
                this.chatService.updateMessage(id),
                HttpStatus.OK);
    }
}
