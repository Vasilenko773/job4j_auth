package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Person;
import ru.job4j.repository.PersonRepository;
import ru.job4j.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/person")

public class PersonController {

    @Autowired
    private PersonService service;


    @GetMapping("/")
    @Transactional
    public List<Person> findAll() {
        return StreamSupport.stream(
                this.service.findAllPerson().spliterator(), false
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Person> findById(@PathVariable int id) {
        return new ResponseEntity<Person>(service.findById(id) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    @Transactional
    public ResponseEntity<Void> saveOrUpdate(@RequestBody Person person) {
        this.service.saveOrUpdate(person);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.findById(id) != null) {
            this.service.deletePersonById(id);
            return ResponseEntity.ok().build();
        }
        return null;
    }

    @Transactional
    public Person findByLogin(String login) {
        return service.findByLogin(login);
    }
}