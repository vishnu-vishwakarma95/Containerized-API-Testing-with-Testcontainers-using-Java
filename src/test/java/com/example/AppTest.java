package com.example;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class AppTest {

    @Container
    public GenericContainer<?> container = new GenericContainer<>("nginx:latest")
            .withExposedPorts(80);

    private String getContainerUrl() {
        Integer mappedPort = container.getMappedPort(80);
        return "http://" + container.getHost() + ":" + mappedPort;
    }

    @Test
    public void testContainerRunning() {
        String containerAddress = getContainerUrl();

        given()
                .when()
                .get(containerAddress)
                .then()
                .statusCode(200)
                .body(containsString("Welcome to nginx!")); // Check welcome message
    }

    @Test
    public void testContainerStatusCode() {
        String containerAddress = getContainerUrl();

        given()
                .when()
                .get(containerAddress)
                .then()
                .statusCode(200); // Check status code
    }

    @Test
    public void testContentType() {
        String containerAddress = getContainerUrl();

        given()
                .when()
                .get(containerAddress)
                .then()
                .contentType("text/html"); // Updated to match actual content type
    }

    @Test
    public void testResponseTime() {
        String containerAddress = getContainerUrl();

        given()
                .when()
                .get(containerAddress)
                .then()
                .time(lessThan(5000L)); // Response time should be less than 5 seconds
    }

    @Test
    public void testServerHeader() {
        String containerAddress = getContainerUrl();

        given()
                .when()
                .get(containerAddress)
                .then()
                .header("Server", "nginx/1.27.1"); // Updated to match actual server version
    }

    @Test
    public void testNotFound() {
        String containerAddress = getContainerUrl();
        String invalidEndpoint = containerAddress + "/invalid-path"; // Use a clearly invalid path

        given()
                .when()
                .get(invalidEndpoint)
                .then()
                .statusCode(404); // Check for not found status
    }

    @Test
    public void testPageTitle() {
        String containerAddress = getContainerUrl();

        String response = given()
                .when()
                .get(containerAddress)
                .asString();

        // Check if the response body contains the page title
        assertTrue(response.contains("<title>Welcome to nginx!</title>"));
    }
}
