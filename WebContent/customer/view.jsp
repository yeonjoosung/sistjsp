<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	Object _s = session.getAttribute("st");
	Object _a = application.getAttribute("st");	
	
	String s= "";
	String a= "";
	String c= "";
	
	
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("st")) {
			c = cookie.getValue();
			break;
		}
	}
	if(_s != null)
		s = _s.toString();// .toString() 때문에 null point exception 발생할수있음 
	if(_a != null)
		a = a.toString();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript"></script>
	<div>
		<ul>
			<li>session: <%=s%></li>
			<li>cookie: <%=c%></li>
			<li>application: <%=a%></li>

		</ul>
	</div>
</body>
</html>