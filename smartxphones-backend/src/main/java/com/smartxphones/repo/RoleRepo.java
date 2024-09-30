package com.smartxphones.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartxphones.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	
	@Query("SELECT r FROM Role r WHERE r.role = ?1")
	public Role getRoleByName(String name);
}
