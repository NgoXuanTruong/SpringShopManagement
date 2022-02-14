package com.eshop.admin.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.admin.bean.AccountFilter;
import com.eshop.repository.Account;
import com.eshop.repository.Role;
import com.eshop.repository.service.AccountService;
import com.eshop.repository.service.RoleService;
import com.eshop.service.session.SessionService;
import com.eshop.service.upload.UploadService;

@Controller
public class AccountAdminController {
	@Autowired
	AccountService accountService;
	@Autowired
	UploadService uploadService;
	@Autowired
	SessionService sessionService;
	@Autowired
	RoleService roleService;
	
	
	@RequestMapping("/admin/account/paginate/{pageNumber}")
	public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		model.addAttribute("edit", false);
		return "forward:/admin/account/index";
	}
	
	@RequestMapping("/admin/account/filter")
	public String filterRole(AccountFilter filter) {
		sessionService.set("filter", filter);
		return "forward:/admin/account/paginate/0";
	}
	@RequestMapping("/admin/account/index")
	public String index(Model model) {
//		List<Account> items = accountService.findAll() ;
		model.addAttribute("item", new Account());
//		model.addAttribute("items", items);
		model.addAttribute("edit", false);
		return this.forward(model);
	}
	@RequestMapping("/admin/account/edit/{id}")
	public String edit(Model model, @PathVariable("id") String username ) {
		Account item = accountService.getByUsername(username);
//		List<Account> items = accountService.findAll() ;
		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	@RequestMapping("/admin/account/create")
	public String create (Model model, @Validated @ModelAttribute("item") Account item,@RequestPart("photo_file") MultipartFile photoFile) {
//		Account item = dao.getById(id);
		if(!photoFile.isEmpty()) {
			File photo = uploadService.save(photoFile, "/images/photos/");
			item.setPhoto(photo.getName());
		}
		accountService.create(item);
		model.addAttribute("message", "Create successfully!");
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	@RequestMapping("/admin/account/update")
	public String update (Model model,@Validated @ModelAttribute("item") Account item, @RequestPart("photo_file") MultipartFile photoFile ) {
//		
		if(!photoFile.isEmpty()) {
			File photo = uploadService.save(photoFile, "/images/photos/");
			item.setPhoto(photo.getName());
		}
		accountService.update(item);
		model.addAttribute("message", "Update Successfully!");
//		List<Account> items = accountService.findAll() ;
//		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	@RequestMapping("/admin/account/delete/{id}")
	public String delete(Model model, @PathVariable("id") String username ) {
		try {
			accountService.deleteByUsername(username);
			model.addAttribute("message", "Delete succesfully!");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "Delete Failture!");
		}
		Account item = new Account();
		model.addAttribute("item", item);
		model.addAttribute("edit", false);
		return this.forward(model);
	}
	@RequestMapping("/admin/account/reset")
	public String reset(Model model) {
		model.addAttribute("edit", true);
		return "redirect:/admin/account/index";
	}
	String forward(Model model) {
		AccountFilter filter = sessionService.get("filter", new AccountFilter());
		model.addAttribute("filter", filter);
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageAble = PageRequest.of(pageNumber, 10);
		Page<Account> page = accountService.findPageByFilter(filter, pageAble);
		model.addAttribute("page", page);
		return "admin/account/index";
	}

	@ModelAttribute("roles")
	public List<Role> roles(){
		return roleService.findAll();
	}
}
