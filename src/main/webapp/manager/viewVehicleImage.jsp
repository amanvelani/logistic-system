<!DOCTYPE html>
<html>
<body>
<%
String id = (String)request.getAttribute("id");
%>
<a href="/manager/showVehicle"><button style="width:150px; height:30px"> Back </button></a>
<br>
<br>
<br>


<img src="/images/vehicle/<%=id%>.jpg"  style="width:400px; height:750px; padding-left:450px ">

</body>
</html>


