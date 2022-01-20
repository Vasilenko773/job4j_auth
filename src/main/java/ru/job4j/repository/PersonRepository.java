package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.model.Person;


public interface PersonRepository extends CrudRepository<Person, Integer> {


    public Person findByLogin(String login);


    public void deletePersonByLogin(String login);


    public void deletePersonById(Integer id);

}
