package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.MemberRole;

public class JdbcMemberDao implements MemberDao, MemberRoleDao {

	@Override
	public int insert(String id, String pwd, String name, String phone, String email) {
		return insert(new Member(id, pwd, name, null, null, phone, email));
	}

	@Override
	public int insert(Member member) {

		int result = 0;
		String sql = "INSERT INTO Member(id, pwd, name, gender, birthday, phone, email) VALUES(?,?,?,?,?,?,?)";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, member.getId());
			st.setString(2, member.getPwd());
			st.setString(3, member.getName());
			st.setString(4, member.getGender());
			st.setString(5, member.getBirthday());
			st.setString(6, member.getPhone());
			st.setString(7, member.getEmail());

			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public Member get(String id) {
		String sql = "SELECT *FROM Member where id=?";
		Member m = null;
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();

			/* list = new ArrayList<>(); */
			while (rs.next()) {
				
				m = new Member(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("gender"),
						rs.getString("birthday"), rs.getString("phone"), rs.getString("email"));
			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
		}
		return m;
	}

	@Override
	public String getDefaultRole(String memberId) {
		
		String role =null;
		String sql = "select roleId from MemberRole where memberId= ? and defaultRole =1;";
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, memberId);
			ResultSet rs = st.executeQuery();
				rs.next();
			/*list = new ArrayList<>();*/
			role=rs.getString("roleId");
			
			
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
		}
		return role;
		
		
		
	}

}
