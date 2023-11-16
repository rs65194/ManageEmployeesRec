package com.employee.management.entity;

import java.sql.Date;

/**
* Employee is POJO .
*/
public class Employee {
	
	    private int employeeId;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phoneNumbers;
	    private Date doj;
	    private Double salary;
	    
	    public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumbers() {
			return phoneNumbers;
		}
		public void setPhoneNumbers(String phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
		}
		public Date getDoj() {
			return doj;
		}
		public void setDoj(Date doj) {
			this.doj = doj;
		}
		public Double getSalary() {
			return salary;
		}
		public void setSalary(Double salary) {
			this.salary = salary;
		}
		

}
