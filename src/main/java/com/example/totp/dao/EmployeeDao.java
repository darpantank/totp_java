package com.example.totp.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.totp.model.Employee;


public interface EmployeeDao extends CrudRepository<Employee, Long> {
	public Optional<Employee> findByEmail(String email);
	public Optional<Employee> findByEmailAndPassword(String username, String password);
	public void deleteById(long employeeId);
}
