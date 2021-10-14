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
		// title, contents, hit, reg_date, group_no, order_no, depth, user_no,
		// title, contests 가져온다
		// hit는 1로 고정(list에서 게시물 클릭 시 마다 1증가)
		// reg_date는 now()로 고정
		// group_no는 최초 입력 시 null 이므로 1로 지정, max(group_no) + 1 해서 넣어준다
		// order_no, depth는 0, 0으로 고정
		// user_no 는 현재 세션의 user_no를 가져온다
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return;
		}
		System.out.println();
		
		BoardVo boardVo = new BoardVo();
		BoardDao boardDao = new BoardDao();
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long userNo = authUser.getNo();
		Long groupNo = boardDao.findGroupNo() + 1L;
		
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setGroupNo(groupNo);
		boardVo.setUserNo(userNo);
		
		boardDao.insert(boardVo);
		
		MvcUtil.redirect("/mysite02/board", request, response);
	}

}
