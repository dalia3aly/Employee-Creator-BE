package com.employees.empcreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employees.empcreator.dto.CreateEmployeeDTO;
import com.employees.empcreator.dto.UpdateEmployeeDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeDTO employeeDTO) {
        Employee createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> maybeEmployee = employeeService.getEmployeeById(id);
        if (maybeEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maybeEmployee.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable Long id,
            @RequestBody UpdateEmployeeDTO updatedData) {
        Optional<Employee> updatedEmployee = employeeService.updateEmployeeById(id, updatedData);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if (maybeEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
