package com.smartxphones.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartxphones.model.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Long> {

}
