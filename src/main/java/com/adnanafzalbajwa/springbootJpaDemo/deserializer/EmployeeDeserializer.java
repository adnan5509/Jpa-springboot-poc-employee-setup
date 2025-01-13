package com.adnanafzalbajwa.springbootJpaDemo.deserializer;

import com.adnanafzalbajwa.springbootJpaDemo.model.Employee;
import com.adnanafzalbajwa.springbootJpaDemo.model.FullTimeEmployee;
import com.adnanafzalbajwa.springbootJpaDemo.model.PartTimeEmployee;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class EmployeeDeserializer extends JsonDeserializer<Employee> {

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String employeeType = node.get("employeeType").asText(); // Identify type from JSON payload

        return switch (employeeType) {
            case "FULL_TIME" -> new FullTimeEmployee(
                    node.get("name").asText(),
                    node.get("age").asInt(),
                    node.get("salary").asDouble()
            );
            case "PART_TIME" -> new PartTimeEmployee(
                    node.get("name").asText(),
                    node.get("age").asInt(),
                    node.get("hourlyRate").asDouble()
            );
            default -> throw new IllegalArgumentException("Unknown employee type: " + employeeType);
        };
    }
}
