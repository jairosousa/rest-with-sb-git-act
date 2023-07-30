package com.jnsdevs.app.integrationtests.swagger;

import com.jnsdevs.app.config.TestConfigs;
import com.jnsdevs.app.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

/**
 * @Autor Jairo Nascimento
 * @Created 30/07/2023 - 18:09
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("JUNIT test for Should Display Swagger Ui Page")
    void testShouldDisplaySwaggerUiPage() {
        var content = given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertTrue(content.contains("Swagger UI"));

    }
}
