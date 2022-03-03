<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Bootstrap</title>
<link rel=" stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
</head>
<body>
<div class="container mb-3 mt-3">
<table class="table table-striped table-bordered mydatatable" style="width: 100%">
  <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Username</th>
       <th>Username</th>
        <th>Username</th>
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
    

	
    <form action="/admin/update-complete" method="post">
	<% 
	if(b==0){
		String user_id = s1[b].substring(1);
	%>
    <td><input type="hidden" name="id" value="<%=user_id%>">
    <%
      out.print(user_id);
	}
	else if(b==1){
        String email = s1[b];
        %></td><td><% out.print(email);%></td><%
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
        %><td><select name="roles" id="roles" >
				    <option value="<%=role %>"><%=role_string %></option>
					<option value="3">Admin</option>
					<option value="1">User</option>
					<option value="2">Moderator</option>
		    </select></td><%
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
	String id = s1[0].substring(1);
	%>
</tr>
<%
}
%>
</tbody>
</table>
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