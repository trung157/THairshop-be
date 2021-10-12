package com.thairshop.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thairshop.demo.common.ERole;
import com.thairshop.demo.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByRoleName(ERole roleName);
}