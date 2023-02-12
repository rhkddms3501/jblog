package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;



@Repository
public class UserRepository {

	@Autowired
	SqlSession sqlSession;
	
	public UserVo selectUser(UserVo vo) {
		return sqlSession.selectOne("user.selectUser", vo);
	}
	
	public void addUser(UserVo vo) {
		sqlSession.insert("user.addUser", vo);
	}

	public UserVo login(UserVo vo) {
		return sqlSession.selectOne("user.login", vo);
	}

	public boolean isUser(String user) {
		return sqlSession.selectOne("user.isUser", user);
	}

	

}
