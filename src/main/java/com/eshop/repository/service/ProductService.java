package com.eshop.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eshop.repository.Product;

public interface ProductService {

	Product getById(Integer id);

	void create(Product item);

	void update(Product item);

	void deleteById(Integer id);

	List<Product> findAll();

	Page<Product> findByCategoryId(Integer cid, Pageable pageable);



}
