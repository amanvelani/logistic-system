<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Bootstrap</title>
<link rel=" stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
<link href="/css/showUser_bootstrap.css" rel="stylesheet" type="text/css" />
<link href="/css/showUser.css" rel="stylesheet" type="text/css" />
<link href="/css/sidebar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

 <script> 
    $(function(){
      $("#sideNav").load("sideNav.html"); 
    });
    </script> 
</head>
<div id="sideNav"></div>
<body style="background-color: white;">
<div class="container mb-3 mt-3" style="margin-left:230px">
<table class="table table-striped table-bordered mydatatable" style="width: 100%">
  <thead style="color:white">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Number</th>
        <th>Activate</th>
        <th>Identification</th>
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
	<% 
	if(b==0){
		String user_id = s1[b].substring(1);
	%>
    <td><input type="hidden" name="id" value="<%=user_id%>">
    <%
      out.print(user_id);
	}
	else if(b==1){
        String name = s1[b];
        %></td><td><% out.print(name);%></td><%
    }
    else if(b==2){
        String number = s1[b];
        %><td><% out.print(number);%></td><%
    }
    else if(b==3){
        String user_id = s1[0].substring(1);
        %>
        <td>
        <a href="/manager/approve-client?id=<%=user_id%>"><button type="button"><i class="fa fa-check" aria-hidden="true"></i></button></a>	    
        <a href="/manager/delete-client?id=<%=user_id%>"><button type="button"><i class="fa fa-ban" aria-hidden="true"></i></button></a>	           
        </td>
    <%
    }
	else{
        String id = s1[0].substring(1);
	%>
    <td>
	<a href="/manager/showClientImage?id=<%=id%>">
    View Image
    </a>
    </td>
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