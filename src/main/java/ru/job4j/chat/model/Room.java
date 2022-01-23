package ru.job4j.chat.model;

import org.springframework.stereotype.Component;
import ru.job4j.model.Person;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "room")
@Component
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title must be not empty")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "room_user", joinColumns = {
            @JoinColumn(name = "room_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", nullable = false, updatable = false)})
    private Set<User> people = new HashSet<>();

    public static Room of(String name) {
        Room room = new Room();
        room.name = name;
        return room;
    }

    public Set<User> addUser(User user) {
        this.people.add(user);
        return people;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getPeople() {
        return people;
    }

    public void setPeople(Set<User> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", people=" + people
                + '}';
    }
}
