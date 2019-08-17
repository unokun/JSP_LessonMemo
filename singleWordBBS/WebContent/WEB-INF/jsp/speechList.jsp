<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.User" %>
<%@ page import="bean.SingleWord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
  // アプリケーションスコープからひとことリストを取得する
  @SuppressWarnings("unchecked")
  List<SingleWord> lists = (ArrayList<SingleWord>) application.getAttribute("lists");
  // セッションスコープからログインユーザ情報を取得
  User user = (User) session.getAttribute("user");
  // リクエストスコープからエラーメッセージを取得
  String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>第10章　ひとこと掲示板</title>
</head>
<body>
<h2>ひとこと掲示板へようこそ</h2>
<% if( user != null ) { %>
  <p><%= user.getUserName() %>さん、ログイン中</p>
<% } %>
<a href="./SingleWord?action=action.LogoutLogic">ログアウト</a>
<hr>
<a href="./SingleWord?action=action.SpeechListLogic">更新</a>
<form action="./SingleWord" method="post">
  <input type="hidden" name="action" value="action.SpeechListLogic" />
  <label for="comment">ひとこと：</label>
  <input type="text" name="comment" id="comment" /><br />
  <input type="submit" value="投稿" />
</form>
<hr>
<% if( errorMessage != null ) { %>
<p><%= errorMessage %></p>
<% } %>
<% if( lists.size() == 0 ) { %>
  <p>投稿された「ひとこと」はまだありません。</p>
<% } else {
      for(SingleWord words : lists){
%>
        <p><%= words.getUserName() %> : <%= words.getComment() %> : <%= words.getDate() %></p>
<%    }
   } %>
</body>
</html>