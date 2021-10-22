package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(
			@RequestParam(value="page", required=true, defaultValue="1") Long page,
			@RequestParam(value="kwd", required=false) String kwd,
			Model model) {
		boolean search = false;
		
		Long currentPage = page;
		Long findPageCount = null;
		
		findPageCount = boardService.findPageCount();
			
		Long pageCount = findPageCount / 10 + (findPageCount % 10 == 0 ? 0 : 1);
		if(currentPage > pageCount) {
			currentPage = pageCount;
		}
		
		Long pageBlock = 5L;
		Long startPage = ((currentPage - 1L) / pageBlock) * pageBlock + 1L;
		Long endPage = startPage + pageBlock - 1L;
		
		
		Long pageNoForRow = (currentPage - 1) * 10;
		if(search) {
//			list = boardService.searchPost(kwd, pageNoForRow);
		} else {
			model.addAttribute("list", boardService.findAll(pageNoForRow));
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("kwd", kwd);
		return "board/list";
	}
	
	@RequestMapping(value="/view/{postNo}", method = RequestMethod.GET)
	public String view(
			@PathVariable("postNo") Long postNo,
			Model model) {
		
		BoardVo boardVo = boardService.getContents(postNo);
		model.addAttribute("boardVo", boardVo);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value="/modify/{postNo}", method=RequestMethod.GET)
	public String modify(
			@AuthUser UserVo authUser,
			@PathVariable("postNo") Long postNo,
			Model model) {
		BoardVo boardVo = boardService.findPost(postNo);
		boardService.updateTitleAndContents(boardVo);
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(
			@AuthUser UserVo authUser,
			@RequestParam("postNo") Long postNo,
			@RequestParam("page") Long page,
			BoardVo boardVo
			) {
		boardVo = boardService.findPost(postNo, boardVo);
		boardService.updateTitleAndContents(boardVo);
		return "redirect:/board";
	}
	
	// 글쓰기 페이지(답글 x)
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@AuthUser UserVo authUser) {
		return "board/write";
	}
	
	// 글쓰기 페이지(답글 o)
	@Auth
	@RequestMapping(value = "/reply/{postNo}", method = RequestMethod.GET)
	public String write(@AuthUser UserVo authUser, 
						@PathVariable("postNo") Long postNo,
						BoardVo boardVo,
						Model model) {
		model.addAttribute("boardVo", boardVo);
		return "board/reply";
	}
	
	// 글쓰기
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, 
						BoardVo boardVo) {
		boardVo.setUserNo(authUser.getNo());
		boardService.addPost(boardVo);
		return "redirect:/board";
	}
	
	// 답글
	@Auth
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser,
						@RequestParam("page") Long page,
						@RequestParam("groupNo") Long groupNo,
						@RequestParam("orderNo") Long orderNo,
						@RequestParam("depth") Long depth,
						BoardVo boardVo,
						Model model) {
		
		boardVo.setUserNo(authUser.getNo());
		boardService.addReplyPost(boardVo);
		return "redirect:/board" + "?page=" + page;
	}
	
	
	
//	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
//	public String delete(@PathVariable("no") Long no, Model model) {
//		model.addAttribute("no", no);
//		return "guestbook/delete";
//	}
//
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public String delete(GuestbookVo vo) {
//		guestbookService.delete(vo);
//		return "redirect:/guestbook";
//	}
//

}
