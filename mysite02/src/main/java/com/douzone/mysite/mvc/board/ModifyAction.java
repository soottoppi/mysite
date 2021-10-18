package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao boardDao = new BoardDao();
		Long postNo = Long.parseLong(request.getParameter("postNo"));
		Long currentPage = Long.parseLong(request.getParameter("page"));
		BoardVo boardVo = boardDao.findPost(postNo);
		boardVo.setTitle(request.getParameter("title"));
		boardVo.setContents(request.getParameter("contents"));
		
		
		HttpSession session = request.getSession(false);
		UserVo authUserVo = (UserVo)session.getAttribute("authUser");
		
		if(authUserVo != null) {
			if(boardVo.getUserNo() == authUserVo.getNo()) {
				boardDao.updateTitleAndContents(boardVo);
			} 
		}
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=view&postNo=" + postNo +"&page=" + currentPage, request, response);
	}

}
