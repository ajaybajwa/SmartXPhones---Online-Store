package com.smartxphones.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartxphones.model.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {

}
