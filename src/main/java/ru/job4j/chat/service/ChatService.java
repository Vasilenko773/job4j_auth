package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.exception.UserAllReadyExistException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.model.User;
import ru.job4j.chat.repository.MessageRepository;
import ru.job4j.chat.repository.RoleRepository;
import ru.job4j.chat.repository.RoomRepository;
import ru.job4j.chat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;


    public User saveUser(User user) throws UserAllReadyExistException {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new UserAllReadyExistException("Пользователь с таким именем уже существует");
        }
        user.setRole(roleRepository.findByName("user"));
        return userRepository.save(user);
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public Message saveMessage(Message message, int userId) {
        message.setUser(findUserById(userId));
        return messageRepository.save(message);
    }

    public Message updateMessage(int id) {
        Message message = messageRepository.findById(id).orElse(null);
        message.setText("UpdateMessage");
        return messageRepository.save(message);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void saveUserInRoom(int id, int userId) {

        Room room = roomRepository.findById(id).orElse(null);
        room.addUser(findUserById(userId));
        roomRepository.save(room);
    }

    public Role findRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public Message findMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    public Room findRoomById(int id) {
        return roomRepository.findById(id).orElse(null);
    }
}

