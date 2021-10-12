package com.thairshop.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thairshop.demo.common.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Role")
public class Role implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
	
	@Enumerated(EnumType.STRING)
    private ERole roleName;
	
}
