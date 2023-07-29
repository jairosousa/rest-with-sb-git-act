package com.jnsdevs.app.services;

import com.jnsdevs.app.exceptions.ResourceNotFoundException;
import com.jnsdevs.app.model.Person;
import com.jnsdevs.app.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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

    @DisplayName("Given Person Object when Sav ePerson then Return Person Object")
    @Test
    void testGivenPersonObject_whenSavePerson_thenReturnPersonObject() {
        // Given
        given(personRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(personRepository.save(person)).willReturn(person);

        //When
        var savedPerson = personServices.create(person);

        //Then
        assertNotNull(savedPerson);
        assertEquals("Jairo", savedPerson.getFirstName());

    }

    @DisplayName("Given ExistEmail when Save Person Object then Return Trows Exception")
    @Test
    void testGivenExistEmail_whenSavePersonObject_thenTrowsException() {
        // Given
        given(personRepository.findByEmail(anyString())).willReturn(Optional.of(person));

        //When
        var exception = assertThrows(ResourceNotFoundException.class, () -> {
            personServices.create(person);
        });

        //Then
        assertNotNull(exception);
        assertEquals("Person already exist given e-mail: " + person.getEmail(), exception.getMessage());
        verify(personRepository, never()).save(any(Person.class));

    }

    @DisplayName("JUNIT test for Given Person List when find all Person then Return Person List")
    @Test
    void testGivenPersonList_whenFindAllPerson_thenReturnPersonList() {
        // Given
        var person1 = new Person(
                "Silvana",
                "Luz",
                "Uberlandia",
                "Female",
                "jairo@email.com");
        given(personRepository.findAll()).willReturn(List.of(person, person1));

        //When
        List<Person> personList = personServices.findAll();

        //Then
        assertNotNull(personList);
        assertEquals(2, personList.size());

    }

    @DisplayName("JUNIT test for Given Empty Person List when find all Person then Return Person Empty List")
    @Test
    void testGivenEmptyPersonList_whenFindAllPerson_thenReturnPersonEmptyList() {
        // Given
        given(personRepository.findAll()).willReturn(Collections.emptyList());

        //When
        List<Person> personList = personServices.findAll();

        //Then
        assertNotNull(personList);
        assertTrue(personList.isEmpty());
        assertEquals(0, personList.size());

    }

    @DisplayName("JUNIT test for Given PersonId when findById then Return Person Object")
    @Test
    void testGivenPersonId_whenFindById_thenReturnPersonObject() {
        // Given
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));

        //When
        var findPerson = personServices.findById(1L); // pode ser qualquer long

        //Then
        assertNotNull(findPerson);
        assertEquals("Jairo", findPerson.getFirstName());

    }

    @DisplayName("Given Person Object when update Person then Return Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject() {
        // Given
        person.setId(1l);
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));

        person.setFirstName("Silvana");
        person.setEmail("sls@email.com");

        //When / Act
        given(personRepository.save(person)).willReturn(person);
        var updatePerson = personServices.update(person);

        //Then / Assert
        assertNotNull(updatePerson);
        assertEquals("sls@email.com", updatePerson.getEmail());
        assertEquals("Silvana", updatePerson.getFirstName());

    }
}