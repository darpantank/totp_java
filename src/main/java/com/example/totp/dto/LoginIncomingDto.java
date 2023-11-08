package com.example.totp.dto;

import lombok.Data;

@Data
public class LoginIncomingDto {
	private String username;
	private String password;
}
