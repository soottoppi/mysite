package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao boardDao = new BoardDao();
		Long postNo = Long.parseLong(request.getParameter("postNo"));
		Long currentPage = Long.parseLong(request.getParameter("page"));
		String kwd = request.getParameter("kwd");
				
		BoardVo boardVo = boardDao.findPost(postNo);
		
		Cookie[] cookies = request.getCookies();
		int visitor = 0;
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("visit")) {
				visitor = 1;
				cookie.setMaxAge(60);
				System.out.println("visit 통과");
				
				if(cookie.getValue().contains(request.getParameter("postNo"))) {
					System.out.println("visit if 통과");
				} else {
					cookie.setValue(cookie.getValue() + "_" + request.getParameter("postNo"));
					response.addCookie(cookie);
					boardDao.updateHit(boardVo);
					
				}
			}
		}
		
		if(visitor == 0) {
			Cookie newCookie = new Cookie("visit", request.getParameter("postNo"));
			newCookie.setMaxAge(60);
			response.addCookie(newCookie);
			boardDao.updateHit(boardVo);
		}
		
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("kwd", kwd);
		MvcUtil.forward("/board/view", request, response);
	}

}
