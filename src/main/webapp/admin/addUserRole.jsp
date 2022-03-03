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
                    <li class="nav-item"><a class="nav-link" href="#" onclick="openForm(); return false;"data-target="#login" data-toggle="modal" >Add Users</a></li>
                    <li class="nav-item"><a class="nav-link" href="#" onclick="openForm(); return false;"data-target="#login" data-toggle="modal" >Add Users</a></li>
                    <li class="nav-item"><a class="nav-link" href="#" onclick="openForm(); return false;"data-target="#login" data-toggle="modal" >Add Users</a></li>
                </ul> --%>
                
            </div>
        </div>
    </nav>
    <br>
    <br>
    <br>
    <br>
    <div id="mainContent" style="margin-left:200px">
    <div  id="myForm">
        <div class="card" >
                <form action="/admin/doAddUserRole" method="post" id="reg_form">
                <h1>Add Additional Roles to User</h1>
                <br>
                <div class="form-data">
                    <div class="forms-inputs mb-4"> <span>Username</span> <input autocomplete="off" type="text" name="username" id="username"></div>
					<div class="forms-inputs mb-4"> <span>Role</span> 
                        <select name="role" id="role" >
                        <option value="">Select Role</option>
                        <option value="1">User</option>
                        <option value="2">Moderator</option>
                        <option value="3">Admin</option>
                        </select>
                    </div>
                    <div class="mb-3"> <input class="btn btn-dark w-100" type="submit" value="submit" form="reg_form"></div>
               </div>
            </form>
        </div>
    </div>
</div>



</body>
</html>