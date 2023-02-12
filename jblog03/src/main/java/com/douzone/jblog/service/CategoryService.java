package com.douzone.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	public void createCategory(UserVo userVo) {
		categoryRepository.createCategory(userVo);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return categoryRepository.getCategoryList(id);
	}

	public void insertCategory(CategoryVo categoryVo) {
		categoryRepository.insertCategory(categoryVo);
	}

	public CategoryVo getCategory(String categoryNo) {
		return categoryRepository.getCategory(categoryNo);
	}

	public void deleteCategory(CategoryVo categoryVo) {
		categoryRepository.deleteCategory(categoryVo);
	}

	public boolean isCategory(String id, String category) {
		Map<String, Object> map = Map.of("id", id, "category", category);
		return categoryRepository.isCategory(map);
	}

}
