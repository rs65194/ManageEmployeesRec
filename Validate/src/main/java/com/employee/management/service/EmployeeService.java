package com.employee.management.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.employee.management.dto.TaxDeductionDTO;
import com.employee.management.entity.Employee;

/**
* EmployeeService is for insert and calculate employee in database.
*/
@Service
public class EmployeeService {

	public ResponseEntity<String> storeEmployeeDetails(Employee employee, JdbcTemplate jdbcTemplate) {

		if(employee.getEmployeeId() == 0 ) {
			return new ResponseEntity<>("Employee id should not empty !", HttpStatus.BAD_REQUEST);
		} else if ( employee.getFirstName()  == null && employee.getFirstName().length() ==0) {
			return new ResponseEntity<>("Employee First name should not empty !", HttpStatus.BAD_REQUEST);
		} else if ( employee.getLastName()  == null && employee.getLastName().length() ==0) {
			return new ResponseEntity<>("Employee last  name should not empty !", HttpStatus.BAD_REQUEST);
		} else if ( employee.getEmail()  == null && employee.getEmail().length() ==0) {
			return new ResponseEntity<>("Employee emailid  should not empty !", HttpStatus.BAD_REQUEST);
		} else if ( employee.getPhoneNumbers()  == null && employee.getPhoneNumbers().length() ==0) {
			return new ResponseEntity<>("Employee phone number should not empty !", HttpStatus.BAD_REQUEST);
		} else if ( employee.getDoj()  == null) {
			return new ResponseEntity<>("Employee DOJ should not empty !", HttpStatus.BAD_REQUEST);
		} else if ( employee.getSalary()  == null ) {
			return new ResponseEntity<>("Employee Salary should not empty !", HttpStatus.BAD_REQUEST);
		}
		String sql = "INSERT INTO Employee (employeeId, firstName, lastName, email, phoneNumbers, doj, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
				employee.getEmail(), employee.getPhoneNumbers(), employee.getDoj(), employee.getSalary());

		return new ResponseEntity<>("Employee added successfully!", HttpStatus.CREATED);

	}

	public List<TaxDeductionDTO> calculateTaxDeductionForCurrentYear(JdbcTemplate jdbcTemplate) {

		String queryString = "Select * from Employee";
		List<Employee> listEmployee = jdbcTemplate.query(queryString, BeanPropertyRowMapper.newInstance(Employee.class));	

		List<TaxDeductionDTO> dtos = new ArrayList<TaxDeductionDTO>();
		for(Employee emp : listEmployee) {
			
			TaxDeductionDTO dto = new TaxDeductionDTO();
			dto.setEmployeeCode(emp.getEmployeeId());
			dto.setFirstName(emp.getFirstName());
			dto.setLastName(emp.getLastName());

			LocalDate currentDate = LocalDate.now();
			LocalDate localDate = (emp.getDoj()).toLocalDate();
			Period period = Period.between(localDate, currentDate);
			int monthsDifference = period.getMonths();
			if(monthsDifference == 0) {
				dto.setYearlySalary(emp.getSalary());
			} else {
				dto.setYearlySalary(emp.getSalary() * monthsDifference);
			}
			dto.setTaxAmount(calculateTax(dto.getYearlySalary()));
			dto.setCessAmount(calculateCess(dto.getYearlySalary()));
			dtos.add(dto);
		}		
		return dtos;
	}
	public double calculateCess(double salary) {
		if(salary > 2500000) {
			return (salary - 2500000) * 0.02;
		} else {
			return 0;
		}
	}

	public double calculateTax(double salary) {

		if (salary <= 250000) {
			return 0;
		} else if (salary <= 500000) {
			return (salary - 250000) * 0.05;
		} else if (salary <= 1000000) {
			return 250000 + (salary - 500000) * 0.1;
		} else {
			return 750000 + (salary - 1000000) * 0.2;
		}   
	}


}
