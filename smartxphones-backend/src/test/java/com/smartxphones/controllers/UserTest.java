package com.smartxphones.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartxphones.controller.UserController;
import com.smartxphones.email.EmailService;
import com.smartxphones.model.Address;
import com.smartxphones.model.Orders;
import com.smartxphones.model.Product;
import com.smartxphones.model.ProductCategory;
import com.smartxphones.model.Review;
import com.smartxphones.model.Role;
import com.smartxphones.model.User;
import com.smartxphones.model.UserCart;
import com.smartxphones.model.UserCartId;
import com.smartxphones.model.Vendors;
import com.smartxphones.repo.RoleRepo;
import com.smartxphones.repo.UserRepo;
import com.smartxphones.service.OrderService;
import com.smartxphones.service.RoleService;
import com.smartxphones.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserTest {
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private RoleService roleService;
	
	@MockBean
	private EmailService emailService;
	
	@MockBean
	private OrderService orderService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepo userRepo;
	
	@MockBean
	private RoleRepo roleRepo;
	
	@MockBean
	Vendors vendors;
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
	
	long id= 1L;
	List<Orders> o = new ArrayList<>();
	Set<Role> r = new HashSet<>();
	List<Address> a = new ArrayList<>();
	List<UserCart> userCart = new ArrayList<>();
	List<Product> products = new ArrayList<>();
	List<Review> reviews = new ArrayList<>();
	
	@MockBean
	ProductCategory pc;
	
	@MockBean
	UserCartId userCartId;
	
	@MockBean
	Product product;
	
	@MockBean
	User user;
	
	@BeforeEach
	void setUp() throws Exception {
		
		userService = new UserService(userRepo, roleRepo);
		user = new User(id,"3", "firstName","lastName","email","username","password","contact","ssn",o,r,a,userCart);
		pc = new ProductCategory(id, "category", products);
		product = new Product(id,"Lenovo Laptop","Legion 5 latop",
				new BigDecimal(15),new BigDecimal(15), 3,"sample URL",3,o,pc,userCart, vendors, reviews);
		userCartId = new UserCartId(user.getUserId(), product.getProductId());
	}
	
	@Test
	void testFindAllUsers() throws Exception {
		// Instantiate necessary objects
		List<Orders> orders = new ArrayList<>();
		Set<Role> roles = new HashSet<>();
		List<Address> addresses = new ArrayList<>();
		
		User user = new User(1L, "3", "Ajaydeep", "Singh", "Ajaydeep1717@gmail.com", "Jay", "123", "919", "8604",
				orders, roles, addresses, null);
		
		// List to compare
		List<User> users = new ArrayList<>();
		users.add(user);
		
		Mockito.when( userService.getAllUsers()).thenReturn(users);
		mockMvc.perform(get("/user/admin"))
				.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/user/customer/update")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void testGetUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/customer/" + user.getUserId())
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void testAddRoleToUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/admin/" + user.getUserId() + "/role/" + 1L)
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

}
