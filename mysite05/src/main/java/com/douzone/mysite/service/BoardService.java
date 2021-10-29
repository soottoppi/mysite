package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;

	public Long findPageCount() {
		return boardRepository.findPageCount();
	}

	public Object findAll(Long pageNoForRow) {
		return boardRepository.findAll(pageNoForRow);
	}

	public BoardVo findPost(Long postNo) {
		return boardRepository.findPost(postNo);
	}
	
	public BoardVo findPost(Long postNo, BoardVo boardVo) {
		BoardVo vo = boardRepository.findPost(postNo);
		vo.setTitle(boardVo.getTitle());
		vo.setContents(boardVo.getContents());
		return vo;
	}

	public Long findMaxGroupNo() {
		return boardRepository.findMaxGroupNo();
	}

	public void updateTitleAndContents(BoardVo boardVo) {
		boardRepository.updateTitleAndContents(boardVo);
	}

	public BoardVo getContents(Long postNo) {
		BoardVo boardVo = boardRepository.findPost(postNo);
		
		if(boardVo != null) {
			boardRepository.updateHit(boardVo);
		}

		return boardVo;
	}

	public boolean addPost(BoardVo boardVo) {
		// 답글인 경우
		if(boardVo.getGroupNo() != null) {
			boardVo.setOrderNo(boardVo.getOrderNo() + 1L);
			boardVo.setDepth(boardVo.getDepth() + 1L);
			boardRepository.updateOrderNo(boardVo);
			
		} else {
			boardVo.setGroupNo(findMaxGroupNo() + 1L);
			boardVo.setOrderNo(1L);
			boardVo.setDepth(0L);
		}
		return boardRepository.insert(boardVo);
	}

	public boolean delete(Long postNo, String password) {
		return boardRepository.delete(postNo, password);
	}
	
}
