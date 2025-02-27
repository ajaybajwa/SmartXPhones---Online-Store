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

import com.smartxphones.model.Role;
import com.smartxphones.service.RoleService;

@CrossOrigin(origins = {"http://localhost:4201","smartxstore.azurewebsites.net" })
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/{id}")
	public Role getrole(@PathVariable Long id) {
		return roleService.getRoleById(id);
	}
	
	@GetMapping()
	public List<Role> getroles() {
		return roleService.getAllRoles();
	}
	
	@PostMapping("/add")
	public void addrole(@RequestBody Role role) {
		roleService.addRole(role);
	}
	
	@PutMapping("/update")
	public void updaterole(@RequestBody Role role) {
		roleService.updateRole(role);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleterole(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
}
