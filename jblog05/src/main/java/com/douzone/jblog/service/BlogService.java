package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	BlogRepository blogRepository;
	
	public void createBlog(UserVo vo) {
		blogRepository.createBlog(vo);
	}

	public BlogVo getBlog(String id) {
		return blogRepository.getBlog(id);
	}

	public void updateBlog(BlogVo vo) {
		blogRepository.updateBlog(vo);
	}

}
