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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao boardDao = new BoardDao();
		BoardVo postVo = null;
		BoardVo childVo = new BoardVo();
		boolean reply = false;
		Long postNo = null;
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}

		// 답글에 필요한 정보
		if(!request.getParameter("postNo").isEmpty()) {
			postNo = Long.parseLong(request.getParameter("postNo"));
			// 부모 글의 정보
			postVo = boardDao.findPost(postNo);
			reply = true;
		}
		
		Long currentPage = Long.parseLong(request.getParameter("page"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long groupNo;
		Long orderNo;
		Long depth;
		Long userNo = authUser.getNo();
		
		// 답글인 경우
		if(reply) {
			groupNo = postVo.getGroupNo();
			orderNo = postVo.getOrderNo() +1L;
			depth = postVo.getDepth() +1L;
		} else {
			groupNo = boardDao.findMaxGroupNo() + 1L;
			orderNo = 1L;
			depth = 0L;
		}
		
		childVo.setTitle(title);
		childVo.setContents(contents);
		childVo.setUserNo(userNo);
		childVo.setGroupNo(groupNo);
		childVo.setOrderNo(orderNo);
		childVo.setDepth(depth);
		
		if(reply) {
			boardDao.updateOrderNo(childVo);
		}
		boardDao.insert(childVo);
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
