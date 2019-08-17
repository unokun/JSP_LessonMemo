<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Division" %>
<%@ page import="java.util.List" %>
<%
  List<Division> lists = (List<Division>) application.getAttribute("divisionList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>部署一覧：</h1>
<% if( lists.isEmpty()) { %>
  <p>部署はまだありません。</p>
<% } else { %>
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>部署</th>
      </tr>
    </thead>
    <tbody>
<%
	for (Division division : lists) {
%>

      <tr>
        <td><%= division.getId() %></td>
        <td><%= division.getName() %></td>
        <td>
          <form action="/ems/EditDivision" method="GET">
            <input type="hidden" name="id" value="<%= division.getId() %>" >
	  	    <input type="submit" value="編集">
          </form>
        </td>
        <td>
          <form action="/ems/DeleteDivision" method="POST">
            <input type="hidden" name="id" value="<%= division.getId() %>" >
	  	    <input type="submit" value="削除">
          </form>
        </td>
      </tr>
<%
	}
}
%>
    </tbody>
  </table>
  <form action="/ems/AddDivision" method="GET">
	  <input type="submit" value="新規作成">
  </form>
</body>
</html>