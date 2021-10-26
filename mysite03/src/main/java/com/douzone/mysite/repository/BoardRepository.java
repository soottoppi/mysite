package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert",vo);
		return count == 1;
	}

	public List<BoardVo> findAll(Long pageNoForRow) {
		return sqlSession.selectList("board.findAll", pageNoForRow);
	}
	

	public Long findMaxGroupNo() {
		return sqlSession.selectOne("board.findMaxGroupNo");
	}

	public BoardVo findPost(Long postNo) {
		return sqlSession.selectOne("board.findPost", postNo);
	}
	
	public Long findPageCount() {
		return sqlSession.selectOne("board.findPageCount");
	}

	public boolean updateHit(BoardVo boardVo) {
		return sqlSession.update("board.updateHit", boardVo) == 1;
	}

	public boolean updateTitleAndContents(BoardVo boardVo) {
		return sqlSession.update("board.updateTitleAndContents", boardVo) == 1;
	}

	public boolean updateOrderNo(BoardVo boardVo) {
		return sqlSession.update("board.updateOrderNo", boardVo) == 1;
	}

	public boolean delete(Long postNo, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put( "postNo", postNo);
		map.put( "password", password);
		return sqlSession.delete("board.delete", map) == 1;
	}
	}
