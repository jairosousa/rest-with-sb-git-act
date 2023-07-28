package com.jnsdevs.app.services;

import com.jnsdevs.app.model.Person;
import com.jnsdevs.app.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @Autor Jairo Nascimento
 * @Created 28/07/2023 - 16:59
 */

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServices personServices;

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

    @DisplayName("Given Person Object whenSavePerson then Return Person Object")
    @Test
    void testGivenPersonObject_whenSavePerson_thenReturnPersonObject() {
        // Given
        given(personRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(personRepository.save(person)).willReturn(person);

        //When
        var savedPerson = personServices.create(person);

        //Then
        assertNotNull(savedPerson);
        assertEquals("Jairo", savedPerson.getFirstName());;

    }
}