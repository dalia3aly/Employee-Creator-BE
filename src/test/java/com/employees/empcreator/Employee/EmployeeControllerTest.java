package com.employees.empcreator.Employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employees.empcreator.Employee.dto.CreateEmployeeDTO;
import com.employees.empcreator.Employee.dto.UpdateEmployeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    // POST test

    @Test
    public void createEmployee_ReturnsEmployee() throws Exception {
        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO();
        Employee employee = new Employee();

        given(employeeService.createEmployee(any(CreateEmployeeDTO.class))).willReturn(employee);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // GET test
    @Test
    public void getAllEmployees_ReturnsEmployeeList() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        given(employeeService.findAllEmployees()).willReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // GET by ID test

    // When Employee is found
    @Test
    public void getEmployeeById_WhenFound_ReturnsEmployee() throws Exception {
        Long employeeId = 1L;
        Employee employee = new Employee();

        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

        mockMvc.perform(get("/api/employees/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // When Employee is not found
    @Test
    public void getEmployeeById_WhenNotFound_ReturnsNotFound() throws Exception {
        Long employeeId = 1L;

        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/employees/{id}", employeeId))
                .andExpect(status().isNotFound());
    }

    // PATCH test

    @Test
    public void updateEmployee_ReturnsUpdatedEmployee() throws Exception {
        Long employeeId = 1L;
        UpdateEmployeeDTO updatedData = new UpdateEmployeeDTO();
        Optional<Employee> updatedEmployee = Optional.of(new Employee());

        given(employeeService.updateEmployee(eq(employeeId), any(UpdateEmployeeDTO.class))).willReturn(updatedEmployee);

        mockMvc.perform(patch("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedData)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // DELETE test

    // When Employee is found
    @Test
    public void deleteEmployee_WhenFound_PerformsDeletion() throws Exception {
        Long employeeId = 1L;
        given(employeeService.findById(employeeId)).willReturn(Optional.of(new Employee()));

        mockMvc.perform(delete("/api/employees/{id}", employeeId))
                .andExpect(status().isNoContent());
    }

    // When Employee is not found
    @Test
    public void deleteEmployee_WhenNotFound_ReturnsNotFound() throws Exception {
        Long employeeId = 1L;
        given(employeeService.findById(employeeId)).willReturn(Optional.empty());

        mockMvc.perform(delete("/api/employees/{id}", employeeId))
                .andExpect(status().isNotFound());
    }
}