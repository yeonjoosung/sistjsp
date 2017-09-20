package com.newlecture.javaweb.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.web.entity.Notice;

@WebServlet("/customer/notice-edit")
public class NoticeEditController extends HttpServlet {

	/* doGet , doPost ,service */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		NoticeDao noticeDao = new JdbcNoticeDao();
		noticeDao.update(id,title,content);
		
		response.sendRedirect("notice-detail?id=" + id);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String _id = request.getParameter("id");
		String id = "";
		Notice n = null;
		if (_id != null && !_id.equals(""))
			id = _id;

		/* List<Notice> list = null; */
		String sql = "SELECT *FROM Notice where id=?";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);

			ResultSet rs = st.executeQuery();

			/* list = new ArrayList<>(); */
			while (rs.next()) {
				n = new Notice();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setHit(rs.getInt("HIT"));
				n.setContent(rs.getString("CONTENT"));
				n.setWriterId(rs.getString("WRITERID"));
				n.setRegDate(rs.getDate("REGDATE"));
			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
		}
		request.setAttribute("notice", n);
		// request.setAttribute("list", list); response.sendRedirect("notice.jsp");
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/edit.jsp").forward(request, response);

	}
}
