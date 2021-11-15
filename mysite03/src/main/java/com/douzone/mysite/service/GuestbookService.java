package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> list() {
		return guestbookRepository.findAll();
	}
	
	public List<GuestbookVo> list(Long no) {
		return guestbookRepository.findWithNo(no);
	}
	
	public boolean delete(GuestbookVo vo) {
		return guestbookRepository.delete(vo);
	}
	
	
	public void add(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
	
}
