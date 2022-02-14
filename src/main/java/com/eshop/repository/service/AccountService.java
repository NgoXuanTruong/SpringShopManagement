package com.eshop.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eshop.admin.bean.AccountFilter;
import com.eshop.repository.Account;

public interface AccountService {

	void create(Account item);

	void update(Account item);

	List<Account> findAll();

	Account getByUsername(String username);

	void deleteByUsername(String username);

	Page<Account> findPageByFilter(AccountFilter filter, Pageable page);



}
