package com.thairshop.demo.repository;

import com.thairshop.demo.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Optional<Account> findByEmail(String email);
	Optional<Account> findById(int accountId);
	Boolean existsByEmail(String email);
}
