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

}
