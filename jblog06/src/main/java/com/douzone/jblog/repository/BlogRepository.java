package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	@Autowired
	SqlSession sqlSession;

	public void createBlog(UserVo vo) {
		sqlSession.insert("blog.createBlog", vo);
	}

	public BlogVo getBlog(String id) {
		return sqlSession.selectOne("blog.getBlog", id);
	}

	public void updateBlog(BlogVo vo) {
		sqlSession.update("blog.updateBlog", vo);
	}

}
