package com.eshop.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eshop.repository.Order;

public interface OrderService {

	Order getById(Long id);

	void create(Order item);

	void update(Order item);

	void deleteById(Long id);

	List<Order> findAll();

	Page<Order> findByStatusId(Integer statusId, Pageable pageAble);




}
