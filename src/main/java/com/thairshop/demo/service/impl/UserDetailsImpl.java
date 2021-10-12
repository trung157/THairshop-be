package com.thairshop.demo.service.impl;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thairshop.demo.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int accountId;
	private String fullName;
	private String email;
	private Date dateOfBirth;
	private String gender;
	private String phoneNumber;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;




	public UserDetailsImpl(int accountId, String fullName, String email, Date dateOfBirth, String gender,
			String phoneNumber, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.accountId = accountId;
		this.fullName = fullName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Account account) {
		List<GrantedAuthority> authorities = account.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				account.getAccountId(), 
				account.getFullName(),
				account.getEmail(),
				account.getDateOfBirth(),
				account.getGender(),
				account.getPhoneNumber(),
				account.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String getUsername() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl account = (UserDetailsImpl) o;
		return Objects.equals(accountId, account.accountId);
	}


}