package com.eshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.repository.Order;
import com.eshop.repository.Status;
import com.eshop.repository.service.OrderService;
import com.eshop.repository.service.StatusService;
import com.eshop.service.session.SessionService;

@Controller
public class OrderAdminController {
	@Autowired
	OrderService orderService;
	@Autowired
	StatusService statusService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/admin/order/paginate/{pageNumber}")
	public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		model.addAttribute("edit", false);
		return "forward:/admin/order/index";
	}
	@RequestMapping("/admin/order/status/{statusId}")
	public String status(Model model, @PathVariable("statusId") Integer statusId) {
		sessionService.set("statusId", statusId);
		return "forward:/admin/order/paginate/0";
	}
	@RequestMapping("/admin/order/index")
	public String index(Model model) {

//		List<Order> items = orderService.findAll() ;
		model.addAttribute("item", new Order());
//		model.addAttribute("items", items);
		model.addAttribute("edit", false);
		return this.forward(model);
	}
	@RequestMapping("/admin/order/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id ) {
		Order item = orderService.getById(id);
//		List<Order> items = orderService.findAll() ;
		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	@RequestMapping("/admin/order/create")
	public String create (Model model, @Validated @ModelAttribute("item") Order item, BindingResult errors) {
//		Order item = dao.getById(id);
		orderService.create(item);
		model.addAttribute("message", "Create Successfully!");
//		List<Order> items = orderService.findAll() ;
//		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	@RequestMapping("/admin/order/update")
	public String update (Model model,@Validated @ModelAttribute("item") Order item, BindingResult errors ) {
//		Order item = dao.getById(id);
		orderService.update(item);
		model.addAttribute("message", "Update Successfully!");

//		List<Order> items = orderService.findAll() ;
//		model.addAttribute("item", item);
//		model.addAttribute("items", items);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	@RequestMapping("/admin/order/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id ) {
		orderService.deleteById(id);
		Order item = new Order();
		model.addAttribute("item", item);
		model.addAttribute("edit", false);
		return this.forward(model);
	}
	@RequestMapping("/admin/order/reset")
	public String reset(Model model) {
		model.addAttribute("edit", true);
		return "redirect:/admin/order/index";
	}
	String forward(Model model) {
		Integer statusId = sessionService.get("statusId", 0);
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageAble = PageRequest.of(pageNumber, 8);
		Page<Order> page = orderService.findByStatusId(statusId, pageAble);
		model.addAttribute("page", page);
		return "admin/order/index";
	}
	@ModelAttribute("statuses")
	public List<Status> getStatuses(){
		return statusService.findAll();
	}
}
