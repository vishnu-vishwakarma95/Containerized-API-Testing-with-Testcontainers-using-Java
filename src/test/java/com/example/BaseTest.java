package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected ContainerManager containerManager;

    @BeforeEach
    public void setUp() {
        containerManager = new ContainerManager();
        // Start a container with default values; can be overridden in specific tests
        containerManager.startContainer("nginx:latest", 80);
    }

    @AfterEach
    public void tearDown() {
        containerManager.stopContainer();
    }
}
