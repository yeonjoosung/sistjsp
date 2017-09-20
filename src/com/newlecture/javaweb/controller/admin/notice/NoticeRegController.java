package com.newlecture.javaweb.controller.admin.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/admin/notice/reg")
public class NoticeRegController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null)
			out.println("<script>alert('로그인이 필요한 페이지입니다.');location.href='../../member/login?returnURL=../admin/notice/reg';</script>");
		else 
			request.getRequestDispatcher("/WEB-INF/views/admin/notice/reg.jsp").forward(request, response);

		

		// request.setAttribute("list", list); response.sendRedirect("notice.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String title = request.getParameter("title");

		String content = request.getParameter("content");
		String sql = "INSERT INTO Notice (id, title, content, writerId) VALUES((select IFNULL(MAX(CAST(ID AS UNSIGNED)), 0)+1 FROM Notice n),?,?,?)";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "newlec");
			
			int result = st.executeUpdate();

			st.close();
			con.close();

		} catch (Exception e) {
		}
		response.sendRedirect("list");

	}
	
	
}
