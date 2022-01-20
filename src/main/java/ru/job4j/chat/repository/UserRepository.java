package ru.job4j.chat.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.job4j.chat.model.Room;
import ru.job4j.chat.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByLogin(String login);

    @Query(value = "select distinct u from User u left join fetch u.messages")
    public List<User> findUsersAll();
}
