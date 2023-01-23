<%@page import="dto.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Lists of all books</h1>
<table border="2px solid black">
<tr>
<th>book_id</th>
<th>book_name</th>
<th>author_name</th>
<th>pages</th>
<th>price</th>
<th>delete</th>
<th>update</th>
</tr>
<% ArrayList <Book> books=(ArrayList)request.getAttribute("data");
for(Book bc:books){
%>
<tr>
<td><%=bc.getBook_id() %></td>
<td><%=bc.getBook_name() %></td>
<td><%=bc.getAuthor_name() %></td>
<td><%=bc.getPrice() %></td>
<td><%=bc.getnoof_Pages()%></td>
<td><a href="delete?id=<%= bc.getBook_id() %>">delete</a></td>
<td><a href="update1?id=<%= bc.getBook_id() %>">edit</a></td>
</tr>
<%} %>
</table>
<a href="welcome.html">home</a>
</body>
</html>