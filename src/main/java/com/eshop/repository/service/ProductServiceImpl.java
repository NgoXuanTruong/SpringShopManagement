package com.eshop.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshop.repository.Product;
import com.eshop.repository.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;
	@Override
	public Product getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void create(Product item) {
		dao.save(item);
	}

	@Override
	public void update(Product item) {
		dao.save(item);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}


	@Override
	public Page<Product> findByCategoryId(Integer cid, Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findByCategoryId(cid, pageable);
	}

}
