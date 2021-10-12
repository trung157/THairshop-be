package com.thairshop.demo.dto;

import java.util.Date;

public class AccountDTO {

	private int AccountId;
	private String FullName;
	private Date DateOfBirth;
	private String	Gender;
	private String	Email;
	private String	PhoneNumber;

	public AccountDTO() {
		super();
	}
	public AccountDTO(int accountId, String fullName, Date dateOfBirth, String gender, String email, String phoneNumber) {
		super();
		AccountId = accountId;
		FullName = fullName;
		DateOfBirth = dateOfBirth;
		Gender = gender;
		Email = email;
		PhoneNumber = phoneNumber;
	}
	public int getAccountId() {
		return AccountId;
	}
	public void setAccountId(int accountId) {
		AccountId = accountId;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public Date getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
}
