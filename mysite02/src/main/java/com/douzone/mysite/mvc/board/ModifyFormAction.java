package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long groupNo = Long.parseLong(request.getParameter("groupNo"));
		Long orderNo = Long.parseLong(request.getParameter("orderNo"));
		Long depth = Long.parseLong(request.getParameter("depth"));
		
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = new BoardVo();
		boardVo.setGroupNo(groupNo);
		boardVo.setOrderNo(orderNo);
		boardVo.setDepth(depth);
		
		boardVo = boardDao.findTitleAndContents(boardVo);
		
		request.setAttribute("boardVo", boardVo);
		MvcUtil.forward("/board/modifyform", request, response);
	}

}
