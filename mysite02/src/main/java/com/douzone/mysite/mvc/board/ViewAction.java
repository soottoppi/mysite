package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
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
		
		boardDao.updateHit(boardVo);
		
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("kwd", kwd);
		MvcUtil.forward("/board/view", request, response);
	}

}
