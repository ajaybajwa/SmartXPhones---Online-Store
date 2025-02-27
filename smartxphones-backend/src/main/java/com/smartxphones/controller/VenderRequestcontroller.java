package com.smartxphones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartxphones.model.VendorRequest;
import com.smartxphones.service.VenderRequestService;

@CrossOrigin(origins = { "http://localhost:4201","smartxstore.azurewebsites.net" })
@RestController
@RequestMapping("/vendor/request")
public class VenderRequestcontroller {

	@Autowired
	private VenderRequestService service;
	
	@GetMapping("/{id}")
	public VendorRequest getVendorRequest(@PathVariable int id) {
		if(!service.getVenderRequestById(id).isPresent()) {
			return null;
		}
		return service.getVenderRequestById(id).get();
	}
	
	@GetMapping()
	public List<VendorRequest> getAllVendors() {
		return service.getVenderRequests();
	}
	
	@PostMapping("/add")
	public void addVendorRequest(@RequestBody VendorRequest vendorRequest) {
		service.addVenderRequest(vendorRequest);
	}
	
	@PutMapping("/update")
	public void updateStatus(@RequestBody VendorRequest vendorRequest) {
		service.changeRequestStatus(vendorRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteVendorRequest(@PathVariable int id) {
		service.deleteVendorRequest(id);
	}
}
