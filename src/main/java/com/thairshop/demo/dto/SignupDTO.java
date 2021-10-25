package com.thairshop.demo.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupDTO {
	@NotBlank
    @Size(min = 3,max = 50)
    private String fullName;
	
    @NotNull
    private Date dateOfBirth;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    @NotBlank
    @Size(max = 50)
    private String gender;
    
    @NotBlank
    @Size(max = 20)
    private String phoneNumber ;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 24)
    private String password;
  
}
