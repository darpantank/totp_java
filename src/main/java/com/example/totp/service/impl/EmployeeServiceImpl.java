package com.example.totp.service.impl;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.totp.dao.EmployeeDao;
import com.example.totp.dto.LoginIncomingDto;
import com.example.totp.global_variable.GlobalVariables;
import com.example.totp.model.Employee;
import com.example.totp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService,GlobalVariables {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee getEmployeeById(long employeeId) {
		Optional<Employee> employee= this.employeeDao.findById(employeeId);
		if(employee.isPresent())
			return employee.get();
		return null;
	}
	@Override
	public Employee saveEmployeeData(Employee employee) {
		return this.employeeDao.save(employee);
	}
	@Override
	public void deleteEmployeeById(long employeeId) {
		this.employeeDao.deleteById(employeeId);
	}
//	@Override
//	public Employee updateEmployeeData(long employeeId,UpdateEmployeeRequestDto employee) {
//		Employee employeeFromDb = employeeDao.findById(employeeId).orElseThrow(() -> new EmployeeNotPresentException());
//		employeeFromDb.setEmployeeName(employee.getEmployeeName());
//		employeeFromDb.setEmail(employee.getEmail());
//		employeeFromDb.setPassword(employee.getPassword());
//		employeeFromDb.setLaptop(new Laptop(employee.getLaptopId()));
//		return employeeDao.save(employeeFromDb);
//	}



	@Override
	public void deleteEmployee(Employee employee) {
		this.employeeDao.delete(employee);
	}

	
	@Override
	public Optional<Employee> validateEmployee(LoginIncomingDto dto) {
		Optional<Employee> emp=employeeDao.findByEmail(dto.getUsername());
		if(emp.isPresent()) {
			if(emp.get().getPassword().equals(dto.getPassword())) {
				return Optional.of(emp.get());
						};
		}
		return null;
	}
}
