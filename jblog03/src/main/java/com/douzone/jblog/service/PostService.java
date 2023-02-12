package com.douzone.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public List<PostVo> getPostList(String id, String categoryNo) {
		Map<String, Object> map = Map.of("id", id, "categoryNo", categoryNo);
		return postRepository.getPostList(map);
	}

	public PostVo getPost(String id, String categoryNo, String postNo) {
		Map<String, Object> map = Map.of("id", id, "categoryNo", categoryNo,  "postNo", postNo);
		return postRepository.getPost(map);
	}

	public void writePost(Map<String, Object> map) {
		postRepository.writePost(map);
	}

	public boolean isPost(String id, String category, String post) {
		Map<String, Object> map = Map.of("id", id, "category", category, "post", post);
		return postRepository.isPost(map);
	}

}
