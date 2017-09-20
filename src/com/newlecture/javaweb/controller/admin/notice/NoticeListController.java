package com.newlecture.javaweb.controller.admin.notice;

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

import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.web.entity.Notice;

@WebServlet("/admin/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		
		
		String _title = request.getParameter("title");
		String title = "";
		String _page = request.getParameter("p");
		int count = 0;
		
		
		int page = 1;
		if(_page != null && !_page.equals(""))
			page = Integer.parseInt(_page);
		int offset =(page-1)*10;
		
		if (_title != null && !_title.equals(""))
			title = _title;

		List<Notice> list = null;
		
		JdbcNoticeDao noticeDao = new JdbcNoticeDao();//context.getDao("noticedao");
		
		request.setAttribute("list", noticeDao.getList(page, title));
		request.setAttribute("count", noticeDao.getCount());
		/*
		  request.setAttribute("list", list); response.sendRedirect("notice.jsp");
*/		  request.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(request, response);

	}
}
