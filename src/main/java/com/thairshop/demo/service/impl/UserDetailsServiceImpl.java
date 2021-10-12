package com.thairshop.demo.service.impl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thairshop.demo.entity.Account;
import com.thairshop.demo.repository.AccountRepository;
import com.thairshop.demo.service.AccountService;

@Service
public class UserDetailsServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;


	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Optional<Account> findById(int id) {
		return accountRepository.findById(id);
	}

	@Override
	public Account save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
	}

	@Override
	public void remove(int id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Account> userOptional = accountRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        return UserDetailsImpl.build(userOptional.get());

	}

}