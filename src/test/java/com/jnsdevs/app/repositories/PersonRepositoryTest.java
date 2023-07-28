package com.jnsdevs.app.repositories;

import com.jnsdevs.app.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}