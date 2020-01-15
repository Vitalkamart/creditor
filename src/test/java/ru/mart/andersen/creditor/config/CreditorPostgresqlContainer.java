package ru.mart.andersen.creditor.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class CreditorPostgresqlContainer extends
        PostgreSQLContainer<CreditorPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:12.1";
    private static CreditorPostgresqlContainer container;

    public CreditorPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static CreditorPostgresqlContainer getInstance() {
        if (container == null) {
            container = new CreditorPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("jdbc:postgresql://localhost:5432/creditor_test", container.getJdbcUrl());
        System.setProperty("creditor", container.getUsername());
        System.setProperty("creditor", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
