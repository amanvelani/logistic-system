<table border="1" class="container">
<thead>
<tr style="color: white">
<td>ID </td>
<td>Username</td>
<td>Password</td>
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
    <form action="/manager/update-complete" method="post" modelAttribute="userBean" >
	<% 
	if(b==0){
		String user_id = s1[b].substring(1);
	%>
    <input type="hidden" name="user_id" value="<%=user_id%>">
    <% 
        out.print(user_id);
	}
    else if(b==1){
        String username = s1[b];
        %><input type="text" name="username" value="<%=username%>"><%
    }
    else if(b==3){
        String role = s1[b];
        
        %><select name="role" id="role" >
				    <option value="<%=role%>"><%=role%></option>
					<option value="ROLE_USER">ROLE_USER</option>
					<option value="ROLE_MANAGER">ROLE_MANAGER</option>
		    </select><%
    }
    else if(b==2){
        String password = s1[b];
		int a = s1[b].length();
		for(int c=0;c<a;c++){
			out.print("*");
		}
        %>
        <input type="hidden" name="password" value="<%=password%>">
        <% 
	}
	else{
	%><input type = "submit" value="Update">
    
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