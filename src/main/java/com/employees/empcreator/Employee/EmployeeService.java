package com.employees.empcreator.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import com.employees.empcreator.Address.Address;
import com.employees.empcreator.Address.AddressRepository;
import com.employees.empcreator.Employee.dto.CreateEmployeeDTO;
import com.employees.empcreator.Employee.dto.UpdateEmployeeDTO;
import com.employees.exceptions.EmployeeNotFoundException;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepo;

    // Create a new Employee
    public Employee createEmployee(CreateEmployeeDTO employeeDTO) {
        // Map DTO to Employee entity
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        // Map DTO to Address entity and save it
        Address address = modelMapper.map(employeeDTO.getAddress(), Address.class);
        Address savedAddress = addressRepo.save(address);

        // link the saved address to the employee to make sure the address_id column is populated
        employee.setAddress(savedAddress);

        Employee savedEmployee = employeeRepo.save(employee);
        addressRepo.save(savedAddress);

        return savedEmployee;
    }

    // Find all Employees
    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    // Get Employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    // Update an Employee
    public Optional<Employee> updateEmployee(Long id, UpdateEmployeeDTO employeeDTO) {
        Optional<Employee> maybeEmployee = employeeRepo.findById(id);

        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }

        Employee employee = maybeEmployee.get();
        modelMapper.map(employeeDTO, employee);

        Address address;
        if (employee.getAddress() != null) {
            // Update existing address
            address = employee.getAddress();
            modelMapper.map(employeeDTO.getAddress(), address);
        } else {
            // Create new address
            address = modelMapper.map(employeeDTO.getAddress(), Address.class);
        }

        Address savedAddress = addressRepo.save(address);
        employee.setAddress(savedAddress);

        return Optional.of(employeeRepo.save(employee));
    }
   
    // Delete an Employee
    public boolean deleteEmployeeById(Long id) {
        Optional<Employee> maybeEmployee = this.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        this.employeeRepo.delete(maybeEmployee.get());
        return true;
    }

    // Find an Employee by ID
    public Optional<Employee> findById(Long id) {
        if (id == null) {
            throw new EmployeeNotFoundException("Employee ID cannot be null");
        }
        return this.employeeRepo.findById(id);
    }
}
