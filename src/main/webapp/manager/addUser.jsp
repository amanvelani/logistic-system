<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="/css/showUser_bootstrap.css" rel="stylesheet" type="text/css" />
<link href="/css/showUser.css" rel="stylesheet" type="text/css" />
<link href="/css/pop-up.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Manage Users</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

 <script> 
     $(function(){
      $("#sideNav").load("/Nav/sideNav.html"); 
    });
    $(function(){
      $("#top").load("/Nav/topbar.html"); 
    });
    </script> 
</head>
<body style="background-color: white;">
<div class="wrapper">
<div id="sideNav"></div>
<div id="content">
<div id="top"></div>
    <div  id="myForm">
        <div class="card" >
                <form action="/auth/signup" method="post" id="reg_form">
                <h1>Add Users</h1>
                <br>
                <div class="form-data">
                    <!-- <div class="forms-inputs mb-4"> <span>ID</span> <input autocomplete="off" type="text"  name="user_id"></div> -->
                    <div class="forms-inputs mb-4"> <span>Username</span> <input autocomplete="off" type="text" name="username" id="username"></div>
                    <div class="forms-inputs mb-4"> <span>Password</span> <input autocomplete="off" type="password" name="password" id="password"></div>
                    <div class="forms-inputs mb-4"> <span>Role</span> 
                        <select name="roles" id="roles" >
                        <option value="">Select Role</option>
                        <option value="">User</option>
                        <option value="mod">Manager</option>
                        </select>
                    </div>

                    <div class="forms-inputs mb-4" style="margin-top:10px"> <span>Email</span> <input autocomplete="off" type="text" name="email" id="email"></div>
                    
                    <input type="hidden" name="auth" id="auth" value="manager">

                    <div class="mb-3"> <input class="btn btn-dark w-100" type="submit" value="submit" form="reg_form"></div>
                    <%-- <div class="mb-3"> <input class="btn btn-dark w-100" onclick="closeForm();" value="Close"> </div> --%>
               </div>
            </form>
        </div>
    </div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


</body>
</html>