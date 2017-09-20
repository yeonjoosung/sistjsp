<%@page import="java.util.Iterator"%>
<%@page import="com.newlecture.web.entity.Notice"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%-- <%!int add(int x, int y) {
		return x + y;
	}%>
<!-- 멤버함수 정의:!붙여서  -->
<%
	int x = 3;
	int y = 4;
%> --%>

<%
	//notice?title=aa :title="a"
	//notice?title= : title=""
	//notice : title=null

	String _title = request.getParameter("title");
	String title = "";
	
	application.setAttribute("x", "어플");
	session.setAttribute("x", "세");
	request.setAttribute("x", "리");
 	pageContext.setAttribute("x", "페");
 	/* 우선순위가 제일 작은 범위부터 출력 */
	
	
	if (_title != null && !_title.equals(""))
		title = _title;

	List<Notice> list = null;
	String sql = "SELECT *FROM Notice where title like ?";

	String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	try {
		Class.forName("com.mysql.jdbc.Driver");

		Connection con = DriverManager.getConnection(url, "sist", "cclass");

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + title + "%");

		ResultSet rs = st.executeQuery();

		list = new ArrayList<>();

		while (rs.next()) {
			Notice n = new Notice();
			n.setId(rs.getString("ID"));
			n.setTitle(rs.getString("TITLE"));

			list.add(n);
		}
		rs.close();
		st.close();
		con.close();

	} catch (Exception e) {
	}
pageContext.setAttribute("list",list);
%>


<body>
	<form action="notice" method="get">
		<!--  method=>get or post -->
		<label>검색어</label><input type="text" name="title" /> <input
			type="submit" />
	</form>
	<%--
<div>
		 result :
		<%
		out.println(3 + 4);
	%><%=3 + 4%><%=x + y%><%=add(4, 5)%>

	</div> --%>


	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%-- <%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<th><%=list.get(i).getId()%></th>
			<th><%=list.get(i).getTitle()%></th>
			<th><%=list.get(i).get()%></th>
			<th><%=list.get(i).getRegDate()%></th>
			<th><%=list.get(i).getHit()%></th>
		</tr>
		<%
			}
		%> --%>
		<%-- <c:forEach var="i" begin="0" end="3" step="2"><!--begin부터 end 까지 포함  --> --%>
		<c:forEach var="n" items="${list}" ><!--items전체 ,begin과 end,step도 사용가능  -->
		
		<tr>	<!-- step=>2씩증가 -->
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