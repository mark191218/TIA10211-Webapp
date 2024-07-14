package com.menu.model;

import java.util.*;
import java.sql.*;

public class MenuJDBCDAO implements MenuDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TIA102G3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String GET_ALL_STMT = "SELECT * FROM menu ORDER BY menuNumber;";

	@Override
	public List<MenuVO> getAll(){
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuVO = new MenuVO();
				menuVO.setMenuNumber(rs.getInt("menuNumber"));
				menuVO.setMenuImage(rs.getBytes("menuImage"));
				list.add(menuVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}

		return list;
	}

	
	public static void main(String[] args) {
		MenuJDBCDAO dao = new MenuJDBCDAO();

		// Query
		List<MenuVO> list = dao.getAll();
		for (MenuVO aFood : list) {
			System.out.print(aFood.getMenuNumber() + ",");
			System.out.print(aFood.getMenuImage() + ",");			
			System.out.println();

		}

	}

}
