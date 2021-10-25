package com.thairshop.demo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thairshop.demo.common.ERole;
import com.thairshop.demo.common.JwtUtils;
import com.thairshop.demo.dto.LoginDTO;
import com.thairshop.demo.dto.SignupDTO;
import com.thairshop.demo.entity.Account;
import com.thairshop.demo.entity.Role;
import com.thairshop.demo.repository.AccountRepository;
import com.thairshop.demo.repository.RoleRepository;
import com.thairshop.demo.response.JwtResponse;
import com.thairshop.demo.response.MessageResponse;
import com.thairshop.demo.service.impl.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity
				.ok(new JwtResponse(jwt, userDetails.getAccountId(), userDetails.getFullName(), userDetails.getEmail(),
						userDetails.getDateOfBirth(), userDetails.getGender(), userDetails.getPhoneNumber(), roles));
	}

	@PostMapping("/signup")
	public Map<String, Object> registerUser(@Valid @RequestBody SignupDTO signUpDTO, Errors err) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (err.hasErrors()) {
				for (ObjectError element : err.getAllErrors()) {
					map.put("er", element.getDefaultMessage());
					return map;
				}
			} else {
				if (accountRepository.existsByEmail(signUpDTO.getEmail())) {
					map.put("status", false);
					return map;
				} else {
					// Create new user's account
					Account user = new Account(signUpDTO.getFullName(), signUpDTO.getDateOfBirth(),
							signUpDTO.getGender(), signUpDTO.getEmail(), signUpDTO.getPhoneNumber(),
							encoder.encode(signUpDTO.getPassword()));
					Set<String> strRoles = signUpDTO.getRole();
					Set<Role> roles = new HashSet<>();
					if (strRoles == null) {
						Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					} else {
						strRoles.forEach(role -> {
							switch (role) {
							case "admin":
								Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(adminRole);

								break;
							default:
								Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(userRole);
							}
						});
					}
					user.setRoles(roles);
					accountRepository.save(user);
					map.put("status", true);
					return map;
				}

			}
		} catch (Exception e) {
			map.put("status", 500);
		}
		return map;
	}

}
