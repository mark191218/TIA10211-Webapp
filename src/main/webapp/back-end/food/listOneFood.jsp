<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.food.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  FoodVO foodVO = (FoodVO) request.getAttribute("foodVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>食材資料 - listOneFood.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>食材資料 - listOneFoof.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="${pageContext.request.contextPath}/front-end/images/food.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>食材編號</th>
		<th>食材類型編號</th>
		<th>食材名稱</th>
		<th>食材熱量</th>
	</tr>
	<tr>
		<td><%=foodVO.getFoodNumber()%></td>
		<td><%=foodVO.getFoodTypeNumber()%></td>
		<td><%=foodVO.getFoodName()%></td>
		<td><%=foodVO.getFoodCalories()%></td>
	</tr>
</table>

</body>
</html>