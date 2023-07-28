package com.jnsdevs.app.repositories;

import com.jnsdevs.app.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Autor Jairo Nascimento
 * @Created 28/07/2023 - 11:30
 */
@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @DisplayName("Given Person Object when Save then Return Saved Person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnSavedPerson() {
        // Given / Arrange
        var person = new Person(
                "Jairo",
                "Nascimento",
                "Uberlandia",
                "Male",
                "jairo@email.com");
        //When / Act
        var savePerson = personRepository.save(person);

        //Then / Assert
        assertNotNull(savePerson);
        assertTrue(savePerson.getId() > 0);

    }

    @DisplayName("Given Person Object when find all then Return Person List")
    @Test
    void testGivenPersonObject_whenFindAll_thenReturnPersonList() {
        // Given / Arrange
        var person0 = new Person(
                "Jairo",
                "Nascimento",
                "Uberlandia",
                "Male",
                "jairo@email.com");
        var person1 = new Person(
                "Silvana",
                "Luz",
                "Uberlandia",
                "Female",
                "jairo@email.com");
        personRepository.save(person0);
        personRepository.save(person1);

        //When / Act
        List<Person> personList = personRepository.findAll();

        //Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());

    }

    @DisplayName("Given Person Object when find by ID then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByID_thenReturnPersonObject() {
        // Given / Arrange
        var person = new Person(
                "Jairo",
                "Nascimento",
                "Uberlandia",
                "Male",
                "jairo@email.com");
        personRepository.save(person);
        //When / Act
        Person savedPerson = personRepository.findById(person.getId()).get();
        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person.getId(), savedPerson.getId());

    }
}