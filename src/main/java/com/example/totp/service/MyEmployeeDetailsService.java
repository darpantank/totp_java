package com.example.totp.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.totp.dao.EmployeeDao;
//implements UserDetailsService
@Service
public class MyEmployeeDetailsService  {
	@Autowired
	EmployeeDao employeeDao;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		 Optional<Employee> emp= employeeDao.findByEmployeeName(username);
//		 emp.orElseThrow(()->new EmployeeNotPresentException());
//		 return emp.map(MyUserDetails::new).get();
//	}

}
