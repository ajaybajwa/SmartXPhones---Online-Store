package com.smartxphones.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartxphones.model.UserCart;
import com.smartxphones.model.UserCartId;

@Repository
public interface UserCartRepo extends JpaRepository<UserCart, UserCartId>{

}
