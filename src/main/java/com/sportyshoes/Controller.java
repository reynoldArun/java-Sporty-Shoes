package com.sportyshoes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private PurchaseRepo purchaserepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@PostMapping("/admin/product")
	public String createProduct(@RequestBody ProductEntity product) {
		prepo.save(product);
		return "Product Created Successfully!!";
	}
	
	@GetMapping("/admin/product/{id}")
	public Optional<ProductEntity> getProductById(@PathVariable int id) {
		return prepo.findById(id);
	}
	
	@GetMapping("/admin/product")
	public List<ProductEntity> getAllProduct() {
		return prepo.findAll();
	}
	
	@DeleteMapping("/admin/product/{id}")
	public String deleteProduct(@PathVariable int id) {
		prepo.deleteById(id);
		return "Product Deleted Sucessfully!!!";
	}
	
	@PutMapping("/admin/product/{id}")
	public String UpdateProduct(@PathVariable int id, @RequestBody ProductEntity product) {
		ProductEntity newproduct = prepo.getById(id);
		newproduct.setName(product.getName());
		newproduct.setCategory(product.getCategory());
		newproduct.setPrice(product.getPrice());
		prepo.save(newproduct);
		return "Product Update Sucessfully!";
	}
	
	@GetMapping("/admin/Allreports")
	public List<PurchaseEntity> getAllreport() {
		return purchaserepo.findAll();
	}
	
	@GetMapping("/admin/report/{id}")
	public PurchaseEntity getsingleReport(@PathVariable int id) {
		return purchaserepo.getById(id);
	}
	
	@DeleteMapping("/admin/report/{id}")
	public String deleteReport(@PathVariable int id, PurchaseEntity purchase) {
		purchaserepo.deleteById(id);
		return "purchase.getId()+ Report deleted successfully";
	}
	
	@PutMapping("/admin/report/{id}")
	public String UpdateReport(@PathVariable int id, @RequestBody PurchaseEntity purchase) {
		PurchaseEntity newpurchase = purchaserepo.getById(id);
		newpurchase.setUser(purchase.getUser());
		newpurchase.setProductName(purchase.getProductName());
		newpurchase.setProductCategory(purchase.getProductCategory());
		purchaserepo.save(newpurchase);
		return "Report Update Sucessfully!";
	}
	
	@PostMapping("/register")
	public String register(@RequestBody UserEntity user) {
		userrepo.save(user);
		return "Registered successfully!!";
	}
	
	@PostMapping("/login")
	public List<UserEntity> login(@RequestBody UserEntity user) {
		UserEntity checkuser = user;
		 userrepo.findByUsername(checkuser.getUsername());
		 userrepo.findById(checkuser.getUserid());
		 return userrepo.findAll();
		
	}
}
