package com.newlecture.javaweb.controller.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Notice;

@WebServlet("/member/join")
public class JoinController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id= request.getParameter("id");
		String[] pwds= request.getParameterValues("pwd");
		String isLunar= request.getParameter("is-Lunar");
		String name= request.getParameter("name");
		String gender= request.getParameter("gender");
		String birthday= request.getParameter("birthday");
		String phone= request.getParameter("phone");
		String email= request.getParameter("email");
		
		
		
		
		/*System.out.println("id :"+ id);
		System.out.println("pwd :"+ pwds[0]);
		System.out.println("isLunar :"+ isLunar);*/
		
		MemberDao memberDao = new JdbcMemberDao();
		
		Member member = new Member(id,pwds[0],name,gender,birthday,phone,email);
		int result = memberDao.insert(member);
		
		if(result>0)
			response.sendRedirect("confirm");
		else
			response.sendRedirect("../error?code=1234");
	}
}
