package com.smartxphones.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.smartxphones.model.Address;
import com.smartxphones.model.Orders;
import com.smartxphones.model.Role;
import com.smartxphones.model.User;
import com.smartxphones.model.UserCart;
import com.smartxphones.model.UsersDetails;

@ExtendWith(MockitoExtension.class)
class UserDetailsTest {
	
	@Mock
	UsersDetails userDetails;
	
	@Mock
	User user;
	
	long id= 1;
	List<Orders> o = new ArrayList<>();
	Set<Role> r = new HashSet<>();
	List<Address> a = new ArrayList<>();
	List<UserCart> userCart = new ArrayList<>();
	List <User> users = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		user = new User(id,"3","firstName","lastName","email","username","password","contact","ssn",o,r,a,userCart);
		users.add(user);
		r.add(new Role(1L, "ROLE_ADMIN", users));
		userDetails = new UsersDetails(user);
	}
	
	@Test
	void testGetterMethods() {
		assertEquals("username", userDetails.getUsername());
		assertEquals("password", userDetails.getPassword());
		assertFalse(userDetails.getAuthorities().isEmpty());
		assertTrue(userDetails.isAccountNonExpired());
		assertTrue(userDetails.isAccountNonLocked());
		assertTrue(userDetails.isCredentialsNonExpired());
		assertTrue(userDetails.isEnabled());
		
	}

}
