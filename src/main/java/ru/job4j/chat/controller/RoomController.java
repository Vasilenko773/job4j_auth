package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.*;
import ru.job4j.chat.service.ChatService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid  @RequestBody Room room) {
        return new ResponseEntity<Room>(
                this.chatService.saveRoom(room),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateRoom(@RequestParam int roomId, @RequestBody int userId) {
        this.chatService.saveUserInRoom(roomId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/dto")
    public Room example2(@RequestBody RoomTDO roomTDO) {
        Room room = chatService.findRoomById(roomTDO.getId());
        if (room != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Команта с указанным id не найденно.");
        }
        if (roomTDO.getName() != null) {
            room.setName(roomTDO.getName());
        }
       room.getPeople().clear();
        if (roomTDO.getArrayId().length > 0) {
            for (int i : roomTDO.getArrayId()) {
                if (chatService.findUserById(i) != null) {
                   room.addUser(chatService.findUserById(i));
                }
            }
        }
        chatService.saveRoom(room);
        return room;
    }
}
