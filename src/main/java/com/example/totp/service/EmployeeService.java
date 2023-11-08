package com.example.totp.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.totp.dto.LoginIncomingDto;
import com.example.totp.model.Employee;


@Service
@Component
public interface EmployeeService {

	Employee getEmployeeById(long employeeId);
	Employee saveEmployeeData(Employee employee);
	void deleteEmployeeById(long employeeId);
	void deleteEmployee(Employee employee);	
	Optional<Employee> validateEmployee(LoginIncomingDto dto);
}


