package com.eshop.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.repository.Status;
import com.eshop.repository.StatusDAO;

@Service
public class StatusServiceImpl implements StatusService {
	@Autowired
	StatusDAO dao;
	@Override
	public List<Status> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
