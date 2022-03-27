<div  id="myForm" style="display:none; margin-left:200px">
        <div class="card" >
                <a class="login-trigger" href="#" onclick="closeForm(); return false;"data-target="#login" data-toggle="modal" >Close</a>

        <form name="fileform" method="post" action="/master/addGoods" enctype="multipart/form-data">
                <h1>Add Goods to Database</h1>
                <br>
                <div class="form-data">
                    <!-- <div class="forms-inputs mb-4"> <span>ID</span> <input autocomplete="off" type="text"  name="user_id"></div> -->
                    <div class="forms-inputs mb-4"> <span>Item Name</span> <input type="text" name="itemname" size="50" placeholder="Enter Your Item Name" required/></div>
                    <div class="forms-inputs mb-4"> <span>Item Photo</span> <input type="file" style="margin-top:10px" name="photo" placeholder="Upload Your Image" required/></div>
                    <input class="btn btn-dark w-100" type="submit" value="Save">
               </div>
            </form> 
        </div>
    </div>

<div class="container mb-3 mt-3" >
<table class="table table-striped table-bordered mydatatable" style="width: 100%">
  <thead style="color:white">
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
	else if(b==1){
        String company = s1[b];
        %></td><td><% out.print(company);%></td><%
    }
    else if(b==2){
        String model = s1[b];
        %><td><% out.print(model);%></td><%
    }
    else if(b==3){
      String user_id = s1[0].substring(1);
        %>
        <td>
       <a href="/manager/delete-goods?id=<%=user_id%>"><button type="button"><i class="fa fa-ban" aria-hidden="true"></i></button></a>	   
	    </td>
    <%
    }
	else{
        String id = s1[0].substring(1);
	%>
    <td>
	  <a href="/manager/showGoodsImage?id=<%=id%>">
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