package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void addUser(UserVo vo) {
		userRepository.addUser(vo);
	}

	public UserVo selectUser(UserVo vo) {
		return userRepository.selectUser(vo);
	}

	public boolean isUser(String user) {
		return userRepository.isUser(user);
	}

	public UserVo login(UserVo vo) {
		return userRepository.login(vo);
	}

}
