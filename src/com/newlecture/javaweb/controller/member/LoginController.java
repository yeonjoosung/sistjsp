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
import com.sun.java.swing.plaf.windows.resources.windows;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String id =request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDao memberDao = new JdbcMemberDao();
		
		Member member = memberDao.get(id);
		
		if(member == null)
			response.sendRedirect("login?error1");
		else if(!member.getPwd().equals(pwd))
			response.sendRedirect("login?error2");
		else {
			
			request.getSession().setAttribute("id", id);
			
			String returnURL = request.getParameter("returnURL");

			if(returnURL !=null && returnURL !="") {
				response.sendRedirect(returnURL);
			}
			else
				response.sendRedirect("../index");
		}
		
		
	}
}
