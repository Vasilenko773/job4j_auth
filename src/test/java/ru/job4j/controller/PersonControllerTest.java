package ru.job4j.controller;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.Job4jAuthApplication;

@SpringBootTest(classes = Job4jAuthApplication.class)
@AutoConfigureJdbc
class PersonControllerTest {

    /*@Autowired
    private PersonController personController;

    @Test
    @Transactional
    public void addPersonAndFindAll() {
        personController.saveOrUpdate(new Person(1, "First", "First"));
        assertThat(personController.findAll().size(), is(1));
    }

    @Test
    @Transactional
    public void addPersonAndAndFindById() {
        personController.saveOrUpdate(new Person(1, "First", "First"));
        personController.saveOrUpdate(new Person(2, "Second", "Second"));
        assertThat(personController.findById(2), is(new ResponseEntity<Person>(HttpStatus.OK)));
    }

    @Test
    @Transactional
    public void deletePerson() {
        personController.saveOrUpdate(new Person(1, "First", "First"));
        personController.saveOrUpdate(new Person(2, "Second", "Second"));
        personController.delete(2);
        assertThat(personController.findById(2), is(new ResponseEntity<Person>(HttpStatus.NOT_FOUND)));
    }

    @Test
    @Transactional
    public void updatePerson() {
        personController.saveOrUpdate(new Person(1, "First", "First"));
        personController.saveOrUpdate(new Person(1, "Second", "Second"));
        assertThat(personController.findByLogin("Second").getPassword(), is("Second"));
    }*/
}