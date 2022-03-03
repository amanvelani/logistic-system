<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<link href="/css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		${abc}
<div class="container">
	<div class="screen">
		<div class="screen__content">
	
			<form class="login" method="post" action="doRegister" modelAttribute="userBean" name="reg_form">
					<sf:errors />
				<div class="login__field">
					<i class="login__icon fas fa-user"></i>
					<input type="text" class="login__input" placeholder="User name / Email" name="username" id="username">
				</div>
				<div class="login__field">
					<i class="login__icon fas fa-lock"></i>
					<input type="password" class="login__input" placeholder="Password" name="password" id="password">
				</div>
				<br>
				<div class="login_feild" tyle="width:200px;">
				<i class="login__icon fas fa-lock"></i>
				<select name="role" id="role" class="login__input">
				<option value="">Select a Role</option>
				<option value="ROLE_ADMIN">ADMIN</option>
				<option value="ROLE_USER">User</option>
				<option value="ROLE_MANAGER">MANAGER</option>
				</select>
				</div>
<!-- 				<div class="login__field">
					<i class="login__icon fas fa-lock"></i>
					<input type="text" class="login__input" placeholder="Role" name="role" id="role">
				</div>
				<div class="login__field">
					<i class="login__icon fas fa-lock"></i>
					<input type="text" class="login__input" placeholder="Enabled" name="enabled" id="enabled">
				</div> -->
				<br>
				<br>
				<div class="login_feild" tyle="width:200px;">
				<i class="login__icon fas fa-lock"></i>
				<select name="enabled" id="enabled" class="login__input">
				<option value="">Enabled</option>
				<option value="1">Yes</option>
				<option value="0">No</option>
				
				</select>
				</div>
				
				
				<button class="button login__submit">
					<span class="button__text">Register User</span>
					<i class="button__icon fas fa-chevron-right"></i>
				</button>			
			
			</form>
				<a href="/logout">
			<button class="button login1__submit">
					<span class="button__text">Logout</span>
					<i class="button1__icon fas fa-chevron-right"></i>
			</button>
			</a>
			
		</div>
		<div class="screen__background">
			<span class="screen__background__shape screen__background__shape4"></span>
			<span class="screen__background__shape screen__background__shape3"></span>		
			<span class="screen__background__shape screen__background__shape2"></span>
			<span class="screen__background__shape screen__background__shape1"></span>
		</div>		
	</div>
</div>


	</body>
	<script>
	function Validation() {
		//var ph = document.reg_form.phone;
		//var em = document.reg_form.email;
		//var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		var ps =  document.reg_form.password;
		//if (ph.value.length > 10) {    
          //  alert("Mobile number is too long");    
            //ph.focus();
            //return false;
      //  }  
	//	else if(!em.value.match(mailformat)){
			//alert("You have entered an invalid email address!");
			//em.focus();
		//	return false;
	//	}
		if(ps.value.length < 6){
			 alert("Password is short");    
	         ps.focus();    
	         return false;    
		}
		else{
			
			return true;
		}
	}
	</script>
	
</html>