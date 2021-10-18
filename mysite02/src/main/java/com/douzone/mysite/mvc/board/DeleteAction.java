package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long postNo = Long.parseLong(request.getParameter("postNo"));
		String password = request.getParameter("password");
		Long currentPage = Long.parseLong(request.getParameter("page"));

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}
		
		new BoardDao().delete(postNo, password);
		MvcUtil.redirect(request.getContextPath() + "/board" + "?page=" + currentPage, request, response);

	}

}
