package com.eshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
	@Query("SELECT o FROM Product o ")
	List<Product> findByCategoryId(Integer cid);

}
