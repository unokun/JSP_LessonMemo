<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Division" %>
<%
  Division division = (Division) application.getAttribute("division");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="/ems/EditDivision" method="POST">
    <div>
	    <label id="id">部署ID</label>
	    <input type="hidden" id="id" name="id" value="<%= division.getId() %>">
	    <label><%= division.getId() %></label>(部署IDは編集不可)
    </div>
    <div>
	    <label id="name">部署名</label>
	    <input type="text" id="name" name="name" value="<%= division.getName() %>" >
    </div>
    <div>
	    <button type="submit" name="action" value="EDIT">設定</button>
    </div>
    <!--
    <div>
	    <button type="submit" name="action" value="CANCEL">キャンセル</button>
    </div>
     -->
  </form>
</body>
</html>