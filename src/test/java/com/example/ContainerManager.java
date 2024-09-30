package com.example;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class ContainerManager {

    private GenericContainer<?> container;

    public void startContainer(String imageName, int exposedPort) {
        container = new GenericContainer<>(DockerImageName.parse(imageName))
            .withExposedPorts(exposedPort);
        container.start();
    }

    public String getContainerHost() {
        return container.getHost();
    }

    public Integer getContainerPort(int exposedPort) {
        return container.getMappedPort(exposedPort);
    }

    public void stopContainer() {
        if (container != null) {
            container.stop();
        }
    }
}
