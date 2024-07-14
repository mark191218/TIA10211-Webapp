package com;

//package servlet_examples;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.food.model.*;

@WebServlet("/helloworldtest")
public class HelloWorld extends HttpServlet {

	int count = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			                         throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			                          throws ServletException, IOException {

		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();

		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hello World</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("<BIG>Hello World , 世界你好!</BIG>="+(++count));
		out.println("</BODY></HTML>");
		
		FoodVO foodVO = new FoodVO();
		FoodJDBCDAO dao = new FoodJDBCDAO();
		foodVO = dao.getOne(1);
		out.println("<br>" + foodVO.getFoodNumber());
		out.println("<br>" + foodVO.getFoodTypeNumber());
		out.println("<br>" + foodVO.getFoodName());
		out.println("<br>" + foodVO.getFoodCalories());
		
//		req.setAttribute("foodVO", foodVO); // 資料庫取出的empVO物件,存入req
//		String url = "/emp/listOneEmp.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//		successView.forward(req, res);
		
		
	}
}