<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%
  String message = (String) application.getAttribute("message");
  List<String> errors = (List<String>) application.getAttribute("errors");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if( errors.isEmpty() ) { %>
  <p><%= message %></p>
<% } else {
	for (String error : errors) {
%>
  <p><%= error %></p>
<%
    }
}
%>
<a href="/ems">TOPに戻る</a>
</body>
</html>