package com.jnsdevs.app.repositories;

import com.jnsdevs.app.integrationtests.testcontainers.AbstractIntegrationTest;
import com.jnsdevs.app.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Autor Jairo Nascimento
 * @Created 28/07/2023 - 11:30
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    PersonRepository personRepository;

    private Person person;

    @BeforeEach
    public void setup() {
        // Given / Arrange
        person = new Person(
                "Jairo",
                "Nascimento",
                "Uberlandia",
                "Male",
                "jairo@email.com");
    }

    @DisplayName("Given Person Object when Save then Return Saved Person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnSavedPerson() {

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
        var person1 = new Person(
                "Silvana",
                "Luz",
                "Uberlandia",
                "Female",
                "jairo@email.com");
        personRepository.save(person);
        personRepository.save(person1);

        //When / Act
        List<Person> personList = personRepository.findAll();

        //Then / Assert
        assertNotNull(personList);
        assertTrue(personList.size() > 2);
        System.out.println("TAMANHO_LISTA: "+personList.size());
    }

    @DisplayName("Given Person Object when find by ID then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByID_thenReturnPersonObject() {

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

        personRepository.save(person);
        //When / Act
        Person findedPerson = personRepository.findByJPQLNamedParametrs(firstName, lastName);

        //Then / Assert
        assertNotNull(findedPerson);
        assertEquals(firstName, findedPerson.getFirstName());
        assertEquals(lastName, findedPerson.getLastName());

    }

    @DisplayName("Given FirstName And LastName when Find Native SQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJNativeSQL_thenReturnPersonObject() {
        // Given / Arrange
        String firstName = "Jairo";
        String lastName = "Nascimento";

        personRepository.save(person);
        //When / Act
        Person findedPerson = personRepository.findByNativeSQL(firstName, lastName);

        //Then / Assert
        assertNotNull(findedPerson);
        assertEquals(firstName, findedPerson.getFirstName());
        assertEquals(lastName, findedPerson.getLastName());

    }

    @DisplayName("Given FirstName And LastName when Find Native SQL with Named Parametrs then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindByNativeSQLWithNamedParametrs_thenReturnPersonObject() {
        // Given / Arrange
        String firstName = "Jairo";
        String lastName = "Nascimento";

        personRepository.save(person);
        //When / Act
        Person findedPerson = personRepository.findByNativeSQLWithNamedParametrs(firstName, lastName);

        //Then / Assert
        assertNotNull(findedPerson);
        assertEquals(firstName, findedPerson.getFirstName());
        assertEquals(lastName, findedPerson.getLastName());

    }
}