<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.lang.*"%>
<%@ page import ="java.io.*"%>
<%@ page import ="java.io.File.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import ="java.util.Arrays"%>
<%@ page import ="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link href="/css/manage.css" rel="stylesheet" type="text/css" />
<meta charset="ISO-8859-1">
<title>Manage Users</title>
</head>
<body>
<h1><span class="blue">&lt;</span>Users<span class="blue">&gt;</span> <span class="yellow">Information</span></h1>
<table border="1" class="container">
<thead>
<tr>
<td>ID </td>
<td>Username</td>
<td>Password</td>
<td>Role</td>
<td>Enabled</td>
<td> Edit</td>
</tr>
</thead>
<tbody>

<%
String[] jsp = (String[])request.getAttribute("data");
for(int i=0; i<jsp.length;i++){
	%><tr>
	<%
	String s = null;
	
	//out.print(jsp[i]);
	s = jsp[i];
	String s1[] = new String[10];
	s1 = s.split(",");
	int b = 0;
	for( b= 0; b<s1.length;b++){%>
	<td>
	<% 
	if(b==0){
		String user_id = s1[b].substring(1);
		out.print(user_id);
	}else if(b==4){
		String enab = s1[b].substring(0, s1[b].length() - 1);
		out.print(enab);
	}else if(b==2){
		int a = s1[b].length();
		for(int c=0;c<a;c++){
			out.print("*");
		}
	}
	else{
	out.println(s1[b]);
	}
	
	%></td><%
	}
	String id = s1[0].substring(1);
	%>
<td>
<a href="update.jsp?id=<%=id%>" style="color: white">Update</a>
</td>
</tr>
<%
}
%>
</tbody>
</table>
<div style="margin-top:20px">
<a href="/logout" class="yellow my-3" style="justify-content:center; text-align:center; margin-left:50%;%">Logout</a>
</div>
</body>
</html>