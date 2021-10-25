package com.thairshop.demo.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	@NotNull
	private int accountId;
	@NotBlank(message = "FullName is not null!")
	private String fullName;
	@NotNull(message = "DateOfBirth is not null!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@NotBlank(message = "Gender is not null!")
	private String gender;
	@NotBlank(message = "Email is not null!")
	private String	email;
	@NotBlank(message = "PhoneNumber is not null!")
	private String	phoneNumber;
	
}
