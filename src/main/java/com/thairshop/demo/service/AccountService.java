package com.thairshop.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.thairshop.demo.entity.Account;
import com.thairshop.demo.service.impl.AccountServiceImpl;

public interface AccountService extends AccountServiceImpl<Account>, UserDetailsService{
	Optional<Account> findByEmail(String email);

}
