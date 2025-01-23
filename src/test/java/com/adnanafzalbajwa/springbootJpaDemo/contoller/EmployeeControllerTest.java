package com.adnanafzalbajwa.springbootJpaDemo.contoller;

import com.adnanafzalbajwa.springbootJpaDemo.dto.EmployeeDto;
import com.adnanafzalbajwa.springbootJpaDemo.request.CreateEmployeeRequest;
import com.adnanafzalbajwa.springbootJpaDemo.response.CreateEmployeeResponse;
import com.adnanafzalbajwa.springbootJpaDemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {EmployeeController.class})
class EmployeeControllerTest {

    @MockitoBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testCreateEmployee_whenValidInputs_ReturnValidOutput() throws Exception {
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        createEmployeeRequest.setName("Adnan Afzal Bajwa");
        createEmployeeRequest.setAge(31);
        createEmployeeRequest.setSalary(100000);
        createEmployeeRequest.setEmail("adnanafzal5509@gmail.com");
        createEmployeeRequest.setDateOfJoining(new Date());

        EmployeeDto employeeDto = new ModelMapper().map(createEmployeeRequest, EmployeeDto.class);

        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createEmployeeRequest));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String response = mvcResult.getResponse().getContentAsString();

        CreateEmployeeResponse createEmployeeResponse = new ObjectMapper().readValue(response, CreateEmployeeResponse.class);

        assertNotNull(createEmployeeResponse.getId(), "The returned employee should have an ID");
        assertEquals(createEmployeeRequest.getName(), createEmployeeResponse.getName(), "Returned Employee should have the same name");
        assertEquals(createEmployeeRequest.getAge(), createEmployeeResponse.getAge(), "Returned Employee should have the same name");
        assertEquals(createEmployeeRequest.getEmail(), createEmployeeResponse.getEmail(), "Returned Employee should have the same name");
        assertEquals(createEmployeeRequest.getContactNo(), createEmployeeResponse.getContactNo(), "Returned Employee should have the same name");
        assertEquals(createEmployeeRequest.getSalary(), createEmployeeResponse.getSalary(), "Returned Employee should have the same name");
        assertEquals(createEmployeeRequest.getDateOfJoining(), createEmployeeResponse.getDateOfJoining(), "Returned Employee should have the same name");


    }
}