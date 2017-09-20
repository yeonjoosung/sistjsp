<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<form action="notice" method="get">
		<label>검색어</label><input type="text" name="title" /> <input
			type="submit" />
	</form>


	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="n" items="${list}">
			<!--items전체 ,begin과 end,step도 사용가능  -->

			<tr>
				<!-- step=>2씩증가 -->
				<th>${n.id}-${sessionScope.x}</th>
				<th>${n.title}</th>
				<th>${n.id}</th>
				<th>${n.regDate}</th>
				<th>${n.hit}</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>