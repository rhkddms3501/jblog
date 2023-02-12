package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	SqlSession sqlSession;

	public List<PostVo> getPostList(Map<String, Object> map) {
		return sqlSession.selectList("post.getPostList", map);
	}

	public PostVo getPost(Map<String, Object> map) {
		return sqlSession.selectOne("post.getPost", map);
	}

	public void writePost(Map<String, Object> map) {
		sqlSession.insert("post.writePost", map);
	}

	public boolean isPost(Map<String, Object> map) {
		return sqlSession.selectOne("post.isPost", map);
	}

}
