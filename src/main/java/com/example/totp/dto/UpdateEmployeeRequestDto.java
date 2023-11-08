package com.example.totp.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UpdateEmployeeRequestDto {
	private String employeeName;
	private String email;
	private String password;
	private Set<Long> designationIds;
	private long laptopId;
	private long departmentId;
}
