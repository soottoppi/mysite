package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryServiceException;
import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ServletContext servletContext;

	@Autowired
	SiteRepository siteRepository;

	@Autowired
	private SiteService siteService;

	@Auth(role = "ADMIN")
	@RequestMapping({ "", "/main" })
	public String main(Model model) {
		SiteVo site = siteService.getSite();
		model.addAttribute("site", site);
		return "admin/main";
	}

	@Auth(role = "ADMIN")
	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String update(@RequestParam("file") MultipartFile file, SiteVo siteVo) {
		try {
			String url = siteService.saveImg(file);
			siteVo.setProfile(url);
		} catch (GalleryServiceException e) {
			System.out.println("사진이 선택되지 않았습니다.");
		}
		System.out.println(siteVo);
		siteService.update(siteVo);
		servletContext.setAttribute("site", siteVo);
		
		return "redirect:/admin";
	}

	@Auth(role = "ADMIN")
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@Auth(role = "ADMIN")
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@Auth(role = "ADMIN")
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
