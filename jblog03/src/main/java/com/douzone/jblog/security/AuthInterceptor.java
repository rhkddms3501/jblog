package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;


public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			// DefaultServlethandler가 처리하는 경우(정적 자원, /assets/**)
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		if (auth == null) {
			return true;
		}
		
		String blogInfo [] = request.getServletPath().split("/");

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (!authUser.getId().equals(blogInfo[1])) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		return true;
	}
}
























