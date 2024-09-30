package com.smartxphones.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartxphones.model.VendorRequest;

public interface VenderRequestRepo extends JpaRepository<VendorRequest, Integer> {

}
