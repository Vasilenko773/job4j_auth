package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.model.Employee;
import ru.job4j.model.Person;
import ru.job4j.repository.EmployeeRepository;
import ru.job4j.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class PersonService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonRepository repository;


    public List<Person> findAllPerson() {
        List<Person> people = new ArrayList<>();
        repository.findAll().forEach(people::add);
        return people;
    }


    public void saveOrUpdate(Person person) {
        repository.save(person);
    }


    public Person findById(int id) {
        return repository.findById(id).orElse(null);
    }


    public void deletePerson(Person person) {
        repository.delete(person);
    }


    public void deletePersonById(Integer id) {
        repository.deletePersonById(id);
    }


    public Person findByLogin(String login) {
        return repository.findByLogin(login);
    }


    public void deletePerson(String login) {
        repository.deletePersonByLogin(login);
    }


    public List<Employee> findAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
      return employees;
    }
}
