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
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Address address = modelMapper.map(employeeDTO.getAddress(), Address.class);
        employee.setAddress(address);
        return employeeRepo.save(employee);
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
    public Optional<Employee> updateEmployeeById(Long id, UpdateEmployeeDTO updatedData) {
        Optional<Employee> maybeEmployee = this.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }

        Employee foundEmployee = maybeEmployee.get();
        modelMapper.map(updatedData, foundEmployee);
        
        Address address = modelMapper.map(updatedData.getAddress(), Address.class);
        address = addressRepo.save(address);
        foundEmployee.setAddress(address);
        

        return Optional.of(employeeRepo.save(foundEmployee));
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
