package com.jnsdevs.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnsdevs.app.exceptions.ResourceNotFoundException;
import com.jnsdevs.app.model.Person;
import com.jnsdevs.app.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Autor Jairo Nascimento
 * @Created 29/07/2023 - 15:57
 */
@WebMvcTest
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
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

    @Test
    @DisplayName("JUnit test for Given Person Object When Create Person then Return Saved Person")
    void testGivenPersonObject_WhenCreatePerson_thenReturnSavedPerson() throws Exception {

        //Given / Arrange
        given(personServices.create(any(Person.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        //When / Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/person")
                .contentType(APPLICATION_JSON)
                .content(mapper
                        .writeValueAsString(person)));

        //Then / Assert
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));

    }

    @Test
    @DisplayName("JUnit test for Given list Persons when findAll Person then Return Person list")
    void testGivenListOfPersons_WhenCreatePerson_thenReturnPersonList() throws Exception {

        //Given / Arrange
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(new Person(
                "Silvana",
                "Luz",
                "Uberlândia",
                "Female",
                "sls@email.com"
        ));

        given(personServices.findAll()).willReturn(persons);

        //When / Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/person"));

        //Then / Assert
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(persons.size())));

    }

    @Test
    @DisplayName("JUnit test for Given personId when findById then Return Person Object")
    void testGivenPersonId_WhenFindById_thenReturnPersonObject() throws Exception {

        //Given / Arrange
        Long personId = 1L;
        given(personServices.findById(personId)).willReturn(person);

        //When / Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/person/{id}", personId));

        //Then / Assert
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));

    }

    @Test
    @DisplayName("JUnit test for Given Invavid personId when findById then Return not found")
    void testGivenInvalidPersonId_WhenFindById_thenReturnNotFound() throws Exception {

        //Given / Arrange
        Long personId = 1L;
        given(personServices.findById(personId)).willThrow(ResourceNotFoundException.class);

        //When / Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/person/{id}", personId));

        //Then / Assert
        response
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    @DisplayName("JUnit test for Given update Person when Update then Return Updated Person Object")
    void testGivenUpdatePerson_WhenUpdate_thenReturnUpdatedPersonObject() throws Exception {

        //Given / Arrange
        Long personId = 1L;
        person.setId(personId);
        given(personServices.findById(person.getId())).willReturn(person);
        person.setFirstName("Silvana");
        person.setLastName("Luz");
        person.setEmail("sls@email.com");
        given(personServices.update(person)).willAnswer((invocation -> invocation.getArgument(0)));

        //When / Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/person")
                .contentType(APPLICATION_JSON)
                .content(mapper
                        .writeValueAsString(person)));

        //Then / Assert
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));

    }

    @Test
    @DisplayName("JUnit test for Given inexistent Person when Update then Return Updated Not Found")
    void testGivenUnexistentPerson_WhenUpdate_thenReturnNotFound() throws Exception {

        //Given / Arrange
        Long personId = 1L;
        person.setId(personId);
        given(personServices.findById(person.getId())).willThrow(ResourceNotFoundException.class);
        given(personServices.update(person)).willAnswer((invocation -> invocation.getArgument(1)));

        //When / Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/person")
                .contentType(APPLICATION_JSON)
                .content(mapper
                        .writeValueAsString(person)));

        //Then / Assert
        response
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    @DisplayName("JUnit test for Given personId when Delete then Return Not Content")
    void testGivenPersonId_WhenDelete_thenReturnNotContent() throws Exception {

        //Given / Arrange
        Long personId = 1L;
        willDoNothing().given(personServices).delete(personId);

        //When / Act
        ResultActions response = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/person/{id}", personId));

        //Then / Assert
        response
                .andExpect(status().isNoContent())
                .andDo(print());

    }

}