package com.smartxphones.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smartxphones.email.EmailService;
import com.smartxphones.model.Product;
import com.smartxphones.model.VendorRequest;
import com.smartxphones.service.ProductService;
import com.smartxphones.service.VenderRequestService;

@CrossOrigin(origins = { "http://localhost:4201" })
@RestController
public class RestockController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private VenderRequestService venderRequestService;
	
	
	 public void requestVendorRestock(Long id) {
		 Product product = this.productService.getProductById(id);
		 try {
			 this.emailService.sendVendorEmail(product.getVendors().getEmail(), product, 5);
			 VendorRequest vendorRequest = new VendorRequest(0, product, 5, LocalDateTime.now(ZoneId.of("America/Toronto")) ,product.getVendors(),"Sent");
			 int reqId = this.venderRequestService.addVenderRequest(vendorRequest);
			 this.receiveVendorRequest(reqId, product);	 
		 }
		 catch (Exception e) {
			
		}
	 }
	 
	 public void receiveVendorRequest(int reqId, Product product) {
		 VendorRequest vendorRequest = this.venderRequestService.getVenderRequestById(reqId).get();
		 vendorRequest.setStatus("Received");
		 this.venderRequestService.changeRequestStatus(vendorRequest);
		 
		 product.setQuantity(product.getQuantity()+vendorRequest.getQuantityrequested());
		 productService.updateProduct(product);	 
	 }
}
