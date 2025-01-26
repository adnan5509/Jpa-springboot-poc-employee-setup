package com.adnanafzalbajwa.springbootJpaDemo.contoller;

import com.adnanafzalbajwa.springbootJpaDemo.response.CreateEmployeeResponse;
import com.adnanafzalbajwa.springbootJpaDemo.response.GetEmployeeResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class UserControllerWithTestContainer {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Container
    @ServiceConnection
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.4.0");


    @Test
    @Order(1)
    @DisplayName("Check if MySql Test Container is created and running")
    void testMySqlTestContainerIsCreatedAndRunning() {
        assertTrue(mySQLContainer::isCreated, "MySql Test Container is Created");
        assertTrue(mySQLContainer::isRunning, "MySql Test Container is Running");
    }

    @Test
    @Order(2)
    void testCreateEmployeeIntegration_whenValidInputs_ReturnValidOutput() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Adnan Afzal Bajwa");
        jsonObject.put("age", 31);
        jsonObject.put("salary", 100000.0);
        jsonObject.put("dateOfJoining", new Date());
        jsonObject.put("contactNo", "1234567");
        jsonObject.put("email", "adnanafzal5509@gmail.com");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toString(), httpHeaders);

        ResponseEntity<CreateEmployeeResponse> createEmployeeResponseResponseEntity = testRestTemplate.postForEntity("/employee", httpEntity, CreateEmployeeResponse.class);

        CreateEmployeeResponse responseBody = createEmployeeResponseResponseEntity.getBody();

        assertEquals(HttpStatus.OK.value(), createEmployeeResponseResponseEntity.getStatusCode().value());
        assertEquals(jsonObject.get("name"), responseBody.getName(), "Returned Employee should have the same name");
        assertEquals(jsonObject.get("age"), responseBody.getAge(), "Returned Employee should have the same age");
        assertEquals(jsonObject.get("email"), responseBody.getEmail(), "Returned Employee should have the same Email");
        assertEquals(jsonObject.get("contactNo"), responseBody.getContactNo(), "Returned Employee should have the same Contact Number");
        assertEquals(jsonObject.get("salary"), responseBody.getSalary(), "Returned Employee should have the same salary");
        assertEquals(jsonObject.get("dateOfJoining").toString(), responseBody.getDateOfJoining().toString(), "Returned Employee should have the " +
                "same Date of Joining");
    }

    @Test
    @Order(3)
    void testGetAllEmployees_whenValidInputs_ReturnsListOfUsers() throws Exception {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<GetEmployeeResponse>> getAllEmployeesResponseEntity = testRestTemplate
                .exchange
                        ("/employee",
                                HttpMethod.GET,
                                requestEntity,
                                new ParameterizedTypeReference<>() {
                                }
                        );
        List<GetEmployeeResponse> responseBody = getAllEmployeesResponseEntity.getBody();
        assertEquals(HttpStatus.OK.value(), getAllEmployeesResponseEntity.getStatusCode().value());
        assertEquals(1, responseBody.size(), "Returns the Only created Employee");
    }


}
