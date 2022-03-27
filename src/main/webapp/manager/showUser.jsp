<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Bootstrap</title>
<link rel=" stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
<link href="/css/showUser_bootstrap.css" rel="stylesheet" type="text/css" />
<link href="/css/showUser.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/datetime/1.1.2/css/dataTables.dateTime.min.css">

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
<div id="sideNav"></div><div id="content">
    <div id="top"></div>
    <h1>Users</h1>

<div class="container mb-3 mt-3 table-responsive">
<table class="table table-striped table-bordered mydatatable" style="width: 100%">
  <thead style="color:white">
    <tr>
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

    <form action="/manager/update-complete" method="post">
	<% 
	if(b==0){
		String id = s1[b].substring(1);
	%>
    <td><input type="hidden" name="id" value="<%=id%>">
    <%
      out.print(id);
	}
	else if(b==1){
        String email = s1[b];
        %></td><td><input type="text" name="email" value="<%=email%>"></td><%
    }
    else if(b==2){
        String username = s1[b];
        %><td><input type="text" name="username" value="<%=username%>"></td><%
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
        %><td><%out.print(role_string);%></td><%
    }
	else{
	%>
	<td>
	<input type = "submit" value="Update">
	<input type = "submit" value="Delete" formaction="/admin/delete-role">
	</td>
    </form>
    <%
	}
	%>
    
    <%
	}
	%>
</tr>
<%
}
%>
</tbody>
</table>
</div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script sre="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script>
$('.mydatatable'). DataTable();
</script>
</body>
</html>