package com.newlecture.javaweb.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberDao;


@WebServlet("/member/home")
public class HomeController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
//		if(session.getAttribute("id")==null)
		Object _memberId = session.getAttribute("id");
		if(_memberId == null)
			response.sendRedirect("login?returnURL=home");
		else {
			String memberId = _memberId.toString();
			MemberRoleDao memberRoleDao =new JdbcMemberDao();
			String defaultRole =memberRoleDao.getDefaultRole(memberId);
			
			System.out.println(defaultRole);
			if(defaultRole.equals("ROLE_ADMIN"))
				response.sendRedirect("../admin/index");
			else if(defaultRole.equals("ROLE_STUDENT"))
				response.sendRedirect("../student/index");
			else
				response.sendRedirect("../index");
		}
//			out.println("<script>alert('로그인이 필요한 페이지입니다.');location.href='login';</script>");
	}
		
	
	
	
	
	
}
