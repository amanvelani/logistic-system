<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Vehicle Approval Requests</title>
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
<div id="sideNav"></div>
<div id="content">
<div id="top"></div>
<div class="mb-3 mt-3 table-responsive">
<h1>Vehicle Approval Requests</h1>

<table class="table table-striped table-bordered mydatatable" style="width: 100%">
  <thead >
  <%
  String meta = (String)request.getAttribute("metaData");
  String s2[] = new String[10];
  s2 = meta.split(",");
  %>
    <tr>
    <%
      for(int x= 0; x<s2.length;x++){
      %>

        <th><%out.print(s2[x]);%></th>
       <%}%>
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
    else if(b==5){
        String user_id = s1[0].substring(1);
        %>
        <td>
        <a href="/manager/approve-vehicle?id=<%=user_id%>"><button type="button"><i class="fa fa-check" aria-hidden="true"></i></button></a>	    
        <a href="/manager/delete-vehicle?id=<%=user_id%>"><button type="button"><i class="fa fa-ban" aria-hidden="true"></i></button></a>	   
	    </td>
    <%
    }
    else if(b==s1.length){
        String id = s1[0].substring(1);
        %><td><a href="/manager/showVehicleImage?id=<%=id%>">
    View Image
    </a></td><%
    }
     else if(b==s1.length-1){
        String date = s1[b].substring(0, s1[b].length()-1);
        %>
        <td><%
      out.print(date);
    %></td>

    <%
    }
    
	else{
	%>
    <td>
<% out.print(s1[b]);%>
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
</div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.2/js/dataTables.buttons.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.print.min.js"></script>

<script>
$(document).ready(function() {
    $('.mydatatable').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );
</script>
</body>
</html>