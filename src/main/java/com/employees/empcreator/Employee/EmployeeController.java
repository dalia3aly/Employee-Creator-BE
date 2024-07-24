package com.employees.empcreator.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.employees.empcreator.Employee.dto.CreateEmployeeDTO;
import com.employees.empcreator.Employee.dto.UpdateEmployeeDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Creates a new employee using the provided employee data.
     *
     * @param  employeeDTO   the employee data to create the new employee with
     * @return                the created employee as a ResponseEntity
     */
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

    /**
     * Retrieves an employee by their ID.
     *
     * @param  id	the ID of the employee to retrieve
     * @return     	a ResponseEntity containing the retrieved employee, or a not found response if the employee is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> maybeEmployee = employeeService.getEmployeeById(id);
        if (maybeEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maybeEmployee.get());
    }

    /**
     * Updates an employee with the provided ID.
     *
     * @param  id         the ID of the employee to update
     * @param  updatedData the data to update the employee with
     * @return             a ResponseEntity containing the updated employee, or an empty Optional if the employee is not found
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDTO updatedData) {
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, updatedData);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Deletes an employee by their ID.
     *
     * @param  id    the ID of the employee to delete
     * @return       a ResponseEntity with a status code indicating the success or failure of the deletion
     */
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
