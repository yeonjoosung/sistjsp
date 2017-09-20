package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public class JdbcNoticeDao implements NoticeDao{

	public List<NoticeView> getList(int page, String query) {
		
		int offset =(page-1)*10;
		List<NoticeView> list = null;
		String sql = "SELECT *FROM NoticeView where title like ? order by regDate desc limit ?, 10";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, offset);
			ResultSet rs = st.executeQuery();
			list = new ArrayList<>();
			
			
			while (rs.next()) {
				NoticeView n = new NoticeView();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setHit(rs.getInt("HIT"));
				n.setContent(rs.getString("CONTENT"));
				n.setWriterId(rs.getString("WRITERID"));
				n.setRegDate(rs.getDate("REGDATE"));
				n.setWriterName(rs.getString("WRITERNAME"));
				n.setCountCmt(rs.getInt("COUNTCMT"));
				n.setFileName(rs.getString("FILENAME"));

				list.add(n);
			}
			rs.close();
			st.close();
			
			con.close();

		} catch (Exception e) {
		}
		
		
		return list;
	}

	public int getCount() {
		String sqlCount = "SELECT count(id) count FROM Notice";
		int count =0;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");
			
			
			
			Statement stCount = con.createStatement();
			ResultSet rs2 = stCount.executeQuery(sqlCount);
			
			rs2.next();
			count = rs2.getInt("count");

			rs2.close();
			stCount.close();
			con.close();

		} catch (Exception e) {
		}
		return count;
	}

	@Override
	public NoticeView get(String id) {
		
//		String id = "";
//		Notice n = null;
//		if (_id != null && !_id.equals(""))
//			id = _id;
//
//		List<Notice> list = null;
		String sql = "SELECT *FROM Notice where id=?";
		NoticeView n=null;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
				
			/*list = new ArrayList<>();*/
			while (rs.next()) {
				n= new NoticeView();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setHit(rs.getInt("HIT"));
				n.setContent(rs.getString("CONTENT"));
				n.setWriterId(rs.getString("WRITERID"));
				n.setRegDate(rs.getDate("REGDATE"));
				n.setFileName(rs.getString("FILENAME"));
				
				/*list.add(n);*/
			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
		}
		return n;
	}

	@Override
	public int update(String id, String title, String content) {
		
		int result=0;
		String sql = "UPDATE Notice SET title=?, content=? where id=?";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, id);

			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (Exception e) {
		}
		return result;
	}

}
