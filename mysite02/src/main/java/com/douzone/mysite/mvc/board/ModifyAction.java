package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = new BoardVo();
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long groupNo = Long.parseLong(request.getParameter("groupNo"));
		Long orderNo = Long.parseLong(request.getParameter("orderNo"));
		Long depth = Long.parseLong(request.getParameter("depth"));
		
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setGroupNo(groupNo);
		boardVo.setOrderNo(orderNo);
		boardVo.setDepth(depth);
		
		boardDao.updateTitleAndContents(boardVo);
		
		MvcUtil.redirect("/mysite02/board", request, response);
	}

}
