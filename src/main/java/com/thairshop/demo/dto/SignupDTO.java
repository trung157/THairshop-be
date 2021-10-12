package com.thairshop.demo.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
	@NotBlank
    @Size(max = 255)
    private String fullName;
	
    @NotBlank
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
    private String numberPhone ;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
}
