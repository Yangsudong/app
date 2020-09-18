<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberAll</title>
</head>
<body>
	<h3 class="page_title">수동왕자님의 페이지조회!</h3>
	<div>
	<ul class="search">
		<li>메일수신여부</li>
		<li>성별</li>
		<li><button type="button">검색</button></li>
		
	</ul>
	</div>
	<table border="1" id="members">
		<thead>
			<tr>
				<th width="100" height="50">아이디</th>
				<th width="100">패스워드</th>
				<th width="50">성별</th>
				<th width="200">직업</th>
				<th width="150">메일수신여부</th>
				<th width="150">가입동기</th>
				<th width="200">취미</th>
				<th width="150">가입날짜</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${list}" var="member">		
			<tr height="30">
				<td><a href="memberSelect.jsp">${member.id}</a></td>
				<td>${member.pass}</td>
				<td>${member.gender}</td>  
				<td>${member.job}</td>  
				<td>
					<c:if test="${member.mailYN == 'y'}"> 
						<button type="button">공주빔 발사 </button>
					</c:if>					
				</td>  
				<td>${member.reason}</td>
				<td>${member.hobby}</td> 
				<td>
				<fmt:parseDate value="${member.regdate}" pattern="yyyy-MM-dd" var="day" />
				<fmt:formatDate value="${day}" pattern="MM/dd"/>					
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>