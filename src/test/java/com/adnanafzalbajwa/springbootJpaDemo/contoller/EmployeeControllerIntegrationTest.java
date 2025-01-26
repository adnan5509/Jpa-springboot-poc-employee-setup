package com.adnanafzalbajwa.springbootJpaDemo.contoller;

import com.adnanafzalbajwa.springbootJpaDemo.response.CreateEmployeeResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class EmployeeControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
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
}