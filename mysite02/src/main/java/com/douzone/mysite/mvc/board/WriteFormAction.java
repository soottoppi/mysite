package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}
		
		Long currentPage = Long.parseLong(request.getParameter("page"));
		request.setAttribute("currentPage", currentPage);
		MvcUtil.forward("board/writeform", request, response);
	}

}
