<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Order Goods</title>
<link rel=" stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
<link href="/css/showUser_bootstrap.css" rel="stylesheet" type="text/css" />
<link href="/css/showUser.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/datetime/1.1.2/css/dataTables.dateTime.min.css">
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
<div  id="myForm" style="display:none; margin-left:200px" class="mb-3 mt-3">
        <div class="card mb-3 mt-3" >
                <a class="login-trigger" href="#" onclick="closeForm(); return false;"data-target="#login" data-toggle="modal" >Close</a>

        <form name="fileform" method="post" action="/order/placeOrder" enctype="multipart/form-data">
                <h1>Place order</h1>
                <br>
                <div class="form-data">
                    <!-- <div class="forms-inputs mb-4"> <span>ID</span> <input autocomplete="off" type="text"  name="user_id"></div> -->
                    <div class="forms-inputs mb-4"> <span>Client Id</span> <input type="text" name="client_id" size="50" placeholder="Enter Client Id" required/></div>
                    <div class="forms-inputs mb-4"> <span>Item Id</span> <input type="text" name="item_id" size="50" placeholder="Enter Item Id" required/></div>
                    <div class="forms-inputs mb-4"> <span>Item Quantity</span> <input type="text" name="quantity" size="50" placeholder="Enter Item Quantity" required/></div>
                    <%-- <div class="forms-inputs mb-4"> <span>Item Photo</span> <input type="file" style="margin-top:10px" name="photo" placeholder="Upload Your Image" required/></div> --%>
                    <input class="btn btn-dark w-100" type="submit" value="Place Order">
               </div>
            </form> 
        </div>
    </div>
  <div id="content">
    <div id="top"></div>
<h1>Order Goods</h1>

<div class="mb-3 mt-3 table-responsive" >
<table id="example" class="table table-striped table-bordered mydatatable" style="width: 100%">
  <thead>
  <%
  String meta = (String)request.getAttribute("metaData");
  String s2[] = new String[10];
  s2 = meta.split(",");
  %>
    <tr>
    <%
      for(int x= 0; x<s2.length-2;x++){
      
        if(x==4){
        %> <th><%out.print("Item Quantity");%></th> <%
        }
        else{%>
        <th><%out.print(s2[x]);%></th>
       <%}
       }%>
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
	<% 
	if(b==0){
		String user_id = s1[b].substring(1);
	%>
    <td><input type="hidden" name="id" value="<%=user_id%>">
    <%
      out.print(user_id);
	}
    else if(b== s1.length-1){
        String data = s1[b].substring(0, s1[b].length()-1);
        %>
        <td><%
      out.print(data);
    %></td>

    <%
    }
    
	else{
	%>
    <td><% out.print(s1[b]);%>
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

var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();

today = yyyy + '-' + mm + '-' + dd;

 
$(document).ready(function() {


    $.fn.dataTable.ext.search.push(
    function (settings, searchData, index, rowData, counter) {
      
      var checked = $('input:checkbox[name="chk_box"]').is(':checked');
      
      return checked && $(table.row(index).node()).css('background-color') !== 'rgb(255, 128, 128)' ? false: true;
      
    });
    // DataTables initialisation
    var table = $('#example').DataTable({
        
        dom: 'Bfrtip',
        buttons: [
            {
                text: 'Place Order',
                action: function ( e, dt, node, config ) {
                    openForm();
                }
            },'copy', 'csv', 'excel', 'pdf', 'print'
        ],
        "createdRow": function ( row, data, dataIndex ) {
            if ( data[3] <= today) {
            $(row).css('background-color', '#ff8080');            
            }
        }
    });
 
    // Refilter the table
   $('input:checkbox').on('change', function () {
        table.draw();
  });   
});
</script>
    <script src="/js/form.js"></script>

</body>
</html>