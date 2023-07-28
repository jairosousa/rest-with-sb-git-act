package com.jnsdevs.app.repositories;

import com.jnsdevs.app.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

    @DisplayName("Given Person Object when find by email then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject() {
        // Given / Arrange
        var person = new Person(
                "Jairo",
                "Nascimento",
                "Uberlandia",
                "Male",
                "jairo@email.com");
        personRepository.save(person);
        //When / Act
        Person savedPerson = personRepository.findByEmail(person.getEmail()).get();
        //Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person.getEmail(), savedPerson.getEmail());

    }

    @DisplayName("Given Person Object when update Person then Return Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject() {
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
        savedPerson.setFirstName("Silvana");
        savedPerson.setEmail("sls@email.com");

        var updatePerson = personRepository.save(savedPerson);

        //Then / Assert
        assertNotNull(updatePerson);
        assertEquals("sls@email.com", updatePerson.getEmail());
        assertEquals("Silvana", updatePerson.getFirstName());

    }

    @DisplayName("Given Person Object when delete person then Remove Person")
    @Test
    void testGivenPersonObject_whenDeletePerson_thenRemovePerson() {
        // Given / Arrange
        var person = new Person(
                "Jairo",
                "Nascimento",
                "Uberlandia",
                "Male",
                "jairo@email.com");
        personRepository.save(person);
        //When / Act
        personRepository.deleteById(person.getId());

        Optional<Person> optionalPerson = personRepository.findById(person.getId());
        //Then / Assert
        assertTrue(optionalPerson.isEmpty());

    }

    @DisplayName("Given FirstName And LastName when FindJPQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQL_thenReturnPersonObject() {
        // Given / Arrange
        String firstName = "Jairo";
        String lastName = "Nascimento";
        var person = new Person(
                firstName,
                lastName,
                "Uberlandia",
                "Male",
                "jairo@email.com");
        personRepository.save(person);
        //When / Act
        Person findedPerson = personRepository.findByJPQL(firstName, lastName);

        //Then / Assert
        assertNotNull(findedPerson);
        assertEquals(firstName, findedPerson.getFirstName());
        assertEquals(lastName, findedPerson.getLastName());

    }

    @DisplayName("Given FirstName And LastName when Find JPQL Named Parameters then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQLNamedParametrs_thenReturnPersonObject() {
        // Given / Arrange
        String firstName = "Jairo";
        String lastName = "Nascimento";
        var person = new Person(
                firstName,
                lastName,
                "Uberlandia",
                "Male",
                "jairo@email.com");
        personRepository.save(person);
        //When / Act
        Person findedPerson = personRepository.findByJPQLNamedParametrs(firstName, lastName);

        //Then / Assert
        assertNotNull(findedPerson);
        assertEquals(firstName, findedPerson.getFirstName());
        assertEquals(lastName, findedPerson.getLastName());

    }
}