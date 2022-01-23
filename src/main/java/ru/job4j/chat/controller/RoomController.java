package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.model.User;
import ru.job4j.chat.service.ChatService;

import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Map<String, String> body) {
        var name = body.get("name");
        if (name == null) {
            throw new NullPointerException("Не указано название комнаты");
        }
       Room room = Room.of(name);
        return new ResponseEntity<Room>(
                this.chatService.saveRoom(room),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateRoom(@RequestParam int roomId, @RequestBody int userId) {
        this.chatService.saveUserInRoom(roomId, userId);
        return ResponseEntity.ok().build();
    }
}
