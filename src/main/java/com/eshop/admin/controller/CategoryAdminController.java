package com.eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.repository.Category;
import com.eshop.repository.service.CategoryService;

@Controller
public class CategoryAdminController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/admin/category/index")
	public String index(Model model) {

//		List<Category> items = categoryService.findAll() ;
		model.addAttribute("item", new Category());
//		model.addAttribute("items", items);
		return this.forward(model);
	}
	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id ) {
		Category item = categoryService.getById(id);
//		List<Category> items = categoryService.findAll() ;
		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		return this.forward(model);
	}
	@RequestMapping("/admin/category/create")
	public String create (Model model, @Validated @ModelAttribute("item") Category item, BindingResult errors) {
//		Category item = dao.getById(id);
		if(errors.hasErrors()) {
			model.addAttribute("message", "Please fix errors bellow !");
		}	
		else if(item.getId()!=null){
			model.addAttribute("message", "Item is existed!");
		}
		else {
			try {
				categoryService.create(item);
				model.addAttribute("message", "Create Successfully!");
				return "forward:/admin/category/index";
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("message", "Create Failture!");
			}
		}
//		List<Category> items = categoryService.findAll() ;
//		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		return this.forward(model);
	}
	@RequestMapping("/admin/category/update")
	public String update (Model model,@Validated @ModelAttribute("item") Category item, BindingResult errors ) {
//		Category item = dao.getById(id);
		if(errors.hasErrors()) {
			model.addAttribute("message", "Please fix errors bellow !");
		}else if(item.getId()== null) {
			model.addAttribute("message", "Item not found!");
		}
		
		else{
			try {
				categoryService.update(item);
				model.addAttribute("message", "Update Successfully!");
				return "forward:/admin/category/index";
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("message", "Update Failture!");
			}
		}

//		List<Category> items = categoryService.findAll() ;
//		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		return this.forward(model);
	}
	@RequestMapping("/admin/category/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id ) {
		try {
			categoryService.deleteById(id);
			model.addAttribute("message", "Delete succesfully!");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "Delete Failture!");
		}
		Category item = new Category();
		model.addAttribute("item", item);
		return this.forward(model);
	}
	@RequestMapping("/admin/category/reset")
	public String reset(Model model) {

		return "redirect:/admin/category/index";
	}
	String forward(Model model) {
		List<Category> items = categoryService.findAll();
		model.addAttribute("items", items);
		return "admin/category/index";
	}
}
