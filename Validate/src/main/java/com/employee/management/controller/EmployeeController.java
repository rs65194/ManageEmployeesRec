package com.employee.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employee.management.dto.TaxDeductionDTO;
import com.employee.management.entity.Employee;
import com.employee.management.service.EmployeeService;

/**
* EmployeeController is for insert and calculate employee in database.
*/
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@PostMapping(value = "/storeDetails", consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> storeEmployeeDetails(@RequestBody Employee employee) {
		return employeeService.storeEmployeeDetails(employee, jdbcTemplate);		
	}

	@GetMapping("/taxDeduction")
    public ResponseEntity<List<TaxDeductionDTO>> getTaxDeductionForCurrentYear() {
		List<TaxDeductionDTO> taxDeductions = employeeService.calculateTaxDeductionForCurrentYear(jdbcTemplate);
        return ResponseEntity.ok(taxDeductions);
    } 
}
