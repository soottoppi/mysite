package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;

	@ResponseBody
	@RequestMapping("/spa")
	public JsonResult spa(
		@RequestParam(value="sn", required=true, defaultValue="-1") Long no) {
		// list = guestbookService.findAll(no)를 사용해서 리스트 가져오기
		List<GuestbookVo> list;
		System.out.println("no값 ==>> " + no);
		if(no == -1) {
			
			list = guestbookService.list();	
		} else {
			list = guestbookService.list(no);
		}
		
		return JsonResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(@RequestBody GuestbookVo vo) {
		guestbookService.add(vo);
		return JsonResult.success(vo);
	}

	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		boolean result = guestbookService.delete(vo);
		Long data = result ? no : -1L; 
		
		return JsonResult.success(data);
	}
	


	
}