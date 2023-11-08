package com.example.totp.dto;

import lombok.Data;

@Data
public class LoginOutgoingDto {
	private String userName;
	private boolean isValidUser;
}
