<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.food.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    FoodService foodSvc = new FoodService();
    List<FoodVO> list = foodSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ�������� - listAllFood.jsp</title>

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
	width: 800px;
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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ�������� - listAllFood.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="${pageContext.request.contextPath}/front-end/images/food.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�����s��</th>
		<th>��������</th>
		<th>�W��</th>
		<th>�d����</th>
<!-- 		<th>�~��</th> -->
<!-- 		<th>����</th> -->
<!-- 		<th>����</th> -->
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="foodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${foodVO.foodNumber}</td>
			<td>${foodVO.foodTypeNumber}</td>
			<td>${foodVO.foodName}</td>
			<td>${foodVO.foodCalories}</td>
<%-- 			<td>${foodVO.foodCalories}</td> --%>
<%-- 			<td>${foodVO.foodCalories}</td>  --%>
<%-- 			<td>${foodVO.foodCalories}</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/food/food.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="foodNumber"  value="${foodVO.foodNumber}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/food/food.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="foodNumber"  value="${foodVO.foodNumber}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>