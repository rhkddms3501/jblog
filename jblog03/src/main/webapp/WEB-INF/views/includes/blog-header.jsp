<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="header">
	<h1>${blog.title }</h1>
	<ul>
		<c:choose>
			<c:when test="${authUser.id eq blog.id }">
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">블로그 관리</a></li>
				<li>[ ${authUser.id } ]</li>
			</c:when>
			<c:when test="${authUser ne null }">
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li>[ ${authUser.id } ]</li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>