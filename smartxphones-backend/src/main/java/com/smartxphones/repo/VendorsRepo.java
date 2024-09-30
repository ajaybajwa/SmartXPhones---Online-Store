package com.smartxphones.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartxphones.model.Vendors;

public interface VendorsRepo extends JpaRepository<Vendors, Long> {
	
}