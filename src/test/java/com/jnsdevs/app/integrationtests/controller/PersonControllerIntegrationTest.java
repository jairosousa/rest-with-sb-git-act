package com.jnsdevs.app.integrationtests.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnsdevs.app.config.TestConfigs;
import com.jnsdevs.app.integrationtests.testcontainers.AbstractIntegrationTest;
import com.jnsdevs.app.model.Person;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * @Autor Jairo Nascimento
 * @Created 30/07/2023 - 18:36
 */

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;

    private static ObjectMapper mapper;

    private static Person person;

    @BeforeAll
    public static void setup() {
        // Given / Arrange
        mapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/api/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person(
                "Jairo",
                "Nascimento",
                "Uberlandia",
                "Male",
                "jairo@email.com");
    }

    @Test
    @Order(1)
    @DisplayName("JUNIT integration Test Given Person Object when Create One Person Should Return A Person Object")
    void integrationTestGivenPersonObject_when_CreateOnePerson_ShouldReturnAPersonObject() throws JsonProcessingException {
        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Person createPerson = mapper.readValue(content, Person.class);

        person = createPerson;

        assertNotNull(createPerson);
        assertNotNull(createPerson.getId());
        assertNotNull(createPerson.getFirstName());
        assertNotNull(createPerson.getLastName());
        assertNotNull(createPerson.getAddress());
        assertNotNull(createPerson.getGender());
        assertNotNull(createPerson.getEmail());

        assertTrue(createPerson.getId() > 0);
        assertEquals("Jairo", createPerson.getFirstName());
        assertEquals("Nascimento", createPerson.getLastName());
        assertEquals("Uberlandia", createPerson.getAddress());
        assertEquals("Male", createPerson.getGender());
        assertEquals("jairo@email.com", createPerson.getEmail());
    }

    @Test
    @Order(2)
    @DisplayName("JUNIT integration Test Given Person Object when Update One Person Should Return A Update Person Object")
    void integrationTestGivenPersonObject_when_UpdateOnePerson_ShouldReturnAUpdatePersonObject() throws JsonProcessingException {

        person.setFirstName("Silvana");
        person.setLastName("Luz");
        person.setEmail("sls@email.com");
        person.setGender("Female");

        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Person updatePerson = mapper.readValue(content, Person.class);

        assertNotNull(updatePerson);
        assertNotNull(updatePerson.getId());
        assertNotNull(updatePerson.getFirstName());
        assertNotNull(updatePerson.getLastName());
        assertNotNull(updatePerson.getAddress());
        assertNotNull(updatePerson.getGender());
        assertNotNull(updatePerson.getEmail());

        assertTrue(updatePerson.getId() > 0);
        assertEquals("Silvana", updatePerson.getFirstName());
        assertEquals("Luz", updatePerson.getLastName());
        assertEquals("Uberlandia", updatePerson.getAddress());
        assertEquals("Female", updatePerson.getGender());
        assertEquals("sls@email.com", updatePerson.getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("JUNIT integration Test Given Person Object when FindById Should Return A Person Object")
    void integrationTestGivenPersonObject_when_FindById_ShouldReturnAPersonObject() throws JsonProcessingException {

        var content = given()
                .spec(specification)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Person dataPerson = mapper.readValue(content, Person.class);

        assertNotNull(dataPerson);
        assertNotNull(dataPerson.getId());
        assertNotNull(dataPerson.getFirstName());
        assertNotNull(dataPerson.getLastName());
        assertNotNull(dataPerson.getAddress());
        assertNotNull(dataPerson.getGender());
        assertNotNull(dataPerson.getEmail());

        assertTrue(dataPerson.getId() > 0);
        assertEquals("Silvana", dataPerson.getFirstName());
        assertEquals("Luz", dataPerson.getLastName());
        assertEquals("Uberlandia", dataPerson.getAddress());
        assertEquals("Female", dataPerson.getGender());
        assertEquals("sls@email.com", dataPerson.getEmail());
    }

}
