package com.smartxphones.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.smartxphones.controller.AddressController;
import com.smartxphones.model.Address;
import com.smartxphones.model.User;
import com.smartxphones.service.AddressService;
import com.smartxphones.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
@AutoConfigureMockMvc(addFilters = false)
class AddressTest {	
	
	@Mock
	User user;
	
	@Mock
	Address address;
	
	@MockBean
	private AddressService addressService;
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
		
	@BeforeEach
	void setup() {
		user = new User();
		address = new Address(1L, "Calgary", "AB", "62 Horne Dr", "27545", "Canda", null, user);
	}
	
	@Test
	void testFindAllAddresses() throws Exception {
		// Instantiate necessary objects
		
		// List to compare
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		
		Mockito.when( addressService.getAllAddresses()).thenReturn(addresses);
		mockMvc.perform(get("/address"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].city", Matchers.is("Calgary")));
	}
	
	@Test
	void testupdateAddress() throws Exception {
		Address newAddress = new Address();
		newAddress.setAddressId(1L);
		newAddress.setCity("Brampton");
		newAddress.setStreet("5 Rollingwood Dr.");
		newAddress.setZipcode("27526");
		newAddress.setCountry("Canada");
		newAddress.setApartmentNumber(null);
		newAddress.setUser(user);
		newAddress.setState("ON");
		newAddress.toString();
		Mockito.when(addressService.updateAddress(newAddress)).thenReturn(newAddress);
		assertEquals(newAddress, addressService.updateAddress(newAddress));
		verify(addressService).updateAddress(newAddress);
	}

}
