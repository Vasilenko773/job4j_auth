package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.MessageDTO;
import ru.job4j.chat.service.ChatService;

import javax.validation.Valid;


@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private ChatService chatService;


    @PostMapping
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message, @RequestParam int userId) {
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

    @PatchMapping("/dto")
    public Message example2(@RequestBody MessageDTO messageDTO) {
        Message message = chatService.findMessageById(messageDTO.getId());
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Сообщение с таким id не найденно.");
        } else if (messageDTO.getText() != null
                && chatService.findUserById(messageDTO.getUserId()) != null) {
            message.setText(messageDTO.getText());
        }
       return chatService.saveMessage(message, messageDTO.getUserId());
    }
}
