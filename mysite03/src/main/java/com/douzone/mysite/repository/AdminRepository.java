package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.AdminVo;

@Repository
public class AdminRepository {
	@Autowired
	SqlSession sqlSession;
	
	public boolean update(AdminVo adminVo) {
		return sqlSession.update("admin.update", adminVo) == 1;
	}

	public AdminVo getMainPage(AdminVo adminVo) {
		return sqlSession.selectOne("admin.getMainPage",adminVo);
	}
	
}
