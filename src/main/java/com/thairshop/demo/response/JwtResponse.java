package com.thairshop.demo.response;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int accountId;
	private String fullName;
	private String email;
	private Date dateOfBirth;
	private String gender;
	private String phoneNumber;
    private List<String> roles;
	public JwtResponse(String token, int accountId, String fullName, String email, Date dateOfBirth,
			String gender, String phoneNumber, List<String> roles) {
		this.token = token;
		this.accountId = accountId;
		this.fullName = fullName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
	}
    
}
