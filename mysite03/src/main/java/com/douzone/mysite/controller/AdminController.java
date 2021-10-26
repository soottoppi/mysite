package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.AdminVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Auth(role="ADMIN")
	@RequestMapping({"", "/main"})
	public String main(AdminVo adminVo, Model model) {
		adminVo = adminService.getMainPage(adminVo);
		model.addAttribute("adminVo", adminVo);
		return "admin/main";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping(value="/main/update", method = RequestMethod.POST)
	public String update(AdminVo adminVo) {
		adminService.update(adminVo);
		return "redirect:/admin";
	}
	
	
	@Auth(role="ADMIN")
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
