package com.eshop.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.repository.Role;
import com.eshop.repository.RoleDAO;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO dao;
	
	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

}
