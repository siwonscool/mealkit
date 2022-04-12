package com.site.controller;

import java.util.ArrayList;
import java.util.Map;

import com.site.order.vo.Top3ReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.site.order.OrderService;
import com.site.product.ProductService;
import com.site.vo.ProductVo;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ProductService productService;
	private final OrderService orderService;
	
	@RequestMapping("index")
	public String index(Model model) {
		ArrayList<ProductVo> productList = productService.productTopList();
		Top3ReviewVo map = orderService.Top3reviewList();
		model.addAttribute("p_list", productList);
		model.addAttribute("map", map);
		return "/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/register";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/logout";
	}

}
