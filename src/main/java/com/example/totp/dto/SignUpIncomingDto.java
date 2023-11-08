package com.example.totp.dto;

import com.example.totp.model.Employee;

import lombok.Data;

@Data
public class SignUpIncomingDto {
	private String username;
	private String password;
	private String employeeName;
	
	public Employee DtoToEmployee() {
		Employee emp=new Employee();
		emp.setEmployeeName(this.employeeName);
		emp.setEmail(this.username);
		emp.setPassword(this.password);
		return emp;
	}
}
