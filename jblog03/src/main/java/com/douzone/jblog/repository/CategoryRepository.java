package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryRepository {

	@Autowired
	SqlSession sqlSession;
	
	public void createCategory(UserVo UserVo) {
		sqlSession.insert("category.createCategory", UserVo);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return sqlSession.selectList("category.getCategoryList", id);
	}

	public void insertCategory(CategoryVo categoryVo) {
		sqlSession.insert("category.insertCategory", categoryVo);
	}

	public CategoryVo getCategory(String categoryNo) {
		return sqlSession.selectOne("category.getCategory", categoryNo);
	}

	public void deleteCategory(CategoryVo categoryVo) {
		sqlSession.delete("category.deleteCategory", categoryVo);
	}

	public boolean isCategory(Map<String, Object> map) {
		return sqlSession.selectOne("category.isCategory", map);
	}

}
