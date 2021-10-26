package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.AdminRepository;
import com.douzone.mysite.vo.AdminVo;

@Service
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	
	public boolean update(AdminVo adminVo) {
		return adminRepository.update(adminVo);
	}

	public AdminVo getMainPage(AdminVo adminVo) {
		return adminRepository.getMainPage(adminVo);
	}
	
}
