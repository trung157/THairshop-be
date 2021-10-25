package com.thairshop.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@Entity
@Table(name = "Account", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Account implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int accountId;

	@NotBlank(message = "FullName is not null!")
	@Column(name = "full_name")
	private String fullName;

	@NotNull(message = "DateOfBirth is not null!")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@NotNull(message = "Gender is not null!")
	@Column(name = "gender")
	private String gender;

	@NotNull(message = "Email is not null!")
	@Pattern(regexp = "^\\w{2,}.?\\w+(@\\w{3,8})(.\\w{3,8})+$", message = "Email is malformed! ex: abcabc@abc.abc")
	@Column(name = "email")
	private String email;

	@NotNull(message = "PhoneNumber is not null!")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull(message = "Password is not null!")
	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<Order> orders;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Account_Role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Account(String fullName, Date dateOfBirth, String gender, String email, String phoneNumber,
			String password) {
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

}
