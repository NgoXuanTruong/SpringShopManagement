package com.eshop.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshop.repository.Order;
import com.eshop.repository.OrderDAO;
import com.eshop.repository.OrderDetailDAO;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO orderDao;
	@Autowired
	OrderDetailDAO oderDetailDao;
	@Override
	public Order getById(Long id) {
		// TODO Auto-generated method stub
		return orderDao.getById(id);
	}

	@Override
	public void create(Order item) {
		orderDao.save(item);
	}

	@Override
	public void update(Order item) {
		orderDao.save(item);
	}

	@Override
	public void deleteById(Long id) {
		orderDao.deleteById(id);
	}

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public Page<Order> findByStatusId(Integer statusId, Pageable pageAble) {
		// TODO Auto-generated method stub
		return orderDao.findByStatusId(statusId, pageAble);
	}

}
