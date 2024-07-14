package com.food.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class FoodJDBCDAO implements FoodDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DBTIA102G3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TIA102G3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

//	private static final String INSERT_STMT = "INSERT INTO food (foodNumber, foodTypeNumber, foodName, foodCalories) values (?, ?, ?, ?);";
	private static final String INSERT_STMT = "INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (?, ?, ?);";

	private static final String GET_ALL_STMT = "SELECT * FROM food ORDER BY foodNumber";

	private static final String GET_ONE_STMT = "SELECT foodNumber, foodTypeNumber, foodName, foodCalories FROM food WHERE foodNumber = ?";

	private static final String UPDATE = 
			"UPDATE food SET foodTypeNumber = ?, foodName = ?, foodCalories = ? WHERE foodNumber = ?";
	
	private static final String DELETE = "DELETE FROM food WHERE foodNumber = ?";
	
	@Override
	public void insert(FoodVO foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, foodVO.getFoodNumber());
			pstmt.setInt(1, foodVO.getFoodTypeNumber());
			pstmt.setString(2, foodVO.getFoodName());
			pstmt.setInt(3, foodVO.getFoodCalories());

			pstmt.executeUpdate();

		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//
//		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
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

	}

	@Override
	public void update(FoodVO foodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, foodVO.getFoodTypeNumber());
			pstmt.setString(2, foodVO.getFoodName());
			pstmt.setInt(3, foodVO.getFoodCalories());
			pstmt.setInt(4, foodVO.getFoodNumber());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
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

	}
	
	@Override
	public FoodVO getOne(Integer foodNumber) {
		
		FoodVO foodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, foodNumber);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFoodNumber(rs.getInt("foodNumber"));
				foodVO.setFoodTypeNumber(rs.getInt("foodTypeNumber"));
				foodVO.setFoodName(rs.getString("foodName"));
				foodVO.setFoodCalories(rs.getInt("foodCalories"));

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

		return foodVO;
	}
	
	@Override
	public void delete(Integer foodNumber) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, foodNumber);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}
	
	
	@Override
	public List<FoodVO> getAll() {
		List<FoodVO> list = new ArrayList<FoodVO>();
		FoodVO foodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				foodVO = new FoodVO();
				foodVO.setFoodNumber(rs.getInt("foodNumber"));
				foodVO.setFoodTypeNumber(rs.getInt("foodTypeNumber"));
				foodVO.setFoodName(rs.getString("foodName"));
				foodVO.setFoodCalories(rs.getInt("foodCalories"));
				list.add(foodVO);

			}

		} 
//		catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//
//		} 
		catch (SQLException se) {
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
		FoodJDBCDAO dao = new FoodJDBCDAO();
		
		// INSERT
//		FoodVO foodVO1 = new FoodVO();
//		foodVO1.setFoodNumber(4);
//		foodVO1.setFoodTypeNumber(3);
//		foodVO1.setFoodName("蘿蔔");
//		foodVO1.setFoodCalories(99);
//		dao.insert(foodVO1);
		
		// INSERT
//		FoodVO foodVO2 = new FoodVO();
//		foodVO2.setFoodNumber(4);
//		foodVO2.setFoodTypeNumber(4);
//		foodVO2.setFoodName("空心菜");
//		foodVO2.setFoodCalories(44);
//		dao.update(foodVO2);
		
		// GetOne
		FoodVO foodVO3 = dao.getOne(2);
		System.out.print(foodVO3.getFoodNumber() + ",");
		System.out.print(foodVO3.getFoodTypeNumber() + ",");
		System.out.print(foodVO3.getFoodName() + ",");
		System.out.println(foodVO3.getFoodCalories() + ",");
		System.out.println("=================================");
		
		// Query All
//		List<FoodVO> list = dao.getAll();
//		for (FoodVO aFood : list) {
//			System.out.print(aFood.getFoodNumber() + ",");
//			System.out.print(aFood.getFoodTypeNumber() + ",");
//			System.out.print(aFood.getFoodName() + ",");
//			System.out.print(aFood.getFoodCalories() + ",");
//			System.out.println();
//
//		}

	}

}
