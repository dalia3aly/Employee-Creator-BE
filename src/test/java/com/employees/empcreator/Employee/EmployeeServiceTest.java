package com.employees.empcreator.Employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.employees.empcreator.Address.Address;
import com.employees.empcreator.Address.AddressRepository;
import com.employees.empcreator.Employee.dto.CreateEmployeeDTO;
import com.employees.empcreator.Employee.dto.UpdateEmployeeDTO;


class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AddressRepository addressRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        // Arrange
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO();
        Address mockAddress = new Address();
        Employee expectedEmployee = new Employee();

        when(modelMapper.map(any(CreateEmployeeDTO.class), eq(Employee.class))).thenReturn(expectedEmployee);
        when(employeeRepo.save(any(Employee.class))).thenReturn(expectedEmployee);

        when(modelMapper.map(any(), eq(Address.class))).thenReturn(mockAddress);
        when(addressRepo.save(any(Address.class))).thenReturn(mockAddress);
       
        Employee resultEmployee = employeeService.createEmployee(createEmployeeDTO);

        verify(addressRepo, times(2)).save(any(Address.class));
        verify(employeeRepo, times(1)).save(any(Employee.class));

        assertNotNull(resultEmployee);
        assertEquals(expectedEmployee, resultEmployee);
    }

    @Test
    void testFindAllEmployees() {
        employeeService.findAllEmployees();
        verify(employeeRepo, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);

        assertTrue(foundEmployee.isPresent());
        assertEquals(employee, foundEmployee.get());
    }

  @Test
void testUpdateEmployee() {
    Long employeeId = 1L;
    UpdateEmployeeDTO updateEmployeeDTO = new UpdateEmployeeDTO();
    updateEmployeeDTO.setFirstName("Jane");
    updateEmployeeDTO.setLastName("Doe");

    Employee employee = new Employee();
    Address address = new Address();
    address.setId(1L);

    // Mocking the methods
    when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));
    when(modelMapper.map(any(UpdateEmployeeDTO.class), eq(Employee.class))).thenAnswer(invocation -> {
        UpdateEmployeeDTO dto = invocation.getArgument(0);
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        return employee;
    });
    when(modelMapper.map(any(), eq(Address.class))).thenReturn(address);
    when(addressRepo.save(any(Address.class))).thenReturn(address);
    when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

    Optional<Employee> updatedEmployee = employeeService.updateEmployee(employeeId, updateEmployeeDTO);

    assertTrue(updatedEmployee.isPresent());
    verify(employeeRepo, times(1)).findById(employeeId);
    verify(employeeRepo, times(1)).save(any(Employee.class));
    verify(addressRepo, times(1)).save(any(Address.class));

    // Additional assertions to ensure address is set correctly
    assertNotNull(employee.getAddress());
    assertEquals(address, employee.getAddress());
}

    @Test
    void testDeleteEmployeeById() {
        Long employeeId = 1L;
        Employee employee = new Employee();
        when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepo).delete(any(Employee.class));

        boolean isDeleted = employeeService.deleteEmployeeById(employeeId);

        assertTrue(isDeleted);
        verify(employeeRepo, times(1)).findById(employeeId);
        verify(employeeRepo, times(1)).delete(any(Employee.class));
    }

    @Test
    void testFindById() {
        Long employeeId = 1L;
        Employee employee = new Employee();
        when(employeeRepo.findById(anyLong())).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.findById(employeeId);

        assertTrue(foundEmployee.isPresent());
        assertEquals(employee, foundEmployee.get());
    }
}
