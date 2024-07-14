<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Food page: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Food page: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Food: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFood.jsp'>List</a> all Foods.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/back-end/food/food.do" >
        <b>��J�����s�� (�p1�B2):</b>
        <input type="text" name="foodNumber">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="foodSvc" scope="page" class="com.food.model.FoodService" />
   
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/back-end/food/food.do" >
       <b>��ܭ����s��:</b>
       <select size="1" name="foodNumber">
         <c:forEach var="foodVO" items="${foodSvc.all}" > 
          <option value="${foodVO.foodNumber}">${foodVO.foodNumber}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/back-end/food/food.do" >
       <b>��ܭ����W��:</b>
       <select size="1" name="foodNumber">
         <c:forEach var="foodVO" items="${foodSvc.all}" > 
          <option value="${foodVO.foodNumber}">${foodVO.foodName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�����޲z</h3>

<ul>
  <li><a href='addFood.jsp'>Add</a> a new Emp.</li>
</ul>

<FORM action="${pageContext.request.contextPath}/back-end/uploadServlet3_simple.do" method=post enctype="multipart/form-data">
        <input type="file" name="upfile1">
        <input type="submit" value="�W��">
</FORM>


</body>
</html>