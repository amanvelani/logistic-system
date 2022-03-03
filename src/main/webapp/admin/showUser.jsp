<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
<link href="/css/showUser_bootstrap.css" rel="stylesheet" type="text/css" />
<link href="/css/showUser.css" rel="stylesheet" type="text/css" />
<link href="/css/pop-up.css" rel="stylesheet" type="text/css" />
<link href="/css/sidebar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Manage Users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

 <script> 
    $(function(){
      $("#sideNav").load("sideNav.html"); 
    });
    </script> 
</head>
<div id="sideNav"></div>
<body style="background-color: #9499a5;">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
        <div class="container px-4">
            <a class="navbar-brand" href="#page-top">Admin Panel</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <%-- <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="login-trigger" href="#" onclick="openForm(); return false;"data-target="#login" data-toggle="modal" >Add Users</a>
                </ul> --%>
            </div>
        </div>
    </nav>
<div id="mainContent" style="margin-left:200px">
<h1><span class="blue">&lt;</span>Users<span class="blue">&gt;</span> <span class="yellow">Information</span></h1>
<table border="1" class="container">
<thead>
<tr style="color: white">
<td>ID </td>
<td>Email</td>
<td>Username</td>
<td>Role</td>
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
	for( b= 0; b<s1.length+1;b++){%>
    

	<td style="color: white">
    <form action="/admin/update-complete" method="post">
	<% 
	if(b==0){
		String user_id = s1[b].substring(1);
	%>
    <input type="hidden" name="id" value="<%=user_id%>">
    <%
      out.print(user_id);
	}
	else if(b==1){
        String email = s1[b];
        %><input type="text" name="email" value="<%=email%>"><%
    }
    else if(b==2){
        String username = s1[b];
        %><input type="text" name="username" value="<%=username%>"><%
    }
    
    
    else if(b==3){
    	String role = s1[b].substring(0, s1[b].length() - 1).trim();
    	String role_string = null;
        if(role.equals("1")){
        	role_string = "User";
        }
        else if(role.equals("2")){
        	role_string = "Moderator";
        }
        else{
        	role_string = "Admin";
        }
        %><select name="roles" id="roles" >
				    <option value="<%=role %>"><%=role_string %></option>
					<option value="3">Admin</option>
					<option value="1">User</option>
					<option value="2">Moderator</option>
		    </select><%
    }
	else{
	%>
	
	<input type = "submit" value="Update">
	<input type = "submit" value="Delete" formaction="/admin/delete-role">
	
    </form>
    <%
	}
	%>
    </td>
    
    
    <%
	}
	String id = s1[0].substring(1);
	%>
</tr>
<%
}
%>
</tbody>
</table>
<script>
    function openForm() {
  document.getElementById("myForm").style.display = "block";
  document.getElementById("mainContent").style.display = "none";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
  document.getElementById("mainContent").style.display = "block";

}
</script>
<script src="/js/sidebar.js"></script>

</body>
</html>