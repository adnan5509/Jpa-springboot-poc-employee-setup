package com.adnanafzalbajwa.springbootJpaDemo.contoller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserControllerWithTestContainer {

    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.4.0")
            .withDatabaseName("employeedb")
            .withUsername("root")
            .withPassword("admin");

    @DynamicPropertySource
    private static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    }

    @Test
    @DisplayName("Check if MySql Test Container is created and running")
    void testMySqlTestContainerIsCreatedAndRunning(){
        assertTrue(mySQLContainer::isCreated,"MySql Test Container is Created");
        assertTrue(mySQLContainer::isRunning,"MySql Test Container is Running");
    }

}
