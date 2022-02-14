package com.eshop.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshop.admin.bean.AccountFilter;
import com.eshop.repository.Account;
import com.eshop.repository.AccountDAO;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDAO dao;

	@Override
	public void create(Account item) {
		dao.save(item);
		
	}

	@Override
	public void update(Account item) {
	dao.save(item);
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account getByUsername(String username) {
		return dao.getById(username);
	}

	@Override
	public void deleteByUsername(String username) {
		dao.deleteById(username);
	}

	@Override
	public Page<Account> findPageByFilter(AccountFilter filter, Pageable page) {
		String keyword = "%"+filter.getKeyword()+"%";
		//account
		if(filter.getRole() == 2) {
			if(filter.getActivated() == 2) {
				return dao.findAccountByKeyword(keyword, page);
			}
			return dao.findAccountByKeywordAndActivated(keyword, filter.getActivated() == 1, page);
		}
		//customer
		if(filter.getRole() == 0) {
			if(filter.getActivated() == 2) {
				return dao.findCustomerByKeyword(keyword, page);
			}
			return dao.findCustomerByKeywordAndActivated(keyword, filter.getActivated() == 1 , page);
		}
		//master
		if(filter.getActivated() == 2) {
			return dao.findMasterByKeyword(keyword, page);
		}
		return dao.findMasterByKeywordAndActivated(keyword, filter.getActivated() == 1 , page);
		
		
	}


}
