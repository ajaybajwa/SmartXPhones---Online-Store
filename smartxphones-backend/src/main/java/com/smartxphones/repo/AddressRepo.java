package com.smartxphones.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartxphones.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
	
}
