package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean search = true;
		String kwd;
		if((kwd = request.getParameter("kwd")) == null) {
			search = false;
		}
		Long currentPage;
		if(request.getParameter("page") == null) {
			currentPage = 1L;
		} else {
			currentPage = Long.parseLong(request.getParameter("page"));
			if(currentPage < 1) {
				currentPage = 1L;
			}
		}
		
		BoardDao dao = new BoardDao();
		Long findPageCount = null;
		if(search) {
			findPageCount = dao.findSearchPageCount(kwd);
		} else {
			findPageCount = dao.findPageCount();
			
		}
		Long pageCount = findPageCount / 10 + (findPageCount % 10 == 0 ? 0 : 1);
		if(currentPage > pageCount) {
			currentPage = pageCount;
		}
		
		Long pageBlock = 5L;
		Long startPage = ((currentPage - 1L) / pageBlock) * pageBlock + 1L;
		Long endPage = startPage + pageBlock - 1L;
		
		
		List<BoardVo> list = null;
		Long pageNoForRow = (currentPage - 1) * 10;
		if(search) {
			list = dao.searchPost(kwd, pageNoForRow);
		} else {
			list = dao.findAll(pageNoForRow);	
		}

		
		
		
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("kwd", kwd);
		MvcUtil.forward("/board/list", request, response);
	}

}
