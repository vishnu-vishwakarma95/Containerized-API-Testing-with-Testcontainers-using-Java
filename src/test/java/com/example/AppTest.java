package com.example;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Testcontainers
public class AppTest {

    @Container
    public GenericContainer<?> container = new GenericContainer<>("nginx:latest")
        .withExposedPorts(80);

    @Test
    public void testContainerRunning() {
        Integer mappedPort = container.getMappedPort(80);
        String containerAddress = "http://" + container.getHost() + ":" + mappedPort;

        // Test 1 (Failure): Assert HTML body contains 'Welcome to nginx!'
        given()
            .when()
            .get(containerAddress)
            .then()
            .statusCode(200)
            .body("html.body", equalTo("Welcome to nginx!"));  // This will fail due to HTML formatting differences
    }

    @Test
    public void testContainerStatusCode() {
        Integer mappedPort = container.getMappedPort(80);
        String containerAddress = "http://" + container.getHost() + ":" + mappedPort;

        // Test 2 (Success): Assert the status code is 200
        given()
            .when()
            .get(containerAddress)
            .then()
            .statusCode(200);  // This should pass as the Nginx server is running
    }
}
