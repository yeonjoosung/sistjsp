package com.newlecture.javaweb.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/customer/notice-reg")
public class NoticeRegController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setAttribute("list", list); response.sendRedirect("notice.jsp");
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/reg.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/upload";
		
		ServletContext context = request.getServletContext();
		path = context.getRealPath(path);
		System.out.println("path"+path);
		
		MultipartRequest req = new MultipartRequest(request, path, 1024*1024*100, "UTF-8", new DefaultFileRenamePolicy());
		
		
		request.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		System.out.println(title+"??");
		
		
		String content = req.getParameter("content");
		String fileName = req.getFilesystemName("file");//파일명을 가져옴
		//String fileName = (String) req.getFileNames().nextElement();
		
		System.out.println("fileName:"+fileName);
		
		String sql = "INSERT INTO Notice (id, title, content, writerId,fileName) VALUES((select IFNULL(MAX(CAST(ID AS UNSIGNED)), 0)+1 FROM Notice n),?,?,?,?)";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "newlec");
			st.setString(4, fileName);
			

			int result = st.executeUpdate();

			st.close();
			con.close();

		} catch (Exception e) {
		}
		response.sendRedirect("notice-list");

	}
	
	
}
