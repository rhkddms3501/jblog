package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.service.UserService;


public class AddressIterceptor implements HandlerInterceptor {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PostService postService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Address Address = handlerMethod.getMethodAnnotation(Address.class);

		if (Address == null) {
			return true;
		}
		
		
		String blogInfo [] = request.getServletPath().split("/");
		
		if(blogInfo.length == 2) {
			boolean isUser = userService.isUser(blogInfo[1]);
			if(!isUser) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}else if (blogInfo.length == 3) {
			boolean isCategory = categoryService.isCategory(blogInfo[1], blogInfo[2]);
			if(!isCategory) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}else if (blogInfo.length >= 4) {
			boolean isPost = postService.isPost(blogInfo[1], blogInfo[2], blogInfo[3]);
			if(!isPost) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
		}
		return true;
	}
}
























